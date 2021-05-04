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

package com.jwebmp.plugins.blueimp.fileupload;

import com.google.inject.Singleton;
import com.jwebmp.core.Page;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.core.base.references.JavascriptReference;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.PluginStatus;
import com.jwebmp.core.plugins.jquery.JQueryPageConfigurator;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadDefaultOptions;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGallery;
import jakarta.validation.constraints.NotNull;

@PluginInformation(pluginName = "BlueImp File Upload",
                   pluginUniqueName = "blueimp-file-upload",
                   pluginDescription = "File Upload widget with multiple file selection, drag&drop support, progress bars, validation and preview " +
                                       "images, audio and video for jQuery.\n" +
                                       "Supports cross-domain, chunked and resumable file uploads " +
                                       "and client-side image resizing.\n" +
                                       "Works with any server-side platform (PHP, Python, Ruby on " +
                                       "Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.",
                   pluginVersion = "2.4.7",
                   pluginDependancyUniqueIDs = "jquery-ui,jquery,angular",
                   pluginCategories = "File Upload, Widget, BlueImp",
                   pluginSubtitle = "A File Uploader for JWebSwing",
                   pluginGitUrl = "https://github.com/blueimp/jQuery-File-Upload",
                   pluginSourceUrl = "https://github.com/blueimp/jQuery-File-Upload",
                   pluginWikiUrl = "https://github.com/GedMarc/BlueImpFileUploadPageConfigurator/wiki",
                   pluginOriginalHomepage = "https://github.com/blueimp/jQuery-File-Upload",
                   pluginDownloadUrl = "https://mvnrepository.com/artifact/com.jwebmp.plugins.blueimp/jwebmp-blueimp-fileupload",
                   pluginIconUrl = "",
                   pluginIconImageUrl = "",
                   pluginLastUpdatedDate = "2017/09/18",
                   pluginGroupId = "com.jwebmp.plugins.blueimp",
                   pluginArtifactId = "jwebmp-blueimp-fileupload",
                   pluginModuleName = "com.jwebmp.plugins.blueimp.fileupload",
                   pluginStatus = PluginStatus.Released

)
@Singleton
public class BlueImpFileUploadPageConfigurator
		implements IPageConfigurator<BlueImpFileUploadPageConfigurator>
{
	/**
	 * If this configurator is enabled
	 */
	private static boolean enabled = true;
	private static BlueImpFileUploadDefaultOptions<?> defaultOptions;
	private static boolean renderJqueryUI;
	/**
	 * The div for the gallery
	 */
	private BlueImpGallery<?> galleryDiv;
	
	/**
	 * Configures the page for this component
	 */
	public BlueImpFileUploadPageConfigurator()
	{
		//Nothing Needed
	}
	
	/**
	 * Method isEnabled returns the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @return the enabled (type boolean) of this AngularAnimatedChangePageConfigurator object.
	 */
	public static boolean isEnabled()
	{
		return BlueImpFileUploadPageConfigurator.enabled;
	}
	
	/**
	 * Method setEnabled sets the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @param mustEnable the enabled of this AngularAnimatedChangePageConfigurator object.
	 */
	public static void setEnabled(boolean mustEnable)
	{
		BlueImpFileUploadPageConfigurator.enabled = mustEnable;
	}
	
	/**
	 * Returns the default options applied with this object
	 *
	 * @return
	 */
	@NotNull
	public static BlueImpFileUploadDefaultOptions<?> getDefaultOptions()
	{
		if (BlueImpFileUploadPageConfigurator.defaultOptions == null)
		{
			BlueImpFileUploadPageConfigurator.defaultOptions = new BlueImpFileUploadDefaultOptions<>();
		}
		return BlueImpFileUploadPageConfigurator.defaultOptions;
	}
	
	/**
	 * Sets the default options applied
	 *
	 * @param defaultOptions
	 */
	public static void setDefaultOptions(BlueImpFileUploadDefaultOptions<?> defaultOptions)
	{
		BlueImpFileUploadPageConfigurator.defaultOptions = defaultOptions;
	}
	
	public static boolean isRenderJqueryUI()
	{
		return BlueImpFileUploadPageConfigurator.renderJqueryUI;
	}
	
	public static void setRenderJqueryUI(boolean renderJqueryUI)
	{
		BlueImpFileUploadPageConfigurator.renderJqueryUI = renderJqueryUI;
	}
	
	@NotNull
	@Override
	public Page<?> configure(Page<?> page)
	{
		if (!page.isConfigured() && enabled())
		{
			registerModules();
			
			page.getBody()
			    .add(getGalleryDiv());
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.TemplatesReference.getJavaScriptReference()
			                                                                             .setSortOrder(200));
			
		/*	page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageAll.getJavaScriptReference()
			                                                                                  .setSortOrder(201));*/
			page.getBody()
			    .addCssReference(BlueImpFileUploadReferencePool.LoadImageJCropReference.getCssReference()
			                                                                           .setSortOrder(202));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageAll.getJavaScriptReference()
			                                                                             .setSortOrder(203));
			/*
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageReference.getJavaScriptReference()
			                                                                             .setSortOrder(203));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageMetaReference.getJavaScriptReference()
			                                                                                 .setSortOrder(204));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageExifReference.getJavaScriptReference()
			                                                                                 .setSortOrder(205));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.LoadImageExifMapReference.getJavaScriptReference()
			                                                                                    .setSortOrder(206));*/
			
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.CanvasToBlobReference.getJavaScriptReference()
			                                                                                .setSortOrder(207));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadIFrameTransportReference.getJavaScriptReference()
			                                                                                             .setSortOrder(217));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadReference.getJavaScriptReference()
			                                                                              .setSortOrder(218));
			page.getBody()
			    .addCssReference(BlueImpFileUploadReferencePool.FileUploadReference.getCssReference()
			                                                                       .setSortOrder(219));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadProcessReference.getJavaScriptReference()
			                                                                                     .setSortOrder(220));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadAudioReference.getJavaScriptReference()
			                                                                                   .setSortOrder(221));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadImageReference.getJavaScriptReference()
			                                                                                   .setSortOrder(222));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadValidateReference.getJavaScriptReference()
			                                                                                      .setSortOrder(223));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadVideoReference.getJavaScriptReference()
			                                                                                   .setSortOrder(224));
			
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadUIReference.getJavaScriptReference()
			                                                                                .setSortOrder(225));
			
			page.getBody()
			    .addCssReference(BlueImpFileUploadReferencePool.FileUploadUIReference.getCssReference()
			                                                                                .setSortOrder(225));
			
			
