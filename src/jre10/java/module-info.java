import com.jwebmp.core.base.angular.services.IAngularConfiguration;
import com.jwebmp.core.base.angular.services.IAngularController;
import com.jwebmp.core.base.angular.services.IAngularModule;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.guicedservlets.services.IGuiceSiteBinder;
import com.jwebmp.plugins.blueimp.fileupload.AngularFileUploadBinderGuiceSiteBinder;
import com.jwebmp.plugins.blueimp.fileupload.AngularFileUploadDataBinderConfigurationBase;
import com.jwebmp.plugins.blueimp.fileupload.AngularFileUploadPageConfigurator;
import com.jwebmp.plugins.blueimp.fileupload.angular.AngularBlueImpFileUploadModule;
import com.jwebmp.plugins.blueimp.fileupload.angular.BlueImpFileDestroyController;

module com.jwebmp.plugins.blueimp.fileupload {

	exports com.jwebmp.plugins.blueimp.fileupload;
	exports com.jwebmp.plugins.blueimp.fileupload.options;
	exports com.jwebmp.plugins.blueimp.fileupload.interfaces;
	exports com.jwebmp.plugins.blueimp.fileupload.intercepters;
	exports com.jwebmp.plugins.blueimp.fileupload.parts;
	exports com.jwebmp.plugins.blueimp.fileupload.parts.json;

	requires com.jwebmp.core;
	requires com.jwebmp.guicedservlets;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	requires java.activation;
	requires java.validation;
	requires javax.servlet.api;
	requires java.logging;
	requires com.jwebmp.logmaster;
	requires com.google.guice;
	requires com.jwebmp.guicedinjection;
	requires org.apache.commons.io;
	requires commons.fileupload;
	requires tika.core;
	requires com.jwebmp.plugins.blueimp.gallery;

	provides IAngularConfiguration with AngularFileUploadDataBinderConfigurationBase;
	provides IAngularController with BlueImpFileDestroyController;
	provides IAngularModule with AngularBlueImpFileUploadModule;
	provides IPageConfigurator with AngularFileUploadPageConfigurator;
	provides IGuiceSiteBinder with AngularFileUploadBinderGuiceSiteBinder;

	opens com.jwebmp.plugins.blueimp.fileupload to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.options to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.interfaces to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.angular to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.intercepters to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.servlets to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.parts to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.blueimp.fileupload.parts.json to com.fasterxml.jackson.databind, com.jwebmp.core;
}
