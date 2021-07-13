/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jwebmp.plugins.blueimp.fileupload.servlets;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import com.guicedee.guicedinjection.interfaces.IDefaultService;
import com.guicedee.guicedinjection.json.StaticStrings;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.servlets.JWDefaultServlet;
import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.guicedinjection.pairing.Pair;
import com.guicedee.guicedservlets.GuicedServletKeys;
import com.jwebmp.interception.services.DataCallIntercepter;
import com.guicedee.logger.LogFactory;
import com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadBinderGuiceSiteBinder;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnDeleteFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnFileUploadInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnGetFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnThumbnailFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.parts.json.JsonFile;
import com.jwebmp.plugins.blueimp.fileupload.parts.json.JsonFilesArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.interception.JWebMPInterceptionBinder.*;

/**
 * The default file receiving servlet
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Singleton
@MultipartConfig
public class AngularFileServlet
		extends JWDefaultServlet
{
	private static final Logger log = LogFactory.getInstance()
	                                            .getLogger("BlueImpAngularFileServlet");
	
	private static final String getFileMethod = "getfile";
	private static final String deleteFileMethod = "delfile";
	private static final String getThumbMethod = "getthumb";
	
	private static Map<String, File> stringFileMap = new HashMap<>();
	
	/**
	 * Constructs a new File Servlet
	 */
	public AngularFileServlet()
	{
		//Nothing Needed
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader(StaticStrings.ACCESS_CONTROL_ALLOW_HEADERS_HEADER_NAME, "Content-Type, Accept, Content-Range, Content-Disposition");
		AngularFileServlet.log.log(Level.INFO, "[SessionID]-[{0}];[Connection]-[Data Call Connection Established]", request.getSession()
		                                                                                                                   .getId());
		
		if (!ServletFileUpload.isMultipartContent(request))
		{
			AngularFileServlet.log.warning("File Upload is not a MultiPart, Incorrect servlet being hit");
			return;
		}
		
		for (DataCallIntercepter<?> dataCallIntercepter : GuiceContext.get(DataCallInterceptorKey))
		{
			dataCallIntercepter.intercept(GuiceContext.get(AjaxCall.class), GuiceContext.get(AjaxResponse.class));
		}
		
		long totalS = 0;
		long rangeTotal = 0;
		long remaining = 0;
		boolean completed = false;
		if (!Strings.isNullOrEmpty(request.getHeader("Content-Range")))
		{
			System.out.println("Multipart upload");
			String rangeString = request.getHeader("Content-Range");
			String rangeUpTo = rangeString.substring(rangeString.indexOf('-') + 1, rangeString.indexOf('/'));
			String totalSize = rangeString.substring(rangeString.indexOf('/') + 1);
			totalS = Long.parseLong(totalSize);
			rangeTotal = Long.parseLong(rangeUpTo);
			remaining = (totalS - 1) - rangeTotal;
			if (remaining == 0)
			{
				completed = true;
			}
		}
		else
		{
			totalS = request.getContentLengthLong();
			rangeTotal = request.getContentLengthLong();
			remaining = 0;
			completed = true;
		}
		
		String namedCollector = request.getParameter("uploadCollectorName");
		if(Strings.isNullOrEmpty(namedCollector))
		{
			namedCollector = "";
		}
		
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		JsonFilesArray filesArray = new JsonFilesArray();
		try
		{
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items)
			{
				if (!item.isFormField())
				{
					processUploadedFile(completed, totalS, item, filesArray,namedCollector);
				}
			}
		}
		catch (FileUploadException e)
		{
			AngularFileServlet.log.log(Level.SEVERE, "File Upload Exception Occured : ", e);
		}
		catch (Exception e)
		{
			AngularFileServlet.log.log(Level.SEVERE, "Generic Exception on File Upload Occured : ", e);
		}
		finally
		{
			writeOutput(new StringBuilder(filesArray.toString()), "application/json", Charset.defaultCharset());
		}
	}
	
	@Override
	public void perform()
	{
		HttpServletRequest request = GuiceContext.get(GuicedServletKeys.getHttpServletRequestKey());
		HttpServletResponse response = GuiceContext.get(GuicedServletKeys.getHttpServletResponseKey());
		
		if (request.getParameter(AngularFileServlet.getFileMethod) != null)
		{
			
			processGetFile(request, response);
			
		}
		else if (request.getParameter(AngularFileServlet.deleteFileMethod) != null)
		{
			processDeleteFile(request);
		}
		else if (request.getParameter(AngularFileServlet.getThumbMethod) != null)
		{
			processGetThumb(request, response);
		}
		else
		{
			processRequest(request, response);
		}
	}
	
	private void processGetFile(HttpServletRequest request, HttpServletResponse response)
	{
		String filename = request.getParameter(AngularFileServlet.getFileMethod);
		Set<OnGetFileInterceptor> intercepters = IDefaultService.loaderToSet(ServiceLoader.load(OnGetFileInterceptor.class));
		if (intercepters.isEmpty())
		{
			AngularFileServlet.log.warning(
					"There are no file getter interceptors to catch this file get. Create a class that implements " + "OnGetFileInterceptor to deliver this file.");
		}
		else
		{
			for (OnGetFileInterceptor a : intercepters)
			{
				String namedCollector = request.getParameter("uploadCollectorName");
				if(Strings.isNullOrEmpty(namedCollector))
				{
					namedCollector = "";
				}
				if (a.name()
				     .equals(namedCollector))
				{
					Pair<String, InputStream> is = a.onGetFile(filename);
					// String mimeType = new Tika().detect(is.getKey());
					// response.setContentType(mimeType);
					response.setHeader("Content-Disposition", "inline; filename=\"" + is.getKey() + "\"");
					try
					{
						IOUtils.copyLarge(is.getValue(), response.getOutputStream());
						is.getValue()
						  .close();
					}
					catch (IOException e)
					{
						AngularFileServlet.log.log(Level.SEVERE, "Unable to deliver file when input stream is transferred to output stream", e);
					}
				}
			}
		}
	}
	
	private void processDeleteFile(HttpServletRequest request)
	{
		String filename = request.getParameter(AngularFileServlet.deleteFileMethod);
		
		Set<OnDeleteFileInterceptor> intercepters = IDefaultService.loaderToSet(ServiceLoader.load(OnDeleteFileInterceptor.class));
		if (intercepters.isEmpty())
		{
			AngularFileServlet.log.warning(
					"There are no file delete interceptors to catch this file delete. Create a class that implements " + "OnDeleteFileInterceptor to delete this file.");
		}
		else
		{
			for (OnDeleteFileInterceptor a : intercepters)
			{
				String namedCollector = request.getParameter("uploadCollectorName");
				if(Strings.isNullOrEmpty(namedCollector))
				{
					namedCollector = "";
				}
				if(a.name().equals(namedCollector))
				a.onDeleteFile(filename);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void processGetThumb(HttpServletRequest request, HttpServletResponse response)
	{
		String filename = request.getParameter(AngularFileServlet.getThumbMethod);
		
		Set<OnThumbnailFileInterceptor> intercepters = IDefaultService.loaderToSet(ServiceLoader.load(OnThumbnailFileInterceptor.class));
		if (intercepters.isEmpty())
		{
			AngularFileServlet.log.warning(
					"There are no file get thumbnail interceptors to catch this file thumbnail. Create a class that implements " +
					"OnThumbnailFileInterceptor to deliver the thumbnail.");
		}
		else
		{
			for (OnThumbnailFileInterceptor<?> a : intercepters)
			{
				String namedCollector = request.getParameter("uploadCollectorName");
				if(Strings.isNullOrEmpty(namedCollector))
				{
					namedCollector = "";
				}
				if(a.name().equals(namedCollector))
				{
					Pair<String, InputStream> is = a.onThumbnailGet(filename);
					if (is == null)
					{
						continue;
					}
					response.setContentType("application/json");
					response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
					//String mimeType = new Tika().detect(is.getKey());
					try
					{
						IOUtils.copyLarge(is.getValue(), response.getOutputStream());
						is.getValue()
						  .close();
					}
					catch (IOException e)
					{
						AngularFileServlet.log.log(Level.SEVERE, "Unable to deliver file when input stream is transferred to output stream", e);
					}
				}
			}
		}
	}
	
	private synchronized void processUploadedFile(boolean completed, Long totalS, FileItem item, JsonFilesArray filesArray,String namedCollector) throws IOException
	{
		String fileUploadIdentifier = item.getName() + "|" + totalS + "|" + item.getFieldName();
		if (!AngularFileServlet.stringFileMap.containsKey(fileUploadIdentifier))
		{
			File tempFile = File.createTempFile("jwebswing_fileUpload_", "-ul");
			AngularFileServlet.stringFileMap.put(fileUploadIdentifier, tempFile);
		}
		
		File tempFile = AngularFileServlet.stringFileMap.get(fileUploadIdentifier);
		FileUtils.writeByteArrayToFile(tempFile, item.get(), true);
		
		if(completed)
		{
			JsonFile file = new JsonFile();
			file.setName(item.getName());
			file.setSize(tempFile.length());
			
			FileInputStream fis = new FileInputStream(tempFile);
			file.setContent(fis);
			file.setType(item.getContentType());
			//todo this stream should close
			
			file.setDownloadUrl(SessionHelper.getServerPath() + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?getfile=" + item.getName() + "&uploadCollectorName=" + namedCollector );
			file.setThumbnailUrl(SessionHelper.getServerPath() + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?getthumb=" + item.getName() + "&uploadCollectorName=" + namedCollector );
			file.setDeleteUrl(SessionHelper.getServerPath() + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?delfile=" + item.getName() + "&uploadCollectorName=" + namedCollector );
			
			filesArray.getAllFiles()
			          .add(file);
			
			Set<OnFileUploadInterceptor> intercepters = IDefaultService.loaderToSet(ServiceLoader.load(OnFileUploadInterceptor.class));
			
			if (intercepters.isEmpty())
			{
				AngularFileServlet.log.warning(
						"There are no file upload interceptors to catch this file upload. Create a class that implements " + "OnFileUploadInterceptor to use this file.");
			}
			else
			{
				for (OnFileUploadInterceptor a : intercepters)
				{
					if(Strings.isNullOrEmpty(namedCollector))
					{
						namedCollector = "";
					}
					if(a.name().equals(namedCollector))
						a.onUploadCompleted(file);
				}
			}
			
			fis.close();
			
			if (!tempFile.delete())
			{
				AngularFileServlet.log.warning("Unable to delete temporary file : " + tempFile);
			}
			AngularFileServlet.stringFileMap.remove(fileUploadIdentifier);
		}
	}
}
