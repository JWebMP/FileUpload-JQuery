/*
 * Copyright (C) 2017 Marc Magon
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
import com.jwebmp.core.base.angular.AngularPageConfigurator;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.jquery.JQueryPageConfigurator;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadDefaultOptions;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGallery;

import javax.validation.constraints.NotNull;

@PluginInformation(pluginName = "Angular File Upload",
		pluginUniqueName = "jwebswing-angular-file-upload",
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
		pluginWikiUrl = "https://github.com/GedMarc/AngularFileUploadPageConfigurator/wiki",
		pluginOriginalHomepage = "https://github.com/blueimp/jQuery-File-Upload",
		pluginDownloadUrl = "https://sourceforge.net/projects/jwebswing/files/plugins/AngularFileUploadPageConfigurator.jar/download",
		pluginIconUrl = "",
		pluginIconImageUrl = "",
		pluginLastUpdatedDate = "2017/09/18")
@Singleton
public class AngularFileUploadPageConfigurator
		implements IPageConfigurator
{
	/**
	 * If this configurator is enabled
	 */
	private static boolean enabled = true;
	private static BlueImpFileUploadDefaultOptions defaultOptions;
	private static boolean renderJqueryUI;
	/**
	 * The div for the gallery
	 */
	private BlueImpGallery galleryDiv;

	/**
	 * Configures the page for this component
	 */
	public AngularFileUploadPageConfigurator()
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
		return AngularFileUploadPageConfigurator.enabled;
	}

	/**
	 * Method setEnabled sets the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @param mustEnable
	 * 		the enabled of this AngularAnimatedChangePageConfigurator object.
	 */
	public static void setEnabled(boolean mustEnable)
	{
		AngularFileUploadPageConfigurator.enabled = mustEnable;
	}

	/**
	 * Returns the default options applied with this object
	 *
	 * @return
	 */
	@NotNull
	public static BlueImpFileUploadDefaultOptions getDefaultOptions()
	{
		if (AngularFileUploadPageConfigurator.defaultOptions == null)
		{
			AngularFileUploadPageConfigurator.defaultOptions = new BlueImpFileUploadDefaultOptions();
		}
		return AngularFileUploadPageConfigurator.defaultOptions;
	}

	/**
	 * Sets the default options applied
	 *
	 * @param defaultOptions
	 */
	public static void setDefaultOptions(BlueImpFileUploadDefaultOptions defaultOptions)
	{
		AngularFileUploadPageConfigurator.defaultOptions = defaultOptions;
	}

	public static boolean isRenderJqueryUI()
	{
		return AngularFileUploadPageConfigurator.renderJqueryUI;
	}

	public static void setRenderJqueryUI(boolean renderJqueryUI)
	{
		AngularFileUploadPageConfigurator.renderJqueryUI = renderJqueryUI;
	}

	@NotNull
	@Override
	@SuppressWarnings("unchecked")
	public Page configure(Page page)
	{
		if (!page.isConfigured())
		{
			registerModules(page);

			page.getBody()
			    .add(getGalleryDiv());

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.TemplatesReference.getJavaScriptReference()
			                                                                             .setSortOrder(200));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.LoadImageJCropReference.getJavaScriptReference()
			                                                                                  .setSortOrder(201));
			page.getBody()
			    .addCssReference(AngularFileUploadReferencePool.LoadImageJCropReference.getCssReference()
			                                                                           .setSortOrder(202));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.LoadImageReference.getJavaScriptReference()
			                                                                             .setSortOrder(203));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.LoadImageMetaReference.getJavaScriptReference()
			                                                                                 .setSortOrder(204));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.LoadImageExifReference.getJavaScriptReference()
			                                                                                 .setSortOrder(205));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.LoadImageExifMapReference.getJavaScriptReference()
			                                                                                    .setSortOrder(206));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.CanvasToBlobReference.getJavaScriptReference()
			                                                                                .setSortOrder(207));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadIFrameTransportReference.getJavaScriptReference()
			                                                                                             .setSortOrder(217));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadReference.getJavaScriptReference()
			                                                                              .setSortOrder(218));
			page.getBody()
			    .addCssReference(AngularFileUploadReferencePool.FileUploadReference.getCssReference()
			                                                                       .setSortOrder(219));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadProcessReference.getJavaScriptReference()
			                                                                                     .setSortOrder(220));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadAudioReference.getJavaScriptReference()
			                                                                                   .setSortOrder(221));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadImageReference.getJavaScriptReference()
			                                                                                   .setSortOrder(222));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadValidateReference.getJavaScriptReference()
			                                                                                      .setSortOrder(223));

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadVideoReference.getJavaScriptReference()
			                                                                                   .setSortOrder(224));

			if (AngularFileUploadPageConfigurator.renderJqueryUI)
			{
				page.getBody()
				    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadUIReference.getJavaScriptReference()
				                                                                                .setSortOrder(225));
				page.getBody()
				    .addCssReference(AngularFileUploadReferencePool.FileUploadUIReference.getCssReference()
				                                                                         .setSortOrder(226));
			}

			page.getBody()
			    .addJavaScriptReference(AngularFileUploadReferencePool.FileUploadAngularReference.getJavaScriptReference()
			                                                                                     .setSortOrder(227));
		}
		return page;
	}

	@Override
	public boolean enabled()
	{
		return AngularFileUploadPageConfigurator.enabled;
	}

	private void registerModules(Page page)
	{
		JQueryPageConfigurator.setRequired(true);
		AngularPageConfigurator.setRequired(true);
	}

	/**
	 * The gallery div
	 *
	 * @return
	 */
	public BlueImpGallery getGalleryDiv()
	{
		if (galleryDiv == null)
		{
			setGalleryDiv(new BlueImpGallery());
		}
		return galleryDiv;
	}

	/**
	 * Sets the gallery div
	 *
	 * @param galleryDiv
	 */
	@SuppressWarnings("unchecked")
	public void setGalleryDiv(BlueImpGallery galleryDiv)
	{
		this.galleryDiv = galleryDiv;
		if (this.galleryDiv != null)
		{
			this.galleryDiv.setID("blueimp-gallery");
			this.galleryDiv.setControls(true);
		}
	}
}
