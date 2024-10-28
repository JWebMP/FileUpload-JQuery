import com.jwebmp.plugins.blueimp.fileupload.implementations.BlueImpFileUploadInclusionModule;
import com.jwebmp.plugins.blueimp.fileupload.intercepters.*;

module com.jwebmp.plugins.blueimp.fileupload {

    exports com.jwebmp.plugins.blueimp.fileupload;
    exports com.jwebmp.plugins.blueimp.fileupload.options;
    exports com.jwebmp.plugins.blueimp.fileupload.interfaces;
    exports com.jwebmp.plugins.blueimp.fileupload.intercepters;
    exports com.jwebmp.plugins.blueimp.fileupload.parts;
    exports com.jwebmp.plugins.blueimp.fileupload.parts.json;

    requires org.apache.commons.io;

    requires guiced.vertx;

    requires static lombok;

    requires com.jwebmp.plugins.blueimp.gallery;
    requires com.jwebmp.plugins.jqueryui;
    requires com.guicedee.jsonrepresentation;

    requires com.guicedee.client;

    requires com.jwebmp.client;
    requires com.jwebmp.core;
    requires io.vertx.core;

    uses OnDeleteFileInterceptor;
    uses OnFileUploadInterceptor;
    uses OnGetFileInterceptor;
    uses OnGetFileDownloadInterceptor;
    uses OnThumbnailFileInterceptor;

    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadPageConfigurator;

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