/*
			page.getBody()
			    .addJavaScriptReference(BlueImpFileUploadReferencePool.FileUploadAngularReference.getJavaScriptReference()
			                                                                                     .setSortOrder(227));*/
			
	/*		page.getBody()
			    .addJavaScriptReference(new JavascriptReference("blueimpfileuploadtemplateupload", 1.0, "blueimpfileupload/templateupload.js")
					    .setSortOrder(1000)
					    .setCanMinifyAtRemote(false)
			    .setScriptType("text/x-tmpl"));
			
			page.getBody()
			    .addJavaScriptReference(new JavascriptReference("blueimpfileuploadtemplatedownload", 1.0, "blueimpfileupload/templatedownload.js")
					    .setCanMinifyAtRemote(false)
					    .setSortOrder(1001)
					    .setScriptType("text/x-tmpl"));
			*/
			
			page.getBody().add(new Paragraph<>("<script id=\"template-upload\" type=\"text/x-tmpl\">\n" +
			                                   "      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
			                                   "          <tr class=\"template-upload {%=o.options.loadImageFileTypes.test(file.type)?' image':''%}\">\n" +
			                                   "              <td>\n" +
			                                   "                  <span class=\"preview\"></span>\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  <p class=\"name\">{%=file.name%}</p>\n" +
			                                   "                  <strong class=\"error text-danger\"></strong>\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  <p class=\"size\">Processing...</p>\n" +
			                                   "                  <div class=\"progress progress-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" aria-valuenow=\"0\"><div class=\"progress-bar progress-bar-success\" style=\"width:0%;\"></div></div>\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  {% if (!o.options.autoUpload && o.options.edit && o.options.loadImageFileTypes.test(file.type)) { %}\n" +
			                                   "                    <button class=\"btn btn-success edit\" data-index=\"{%=i%}\" disabled>\n" +
			                                   "                        <i class=\"glyphicon glyphicon-edit\"></i>\n" +
			                                   "                        <span>Edit</span>\n" +
			                                   "                    </button>\n" +
			                                   "                  {% } %}\n" +
			                                   "                  {% if (!i && !o.options.autoUpload) { %}\n" +
			                                   "                      <button class=\"btn btn-primary start\" disabled>\n" +
			                                   "                          <i class=\"glyphicon glyphicon-upload\"></i>\n" +
			                                   "                          <span>Start</span>\n" +
			                                   "                      </button>\n" +
			                                   "                  {% } %}\n" +
			                                   "                  {% if (!i) { %}\n" +
			                                   "                      <button class=\"btn btn-warning cancel\">\n" +
			                                   "                          <i class=\"glyphicon glyphicon-ban-circle\"></i>\n" +
			                                   "                          <span>Cancel</span>\n" +
			                                   "                      </button>\n" +
			                                   "                  {% } %}\n" +
			                                   "              </td>\n" +
			                                   "          </tr>\n" +
			                                   "      {% } %}\n" +
			                                   "    </script>\n" +
			                                   "    <!-- The template to display files available for download -->\n" +
			                                   "    <script id=\"template-download\" type=\"text/x-tmpl\">\n" +
			                                   "      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
			                                   "          <tr class=\"template-download {%=file.thumbnailUrl?' image':''%}\">\n" +
			                                   "              <td>\n" +
			                                   "                  <span class=\"preview\">\n" +
			                                   "                      {% if (file.thumbnailUrl) { %}\n" +
			                                   "                          <a href=\"{%=file.url%}\" title=\"{%=file.name%}\" download=\"{%=file.name%}\" data-gallery><img src=\"{%=file.thumbnailUrl%}\"></a>\n" +
			                                   "                      {% } %}\n" +
			                                   "                  </span>\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  <p class=\"name\">\n" +
			                                   "                      {% if (file.url) { %}\n" +
			                                   "                          <a href=\"{%=file.url%}\" title=\"{%=file.name%}\" download=\"{%=file.name%}\" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>\n" +
			                                   "                      {% } else { %}\n" +
			                                   "                          <span>{%=file.name%}</span>\n" +
			                                   "                      {% } %}\n" +
			                                   "                  </p>\n" +
			                                   "                  {% if (file.error) { %}\n" +
			                                   "                      <div><span class=\"label label-danger\">Error</span> {%=file.error%}</div>\n" +
			                                   "                  {% } %}\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  <span class=\"size\">{%=o.formatFileSize(file.size)%}</span>\n" +
			                                   "              </td>\n" +
			                                   "              <td>\n" +
			                                   "                  {% if (file.deleteUrl) { %}\n" +
			                                   "                      <button class=\"btn btn-danger delete\" data-type=\"{%=file.deleteType%}\" data-url=\"{%=file.deleteUrl%}\"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{\"withCredentials\":true}'{% } %}>\n" +
			                                   "                          <i class=\"glyphicon glyphicon-trash\"></i>\n" +
			                                   "                          <span>Delete</span>\n" +
			                                   "                      </button>\n" +
			                                   "                      <input type=\"checkbox\" name=\"delete\" value=\"1\" class=\"toggle\">\n" +
			                                   "                  {% } else { %}\n" +
			                                   "                      <button class=\"btn btn-warning cancel\">\n" +
			                                   "                          <i class=\"glyphicon glyphicon-ban-circle\"></i>\n" +
			                                   "                          <span>Cancel</span>\n" +
			                                   "                      </button>\n" +
			                                   "                  {% } %}\n" +
			                                   "              </td>\n" +
			                                   "          </tr>\n" +
			                                   "      {% } %}\n" +
			                                   "    </script>").setTextOnly(true));
		}
		return page;
	}
	
	@Override
	public boolean enabled()
	{
		return BlueImpFileUploadPageConfigurator.enabled;
	}
	
	private void registerModules()
	{
		JQueryPageConfigurator.setRequired(true);
	}
	
	/**
	 * The gallery div
	 *
	 * @return
	 */
	public BlueImpGallery<?> getGalleryDiv()
	{
		if (galleryDiv == null)
		{
			setGalleryDiv(new BlueImpGallery<>().addAttribute("aria-modal", "true")
			                                    .addAttribute("aria-label", "image gallery")
			                                    .addAttribute("role", "dialog")
			                                    .addAttribute("data-filter", ":even")
			);
		}
		return galleryDiv;
	}
	
	/**
	 * Sets the gallery div
	 *
	 * @param galleryDiv
	 */
	public void setGalleryDiv(BlueImpGallery<?> galleryDiv)
	{
		this.galleryDiv = galleryDiv;
		if (this.galleryDiv != null)
		{
			this.galleryDiv.setID("blueimp-gallery");
			this.galleryDiv.setControls(true);
		}
	}
}
