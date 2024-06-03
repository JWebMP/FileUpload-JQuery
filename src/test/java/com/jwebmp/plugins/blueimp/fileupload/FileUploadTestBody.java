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

import com.jwebmp.core.Page;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.blueimp.fileupload.parts.BlueImpUploadButtonBar;
import com.jwebmp.plugins.blueimp.fileupload.parts.BlueImpUploadForm;
import org.junit.jupiter.api.Test;

public class FileUploadTestBody
        extends DivSimple<FileUploadTestBody>
{
    @Test
    public void testBodyOutput()
    {
        BlueImpUploadForm<?> fileUpload = new BlueImpUploadForm<>();
        BlueImpUploadButtonBar<?> blueImpUploadButtonBar = fileUpload.addButtonBar()
                                                                     .addAddButton("btn btn-success", "fas fa-plus", "Add", false)
                                                                     .addStartButton("btn btn-primary", "fas fa-upload", "Start Upload")
                                                                     .addCancelButton("btn btn-warning", "fas fa-do-not-enter", "Cancel")
                                                                     .addDeleteSelected("btn btn-danger", "fas fa-times", "Delete Selected")
                                                                     .addDeleteCheckbox("", "fa fas-cancel", "Delete Selected")
                                                                     .addGlobalFileProcessingState()
                                                                     .addGlobalProgressState();

        fileUpload.getOptions()
                  .setUrl("/" + "blueimpangularfileupload")
                  .setMaxFileSize(99999999)
                  .setMaxChunkSize(204800)
                  .setDisableImageResize("/Android(?!.*Chrome)|Opera/" + ".test(window.navigator.userAgent)")
                  .setAcceptFileTypes("/(\\.|\\/)(gif|jpe?g|png)$/i");


        DivSimple<?> d = new DivSimple<>();
        d.add(fileUpload);

        System.out.println("Renders in div");
        System.out.println(d.renderJavascript());

        Page<?> p = new Page<>();
        p.getOptions()
         .setTitle("t");
        p.getOptions()
         .setDynamicRender(false);


        p.add(d);

        System.out.println(p.toString(0));
    }

}
