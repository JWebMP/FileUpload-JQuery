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

package com.jwebmp.plugins.blueimp.fileupload.parts;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.attributes.GlobalAttributes;
import com.jwebmp.plugins.blueimp.fileupload.angular.BlueImpFileDestroyController;

import javax.validation.constraints.NotNull;

import static com.jwebmp.core.utilities.StaticStrings.*;

/**
 * The table listing the files available for upload/download
 *
 * @param <J>
 */
public class BlueImpFileUploadTable<J extends BlueImpFileUploadTable<J>>
		extends Table
{

	private static final String NgRepeat = "data-ng-repeat";
	private static final String NgClass = "data-ng-class";
	private static final String NgSwitch = "data-ng-switch";
	private static final String NgOn = "data-on";
	private static final String NgSwitchWhen = "data-ng-switch-when";
	private static final String NgSwitchDefault = "data-ng-switch-default";
	private static final String NgDownload = "download";
	private static final String NgDataGallery = "data-gallery";
	private static final String NgDataSource = "data-ng-source";
	private static final String NgDataFileUploadPreview = "data-file-upload-preview";
	private static final String NgDataHref = "data-ng-href";
	private static final String NgTitle = "title";
	private static final String NgClick = "data-ng-click";
	private static final String NgHide = "data-ng-hide";
	private static final String FileName = "{{file.name}}";
	private static final String FileUrl = "{{file.url}}";

	private final TableRow<?> fileRow;
	private boolean showThumbnail = true;
	private boolean showFileName = true;
	private boolean showFileSize = true;

	private ComponentHierarchyBase fileProgressBarComponent;

	private boolean showFileButtons = true;

	private String errorClass = "error";
	private String startButtonClass;
	private String cancelButtonClass;
	private String deleteButtonClass;

	private String startButtonIcon;
	private String cancelButtonIcon;
	private String deleteButtonIcon;

	private String startButtonText = "Start";
	private String cancelButtonText = "Cancel";
	private String deleteButtonText = "Delete";

	public BlueImpFileUploadTable()
	{
		fileRow = new TableRow<>();
		fileRow.addAttribute(NgRepeat, "file in queue");
		fileRow.addAttribute(NgClass, "{'processing': file.$processing()}");

		addClass("ng-cloak");
		add(fileRow);
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			if (isShowThumbnail())
			{
				TableCell<?> cell = new TableCell<>();
				cell.addAttribute(NgSwitch, STRING_EMPTY);
				cell.addAttribute(NgOn, "!!file.thumbnailUrl");

				Div sp = new DivSimple<>();
				sp.addClass("preview");
				sp.addAttribute(NgSwitchWhen, "true");

				Link downloadLink = new Link();
				downloadLink.addAttribute(NgDataHref, FileUrl);
				downloadLink.addAttribute(GlobalAttributes.Title, FileName);
				downloadLink.addAttribute(NgDownload, FileName);
				downloadLink.addAttribute(NgDataGallery, STRING_EMPTY);

				Image previewImage = new Image<>(null);
				previewImage.addAttribute(NgDataSource, "{{file.thumbnailUrl}}");

				downloadLink.add(previewImage);
				sp.add(downloadLink);

				cell.add(sp);

				Div previewSwitchDiv = new DivSimple();
				previewSwitchDiv.addClass("preview");
				previewSwitchDiv.addAttribute(NgSwitchDefault, STRING_EMPTY);
				previewSwitchDiv.addAttribute(NgDataFileUploadPreview, "file");

				cell.add(previewSwitchDiv);

				fileRow.add(cell);
			}

			if (isShowFileName())
			{
				TableCell cell = new TableCell();

				Paragraph nameParagraph = new Paragraph();
				nameParagraph.addClass("name");
				nameParagraph.addAttribute(NgSwitch, STRING_EMPTY);
				nameParagraph.addAttribute(NgOn, "!!file.url");

				Span trueSwitchSpan = new Span();
				trueSwitchSpan.addAttribute(NgSwitchWhen, "true");
				trueSwitchSpan.addAttribute(NgSwitch, STRING_EMPTY);
				trueSwitchSpan.addAttribute(NgOn, "!!file.thumbnailUrl");

				Span defaultSwitchSpan = new Span();
				defaultSwitchSpan.addAttribute(NgSwitchDefault, STRING_EMPTY);
				defaultSwitchSpan.setText(FileName);

				Link spanSwitchTrue = new Link();
				spanSwitchTrue.addAttribute(NgSwitchWhen, "true");
				spanSwitchTrue.addAttribute(NgDataHref, FileUrl);
				spanSwitchTrue.addAttribute(NgTitle, FileName);
				spanSwitchTrue.addAttribute(NgDownload, FileName);
				spanSwitchTrue.addAttribute(NgDataGallery, STRING_EMPTY);
				spanSwitchTrue.setText(FileName);

				Link spanSwitchDefault = new Link();
				spanSwitchDefault.addAttribute(NgSwitchDefault, STRING_EMPTY);
				spanSwitchDefault.addAttribute(NgDataHref, FileUrl);
				spanSwitchDefault.addAttribute(NgTitle, FileName);
				spanSwitchDefault.addAttribute(NgDownload, FileName);
				spanSwitchDefault.setText(FileName);

				trueSwitchSpan.add(spanSwitchTrue);
				trueSwitchSpan.add(spanSwitchDefault);

				nameParagraph.add(trueSwitchSpan);
				nameParagraph.add(defaultSwitchSpan);

				cell.add(nameParagraph);

				Strong errorText = new Strong("{{file.error}}");
				errorText.addAttribute("data-ng-show", "file.error");
				errorText.addClass(getErrorClass());

				cell.add(errorText);

				fileRow.add(cell);
			}

			if (isShowFileSize())
			{
				TableCell cell = new TableCell();
				Paragraph fileSize = new Paragraph();
				fileSize.addClass("size");
				fileSize.setText("{{file.size | formatFileSize}}");

				cell.add(fileSize);

				if (fileProgressBarComponent != null)
				{
					fileProgressBarComponent.addAttribute(NgClass, "{pending: 'in'}[file.$state()]");
					fileProgressBarComponent.addAttribute("data-file-upload-progress", "file.$progress()");
					cell.add(fileProgressBarComponent);
				}

				fileRow.add(cell);
			}

			if (isShowFileButtons())
			{
				TableCell cell = new TableCell();

				Button startButton = new Button<>();
				if (startButtonClass != null)
				{
					startButton.addClass(startButtonClass);
				}
				startButton.addClass("start");
				startButton.addAttribute(NgClick, "file.$submit()");
				startButton.addAttribute(NgHide, "!file.$submit || options.autoUpload");
				startButton.addAttribute("data-ng-disabled", "file.$state() == 'pending' || file.$state() == 'rejected'");

				if (startButtonIcon != null)
				{
					Italic i = new Italic<>().addClass(startButtonIcon);
					startButton.add(i);
				}
				Span startButtonTxt = new Span(startButtonText);
				startButton.add(startButtonTxt);

				cell.add(startButton);

				Button cancelButton = new Button<>();
				if (cancelButtonClass != null)
				{
					cancelButton.addClass(cancelButtonClass);
				}
				cancelButton.addClass("cancel");
				cancelButton.addAttribute(NgClick, "file.$cancel()");
				cancelButton.addAttribute(NgHide, "!file.$cancel");

				if (cancelButtonIcon != null)
				{
					Italic i = new Italic<>().addClass(cancelButtonIcon);
					cancelButton.add(i);
				}
				Span cancelButtonTxt = new Span(cancelButtonText);
				cancelButton.add(cancelButtonTxt);

				cell.add(cancelButton);

				Button deleteButton = new Button<>();
				if (deleteButtonClass != null)
				{
					deleteButton.addClass(deleteButtonClass);
				}
				deleteButton.addAttribute("data-ng-controller", BlueImpFileDestroyController.FILE_DESTROY_CONTROLLER_NAME);
				deleteButton.addClass("destroy");
				deleteButton.addAttribute(NgClick, "file.$destroy()");
				deleteButton.addAttribute(NgHide, "!file.$destroy");

				if (deleteButtonIcon != null)
				{
					Italic i = new Italic<>().addClass(deleteButtonIcon);
					deleteButton.add(i);
				}
				Span deleteButtonTxt = new Span(deleteButtonText);
				deleteButton.add(deleteButtonTxt);

				cell.add(deleteButton);

				fileRow.add(cell);
			}

		}
		super.preConfigure();
	}

	public boolean isShowThumbnail()
	{
		return showThumbnail;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setShowThumbnail(boolean showThumbnail)
	{
		this.showThumbnail = showThumbnail;
		return (J) this;
	}

	public boolean isShowFileName()
	{
		return showFileName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setShowFileName(boolean showFileName)
	{
		this.showFileName = showFileName;
		return (J) this;
	}

	public String getErrorClass()
	{
		return errorClass;
	}

	public boolean isShowFileSize()
	{
		return showFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setShowFileSize(boolean showFileSize)
	{
		this.showFileSize = showFileSize;
		return (J) this;
	}

	public boolean isShowFileButtons()
	{
		return showFileButtons;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setShowFileButtons(boolean showFileButtons)
	{
		this.showFileButtons = showFileButtons;
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setErrorClass(String errorClass)
	{
		this.errorClass = errorClass;
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J applyWidthStyleForProgress(ComponentHierarchyBase componentToDynamicallySetWidth)
	{
		componentToDynamicallySetWidth.addAttribute("data-ng-style", "{width: num + '%'}");
		return (J) this;
	}

	public TableRow<?> getFileRow()
	{
		return fileRow;
	}

	public ComponentHierarchyBase getFileProgressBarComponent()
	{
		return fileProgressBarComponent;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setFileProgressBarComponent(ComponentHierarchyBase fileProgressBarComponent)
	{
		this.fileProgressBarComponent = fileProgressBarComponent;
		return (J) this;
	}

	public String getStartButtonClass()
	{
		return startButtonClass;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setStartButtonClass(String startButtonClass)
	{
		this.startButtonClass = startButtonClass;
		return (J) this;
	}

	public String getCancelButtonClass()
	{
		return cancelButtonClass;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setCancelButtonClass(String cancelButtonClass)
	{
		this.cancelButtonClass = cancelButtonClass;
		return (J) this;
	}

	public String getDeleteButtonClass()
	{
		return deleteButtonClass;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDeleteButtonClass(String deleteButtonClass)
	{
		this.deleteButtonClass = deleteButtonClass;
		return (J) this;
	}

	public String getStartButtonIcon()
	{
		return startButtonIcon;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setStartButtonIcon(String startButtonIcon)
	{
		this.startButtonIcon = startButtonIcon;
		return (J) this;
	}

	public String getCancelButtonIcon()
	{
		return cancelButtonIcon;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setCancelButtonIcon(String cancelButtonIcon)
	{
		this.cancelButtonIcon = cancelButtonIcon;
		return (J) this;
	}

	public String getDeleteButtonIcon()
	{
		return deleteButtonIcon;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDeleteButtonIcon(String deleteButtonIcon)
	{
		this.deleteButtonIcon = deleteButtonIcon;
		return (J) this;
	}

	public String getStartButtonText()
	{
		return startButtonText;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setStartButtonText(String startButtonText)
	{
		this.startButtonText = startButtonText;
		return (J) this;
	}

	public String getCancelButtonText()
	{
		return cancelButtonText;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setCancelButtonText(String cancelButtonText)
	{
		this.cancelButtonText = cancelButtonText;
		return (J) this;
	}

	public String getDeleteButtonText()
	{
		return deleteButtonText;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDeleteButtonText(String deleteButtonText)
	{
		this.deleteButtonText = deleteButtonText;
		return (J) this;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}
}
