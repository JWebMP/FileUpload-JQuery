module com.jwebmp.plugins.blueimp.fileupload {

	exports com.jwebmp.plugins.blueimp.fileupload;
	exports com.jwebmp.plugins.blueimp.fileupload.options;
	exports com.jwebmp.plugins.blueimp.fileupload.interfaces;
	exports com.jwebmp.plugins.blueimp.fileupload.intercepters;
	exports com.jwebmp.plugins.blueimp.fileupload.parts;
	exports com.jwebmp.plugins.blueimp.fileupload.parts.json;

	requires com.jwebmp.core;
	requires com.guicedee.guicedservlets;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	requires jakarta.activation;
	requires java.validation;
	requires javax.servlet.api;
	requires java.logging;
	requires com.guicedee.logmaster;
	requires com.google.guice;
	requires com.guicedee.guicedinjection;
	requires org.apache.commons.io;
	requires org.apache.commons.fileupload;

	requires com.jwebmp.plugins.blueimp.gallery;
	requires com.jwebmp.interception;
	requires com.jwebmp.core.angularjs;

	requires io.github.classgraph;

	provides com.jwebmp.core.base.angular.services.IAngularConfiguration with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadDataBinderConfigurationBase;
	provides com.jwebmp.core.base.angular.services.IAngularController with com.jwebmp.plugins.blueimp.fileupload.angular.BlueImpFileDestroyController;
	provides com.jwebmp.core.base.angular.services.IAngularModule with com.jwebmp.plugins.blueimp.fileupload.angular.AngularBlueImpFileUploadModule;
	provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadPageConfigurator;
	provides com.guicedee.guicedservlets.services.IGuiceSiteBinder with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadBinderGuiceSiteBinder;

	provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.plugins.blueimp.fileupload.implementations.BlueImpFileUploadExclusionsModule;

	opens com.jwebmp.plugins.blueimp.fileupload to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.options to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.interfaces to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.angular to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.intercepters to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.servlets to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.parts to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
	opens com.jwebmp.plugins.blueimp.fileupload.parts.json to com.fasterxml.jackson.databind, com.jwebmp.core, com.google.guice;
}
