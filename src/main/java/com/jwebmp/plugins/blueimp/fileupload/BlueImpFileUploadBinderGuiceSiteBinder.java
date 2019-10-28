package com.jwebmp.plugins.blueimp.fileupload;

import com.guicedee.guicedservlets.services.GuiceSiteInjectorModule;
import com.guicedee.guicedservlets.services.IGuiceSiteBinder;
import com.guicedee.logger.LogFactory;
import com.jwebmp.plugins.blueimp.fileupload.servlets.AngularFileServlet;

import java.util.logging.Level;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class BlueImpFileUploadBinderGuiceSiteBinder
		implements IGuiceSiteBinder<GuiceSiteInjectorModule>
{
	public static final String BLUEIMP_FILEUPLOAD_SERVLETURL = "blueimpangularfileupload";
	private static final java.util.logging.Logger log = LogFactory.getLog("AngularFileUpload");

	@Override
	public void onBind(GuiceSiteInjectorModule module)
	{
		module.serveRegex$("(" + "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + ")" + QUERY_PARAMETERS_REGEX)
		      .with(AngularFileServlet.class);

		BlueImpFileUploadBinderGuiceSiteBinder.log.log(Level.INFO, "Serving File Uploads at {0}", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL);
	}
}
