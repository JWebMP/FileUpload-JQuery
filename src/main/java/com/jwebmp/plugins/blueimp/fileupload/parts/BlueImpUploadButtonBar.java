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

import com.jwebmp.core.base.html.Button;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Italic;
import com.jwebmp.core.base.html.Span;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;
import com.jwebmp.core.base.html.inputs.InputFileType;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import jakarta.validation.constraints.NotNull;

import static com.guicedee.services.jsonrepresentation.json.StaticStrings.STRING_EMPTY;
import static com.jwebmp.interception.services.StaticStrings.HTML_TAB;


public class BlueImpUploadButtonBar<J extends BlueImpUploadButtonBar<J>>
        extends DivSimple<J>
{

    private DivSimple<?> buttonBarContainerDiv;
    private InputFileType<?> fileInput = new InputFileType<>();

    public BlueImpUploadButtonBar()
    {
        addClass("row fileupload-buttonbar");
        buttonBarContainerDiv = new DivSimple<>().addClass("col_lg_7");
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
        //span.addAttribute("ng-class", "{disabled: disabled}");

        if (iconClass != null && !iconClass.isEmpty())
        {
            Italic i = new Italic();
            i.addClass(iconClass);
            i.addClass("ml-1");
            span.add(i);
        }

        if (label != null && !label.isEmpty())
        {
            Span labelSpan = new Span();
            labelSpan.setText(label);
            span.add(labelSpan);
        }

        fileInput.setName("files[]");
        //	fileInput.addAttribute("ng-disabled", "disabled");

        if (multiple)
        {
            addAttribute("multiple", STRING_EMPTY);
        }
        span.add(fileInput);
        span.addClass("mr-1");
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
        b.addClass("mr-1");
        //	b.addAttribute("data-ng-click", "submit()");

        if (iconClass != null && !iconClass.isEmpty())
        {
            Italic i = new Italic();
            i.addClass(iconClass);
            i.addClass("mr-1");
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
        b.addClass("mr-1");
        //	b.addAttribute("data-ng-click", "cancel()");

        if (iconClass != null && !iconClass.isEmpty())
        {
            Italic i = new Italic();
            i.addClass(iconClass);
            i.addClass("mr-1");
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
    public J addDeleteSelected(String displayClass, String iconClass, String label)
    {
        Button b = new Button<>();
        if (displayClass != null && !displayClass.isEmpty())
        {
            b.addClass(displayClass);
        }
        b.addClass("delete");
        b.addClass("mr-1");

        //	b.addAttribute("data-ng-click", "delete()");

        if (iconClass != null && !iconClass.isEmpty())
        {
            Italic i = new Italic();
            i.addClass(iconClass);
            i.addClass("mr-1");
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
    public J addDeleteCheckbox(String displayClass, String iconClass, String label)
    {
        InputCheckBoxType<?> b = new InputCheckBoxType<>();
        if (displayClass != null && !displayClass.isEmpty())
        {
            b.addClass(displayClass);
        }
        b.addClass("toggle");
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
    public J addGlobalProgressState()
    {
        DivSimple<?> globalProcessState = new DivSimple<>().addClass("col-lg-5")
                                                           .addClass("fileupload-progress")
                                                           .addClass("fade");
        DivSimple<?> progressSection = new DivSimple<>();
        progressSection.addClass("progress")
                       .addClass("progress-striped")
                       .addClass("active")
                       .addAttribute("role", "progressbar")
                       .addAttribute("aria-valuemin", "0")
                       .addAttribute("aria-valuemax", "100");
        DivSimple<?> progressBar = new DivSimple<>();
        progressBar.addClass("progress-bar")
                   .addClass("progress-bar-success")
                   .addStyle("width", "0%");

        progressSection.add(progressBar);
        globalProcessState.add(progressSection);

        DivSimple<?> progressExtended = new DivSimple<>();
        progressExtended.addClass("progress-extended")
                        .setText(HTML_TAB);

        globalProcessState.add(progressExtended);

        add(globalProcessState);
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

    public InputFileType<?> getFileInput()
    {
        return fileInput;
    }
}
