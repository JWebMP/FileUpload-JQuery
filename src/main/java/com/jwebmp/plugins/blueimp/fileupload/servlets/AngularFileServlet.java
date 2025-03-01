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
import com.guicedee.client.IGuiceContext;
import com.guicedee.guicedinjection.pairing.Pair;
import com.guicedee.vertx.web.spi.VertxRouterConfigurator;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnDeleteFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnFileUploadInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnGetFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.OnThumbnailFileInterceptor;
import com.jwebmp.plugins.blueimp.fileupload.parts.json.JsonFile;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.logging.Level;

/**
 * The default file receiving servlet
 */
@SuppressWarnings({"unchecked",
        "rawtypes"})
@Singleton
@Log
public class AngularFileServlet
        implements VertxRouterConfigurator
{
    private static final String getFileMethod = "getfile";
    private static final String deleteFileMethod = "delfile";
    private static final String getThumbMethod = "getthumb";

    /**
     * Constructs a new File Servlet
     */
    public AngularFileServlet()
    {
        //Nothing Needed
    }

    private void processGetFile(RoutingContext context)
    {
        String filename = context.request()
                .getParam(AngularFileServlet.getFileMethod);
        Set<OnGetFileInterceptor> intercepters = IGuiceContext.loaderToSet(ServiceLoader.load(OnGetFileInterceptor.class));
        if (intercepters.isEmpty())
        {
            AngularFileServlet.log.warning(
                    "There are no file getter interceptors to catch this file get. Create a class that implements " + "OnGetFileInterceptor to deliver this file.");
        } else
        {
            for (OnGetFileInterceptor a : intercepters)
            {
                String namedCollector = context.request()
                        .getParam("uploadCollectorName");
                if (Strings.isNullOrEmpty(namedCollector))
                {
                    namedCollector = "";
                }
                if (a.name()
                        .equals(namedCollector))
                {
                    Pair<String, InputStream> is = a.onGetFile(filename);
                    // String mimeType = new Tika().detect(is.getKey());
                    // response.setContentType(mimeType);
                    ;
                    File tempFile = null;
                    try
                    {
                        tempFile = File.createTempFile("blueimp", "getfile");
                        try (
                                FileOutputStream fos = new FileOutputStream(tempFile))
                        {
                            IOUtils.copyLarge(is.getValue(), fos);
                            context.response()
                                    .sendFile(tempFile.getAbsolutePath());
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    } catch (Exception e)
                    {
                        AngularFileServlet.log.log(Level.SEVERE, "Unable to deliver file when input stream is transferred to output stream", e);
                    }
                }
            }
        }
    }

    private void processDeleteFile(RoutingContext request)
    {
        String filename = request.request()
                .getParam(AngularFileServlet.deleteFileMethod);

        Set<OnDeleteFileInterceptor> intercepters = IGuiceContext.loaderToSet(ServiceLoader.load(OnDeleteFileInterceptor.class));
        if (intercepters.isEmpty())
        {
            AngularFileServlet.log.warning(
                    "There are no file delete interceptors to catch this file delete. Create a class that implements " + "OnDeleteFileInterceptor to delete this file.");
        } else
        {
            for (OnDeleteFileInterceptor a : intercepters)
            {
                String namedCollector = request.request()
                        .getParam("uploadCollectorName");
                if (Strings.isNullOrEmpty(namedCollector))
                {
                    namedCollector = "";
                }
                if (a.name()
                        .equals(namedCollector))
                {
                    a.onDeleteFile(filename);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private void processGetThumb(RoutingContext context)
    {
        String filename = context.request()
                .getParam(AngularFileServlet.getThumbMethod);

        Set<OnThumbnailFileInterceptor> intercepters = IGuiceContext.loaderToSet(ServiceLoader.load(OnThumbnailFileInterceptor.class));
        if (intercepters.isEmpty())
        {
            AngularFileServlet.log.warning(
                    "There are no file get thumbnail interceptors to catch this file thumbnail. Create a class that implements " +
                            "OnThumbnailFileInterceptor to deliver the thumbnail.");
        } else
        {
            for (OnThumbnailFileInterceptor<?> a : intercepters)
            {
                String namedCollector = context.request()
                        .getParam("uploadCollectorName");
                if (Strings.isNullOrEmpty(namedCollector))
                {
                    namedCollector = "";
                }
                if (a.name()
                        .equals(namedCollector))
                {
                    Pair<String, InputStream> is = a.onThumbnailGet(filename);
                    if (is == null)
                    {
                        continue;
                    }
                    File tempFile = null;
                    try
                    {
                        tempFile = File.createTempFile("blueimp", "thumbfile");
                        try (
                                FileOutputStream fos = new FileOutputStream(tempFile))
                        {
                            IOUtils.copyLarge(is.getValue(), fos);
                            context.response()
                                    .sendFile(tempFile.getAbsolutePath());
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    } catch (Exception e)
                    {
                        AngularFileServlet.log.log(Level.SEVERE, "Unable to deliver file when input stream is transferred to output stream", e);
                    }
                }
            }
        }
    }


    @Override
    public Router builder(Router router)
    {
        Route handler = router.post("/blueimpangularfileupload")
                .handler(BodyHandler.create(true)
                        .setUploadsDirectory("uploads")
                        .setDeleteUploadedFilesOnEnd(true));
        // handle the form
        router.post("/blueimpangularfileupload")
                .handler(ctx -> {
                    ctx.response()
                            .setChunked(true);
                    // in your example you only handle 1 file upload, here you can handle
                    // any number of uploads
                    for (FileUpload f : ctx.fileUploads())
                    {
                        // do whatever you need to do with the file (it is already saved
                        // on the directory you wanted...
                        System.out.println("Filename: " + f.fileName());
                        System.out.println("Size: " + f.size());

                        JsonFile file = new JsonFile();
                        file.setName(f.fileName());
                        file.setSize(f.size());
                        try (FileInputStream fis = new FileInputStream(f.uploadedFileName()))
                        {
                            file.setContent(IOUtils.toBufferedInputStream(fis));
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }

                        String uploadCollectorName = ctx.request()
                                .getParam("uploadCollectorName");

                        file.setDownloadUrl(ctx.request()
                                .uri() + "?getfile=" + f.name() + "&uploadCollectorName=" + uploadCollectorName);
                        file.setThumbnailUrl(ctx.request()
                                .uri() + "?getthumb=" + f.name() + "&uploadCollectorName=" + uploadCollectorName);
                        file.setDeleteUrl(ctx.request()
                                .uri() + "?delfile=" + f.name() + "&uploadCollectorName=" + uploadCollectorName);

                        Set<OnFileUploadInterceptor> intercepters = IGuiceContext.loaderToSet(ServiceLoader.load(OnFileUploadInterceptor.class));
                        if (intercepters.isEmpty())
                        {
                            AngularFileServlet.log.warning(
                                    "There are no file upload interceptors to catch this file upload. Create a class that implements " + "OnFileUploadInterceptor to use this file.");
                        } else
                        {
                            for (OnFileUploadInterceptor a : intercepters)
                            {
                                if (Strings.isNullOrEmpty(uploadCollectorName))
                                {
                                    uploadCollectorName = "";
                                }
                                if (a.name()
                                        .equals(uploadCollectorName))
                                {
                                    a.onUploadCompleted(file);
                                }
                            }
                        }

                    }
                    ctx.response()
                            .end();
                });
        router.get("/blueimpangularfileupload")
                .handler(ctx -> {
                    String getfile = ctx.request()
                            .getParam(getFileMethod);
                    String getthumb = ctx.request()
                            .getParam(getThumbMethod);
                    String delfile = ctx.request()
                            .getParam(deleteFileMethod);
                    String uploadCollectorName = ctx.request()
                            .getParam("uploadCollectorName");
                    if (!Strings.isNullOrEmpty(getfile))
                    {
                        processGetFile(ctx);
                    } else if (!Strings.isNullOrEmpty(getthumb))
                    {
                        processGetThumb(ctx);
                    } else if (!Strings.isNullOrEmpty(delfile))
                    {
                        processGetThumb(ctx);
                    }
                });
        return router;
    }
}
