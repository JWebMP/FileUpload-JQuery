import com.jwebmp.plugins.blueimp.fileupload.implementations.BlueImpFileUploadInclusionModule;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.*;

module com.jwebmp.plugins.blueimp.fileupload {

	exports com.jwebmp.plugins.blueimp.fileupload;
	exports com.jwebmp.plugins.blueimp.fileupload.options;
	exports com.jwebmp.plugins.blueimp.fileupload.interfaces;
	exports com.jwebmp.plugins.blueimp.fileupload.intercepters;
	exports com.jwebmp.plugins.blueimp.fileupload.parts;
	exports com.jwebmp.plugins.blueimp.fileupload.parts.json;

	requires com.guicedee.guicedservlets;

	requires org.apache.commons.io;
	requires org.apache.commons.fileupload;

	requires com.jwebmp.plugins.blueimp.gallery;
	requires com.jwebmp.plugins.jqueryui;
	
	uses OnDeleteFileInterceptor;
	uses OnFileUploadInterceptor;
	uses OnGetFileInterceptor;
	uses OnGetFileDownloadInterceptor;
	uses OnThumbnailFileInterceptor;

	provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadPageConfigurator;
	provides com.guicedee.guicedservlets.services.IGuiceSiteBinder with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadBinderGuiceSiteBinder;
	provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions with BlueImpFileUploadInclusionModule;
	
	opens com.jwebmp.plugins.blueimp.fileupload to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.options to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.interfaces to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
//	opens com.jwebmp.plugins.blueimp.fileupload.angular to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.intercepters to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	exports com.jwebmp.plugins.blueimp.fileupload.servlets;
	opens com.jwebmp.plugins.blueimp.fileupload.servlets to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.parts to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.parts.json to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;

}
