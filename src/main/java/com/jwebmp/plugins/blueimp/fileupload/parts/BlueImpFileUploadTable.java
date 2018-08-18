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
		fileRow.addAttribute("data-ng-repeat", "file in queue");
		fileRow.addAttribute("data-ng-class", "{'processing': file.$processing()}");

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
				cell.addAttribute("data-ng-switch", STRING_EMPTY);
				cell.addAttribute("data-on", "!!file.thumbnailUrl");

				Div sp = new DivSimple<>();
				sp.addClass("preview");
				sp.addAttribute("data-ng-switch-when", "true");

				Link downloadLink = new Link();
				downloadLink.addAttribute("data-ng-href", "{{file.url}}");
				downloadLink.addAttribute(GlobalAttributes.Title, "{{file.name}}");
				downloadLink.addAttribute("download", "{{file.name}}");
				downloadLink.addAttribute("data-gallery", STRING_EMPTY);

				Image previewImage = new Image<>(null);
				previewImage.addAttribute("data-ng-source", "{{file.thumbnailUrl}}");

				downloadLink.add(previewImage);
				sp.add(downloadLink);

				cell.add(sp);

				Div previewSwitchDiv = new DivSimple();
				previewSwitchDiv.addClass("preview");
				previewSwitchDiv.addAttribute("data-ng-switch-default", STRING_EMPTY);
				previewSwitchDiv.addAttribute("data-file-upload-preview", "file");

				cell.add(previewSwitchDiv);

				fileRow.add(cell);
			}

			if (isShowFileName())
			{
				TableCell cell = new TableCell();

				Paragraph nameParagraph = new Paragraph();
				nameParagraph.addClass("name");
				nameParagraph.addAttribute("data-ng-switch", STRING_EMPTY);
				nameParagraph.addAttribute("data-on", "!!file.url");

				Span trueSwitchSpan = new Span();
				trueSwitchSpan.addAttribute("data-ng-switch-when", "true");
				trueSwitchSpan.addAttribute("data-ng-switch", STRING_EMPTY);
				trueSwitchSpan.addAttribute("data-on", "!!file.thumbnailUrl");

				Span defaultSwitchSpan = new Span();
				defaultSwitchSpan.addAttribute("data-ng-switch-default", STRING_EMPTY);
				defaultSwitchSpan.setText("{{file.name}}");

				Link spanSwitchTrue = new Link();
				spanSwitchTrue.addAttribute("data-ng-switch-when", "true");
				spanSwitchTrue.addAttribute("data-ng-href", "{{file.url}}");
				spanSwitchTrue.addAttribute("title", "{{file.name}}");
				spanSwitchTrue.addAttribute("download", "{{file.name}}");
				spanSwitchTrue.addAttribute("data-gallery", STRING_EMPTY);
				spanSwitchTrue.setText("{{file.name}}");

				Link spanSwitchDefault = new Link();
				spanSwitchDefault.addAttribute("data-ng-switch-default", STRING_EMPTY);
				spanSwitchDefault.addAttribute("data-ng-href", "{{file.url}}");
				spanSwitchDefault.addAttribute("title", "{{file.name}}");
				spanSwitchDefault.addAttribute("download", "{{file.name}}");
				spanSwitchDefault.setText("{{file.name}}");

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
					fileProgressBarComponent.addAttribute("data-ng-class", "{pending: 'in'}[file.$state()]");
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
				startButton.addAttribute("data-ng-click", "file.$submit()");
				startButton.addAttribute("data-ng-hide", "!file.$submit || options.autoUpload");
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
				cancelButton.addAttribute("data-ng-click", "file.$cancel()");
				cancelButton.addAttribute("data-ng-hide", "!file.$cancel");

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
				deleteButton.addAttribute("data-ng-click", "file.$destroy()");
				deleteButton.addAttribute("data-ng-hide", "!file.$destroy");

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
