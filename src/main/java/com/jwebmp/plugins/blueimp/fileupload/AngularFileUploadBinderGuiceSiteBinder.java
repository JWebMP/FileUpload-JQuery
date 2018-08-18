package com.jwebmp.plugins.blueimp.fileupload;

import com.jwebmp.guicedservlets.GuiceSiteInjectorModule;
import com.jwebmp.guicedservlets.services.IGuiceSiteBinder;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.blueimp.fileupload.servlets.AngularFileServlet;

import java.util.logging.Level;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class AngularFileUploadBinderGuiceSiteBinder
		implements IGuiceSiteBinder<GuiceSiteInjectorModule>
{
	public static final String BLUEIMP_FILEUPLOAD_SERVLETURL = "blueimpangularfileupload";
	private static final java.util.logging.Logger log = LogFactory.getLog("AngularFileUpload");

	@Override
	public void onBind(GuiceSiteInjectorModule module)
	{
		module.serveRegex$("(" + "/" + AngularFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + ")" + QUERY_PARAMETERS_REGEX)
		      .with(AngularFileServlet.class);

		AngularFileUploadBinderGuiceSiteBinder.log.log(Level.INFO, "Serving File Uploads at {0}", "/" + AngularFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL);
	}
}
