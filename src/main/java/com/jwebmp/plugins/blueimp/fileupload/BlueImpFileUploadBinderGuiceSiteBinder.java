package com.jwebmp.plugins.blueimp.fileupload;

import com.google.inject.servlet.ServletModule;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;
import com.jwebmp.plugins.blueimp.fileupload.servlets.AngularFileServlet;
import lombok.extern.java.Log;

import java.util.logging.Level;

import static com.jwebmp.core.utilities.StaticStrings.QUERY_PARAMETERS_REGEX;

@Log
public class BlueImpFileUploadBinderGuiceSiteBinder
				extends ServletModule
				implements IGuiceModule<BlueImpFileUploadBinderGuiceSiteBinder>
{
	public static final String BLUEIMP_FILEUPLOAD_SERVLETURL = "blueimpangularfileupload";
	
	@Override
	public void configureServlets()
	{
		serveRegex("(" + "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + ")" + QUERY_PARAMETERS_REGEX)
						.with(AngularFileServlet.class);
		BlueImpFileUploadBinderGuiceSiteBinder.log.log(Level.INFO, "Serving File Uploads at {0}", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL);
	}
}
