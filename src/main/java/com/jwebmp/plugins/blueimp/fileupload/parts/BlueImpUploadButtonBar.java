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

package com.jwebmp.plugins.blueimp.fileupload.parts;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.inputs.InputFileType;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;

import javax.validation.constraints.NotNull;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class BlueImpUploadButtonBar<J extends BlueImpUploadButtonBar<J>>
		extends DivSimple<J>
{

	private DivSimple<?> buttonBarContainerDiv;

	public BlueImpUploadButtonBar()
	{
		addClass("fileupload-buttonbar");
		buttonBarContainerDiv = new DivSimple<>();
		add(buttonBarContainerDiv);
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J addAddButton(String displayClass, String iconClass, String label, boolean multiple)
	{
		Span<IComponentHierarchyBase, ?, ?> span = new Span();

		if (displayClass != null && !displayClass.isEmpty())
		{
			span.addClass(displayClass);
		}

		span.addClass("fileinput-button");
		span.addAttribute("ng-class", "{disabled: disabled}");

		if (iconClass != null && !iconClass.isEmpty())
		{
			Italic i = new Italic();
			i.addClass(iconClass);
			span.add(i);
		}

		if (label != null && !label.isEmpty())
		{
			Span labelSpan = new Span();
			labelSpan.setText(label);
			span.add(labelSpan);
		}

		InputFileType<?> fileInput = new InputFileType<>();
		fileInput.setName("files[]");
		fileInput.addAttribute("ng-disabled", "disabled");

		if (multiple)
		{
			addAttribute("multiple", STRING_EMPTY);
		}
		span.add(fileInput);

		buttonBarContainerDiv.add(span);
		return (J) this;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J addStartButton(String displayClass, String iconClass, String label)
	{
		Button b = new Button<>();
		if (displayClass != null && !displayClass.isEmpty())
		{
			b.addClass(displayClass);
		}
		b.addClass("start");

		b.addAttribute("data-ng-click", "submit()");

		if (iconClass != null && !iconClass.isEmpty())
		{
			Italic i = new Italic();
			i.addClass(iconClass);
			b.add(i);
		}
		if (label != null && !label.isEmpty())
		{
			Span span = new Span();
			span.setText(label);
			b.add(span);
		}
		buttonBarContainerDiv.add(b);

		return (J) this;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J addCancelButton(String displayClass, String iconClass, String label)
	{
		Button b = new Button<>();
		if (displayClass != null && !displayClass.isEmpty())
		{
			b.addClass(displayClass);
		}
		b.addClass("cancel");

		b.addAttribute("data-ng-click", "cancel()");

		if (iconClass != null && !iconClass.isEmpty())
		{
			Italic i = new Italic();
			i.addClass(iconClass);
			b.add(i);
		}
		if (label != null && !label.isEmpty())
		{
			Span span = new Span();
			span.setText(label);
			b.add(span);
		}
		buttonBarContainerDiv.add(b);

		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J addGlobalFileProcessingState()
	{
		Span sp = new Span();
		sp.addClass("fileupload-process");
		buttonBarContainerDiv.add(sp);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J addGlobalProgressState(Div divToApplyTo, ComponentHierarchyBase progressBarContainer, ComponentHierarchyBase progressBar)
	{
		divToApplyTo.addAttribute("data-ng-class", "{in: active()}");
		progressBarContainer.addAttribute("data-file-upload-progress", "progress()");
		progressBar.addAttribute("data-ng-style", "{width: num + '%'}");

		divToApplyTo.add(new DivSimple<>().addClass("progress-extended")
		                                  .setText(HTML_TAB));
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
