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
import com.jwebmp.core.base.html.Form;
import com.jwebmp.core.base.html.interfaces.children.BodyChildren;
import com.jwebmp.plugins.blueimp.fileupload.parts.*;

public class AngularFileUploadTestPage
		extends DivSimple<AngularFileUploadTestPage>
{
	
	public static void main(String[] args)
	{
		System.out.println("Building ");
		System.out.println(new AngularFileUploadTestPage().toString(0));
		//System.out.println(new DivSimple<>().add(new BlueImpUploadForm()).toString(0));
		BlueImpUploadForm<?> fileUpload = new BlueImpUploadForm<>();
		BlueImpUploadButtonBar<?> blueImpUploadButtonBar = fileUpload.addButtonBar()
		                                                    .addAddButton("btn btn-success", "fas fa-plus", "Add", false)
		                                                    .addStartButton("btn btn-primary", "fas fa-upload", "Start Upload")
		                                                    .addCancelButton("btn btn-warning", "fas fa-do-not-enter", "Cancel")
		                                                    .addDeleteSelected("btn btn-danger", "fas fa-times", "Delete Selected")
		                                                    .addDeleteCheckbox("", "fa fas-cancel", "Delete Selected")
		                                                    .addGlobalFileProcessingState()
		                                                    .addGlobalProgressState();
		;
		fileUpload.addDisplayTable();
		System.out.println("HJere");
		System.out.println(fileUpload.toString(0));
		Form f = new Form();
		f.add(fileUpload);
		System.out.println("ASdfsad");
		System.out.println(f.toString(0));
		
		DivSimple<?> d = new DivSimple<>();
		d.add(fileUpload);
		
		System.out.println("Renders in div");
		System.out.println(d.renderJavascript());
		
		
	}
	
	public AngularFileUploadTestPage()
	{
		BlueImpUploadForm<?> fileUpload = new BlueImpUploadForm();
		/*BlueImpUploadButtonBar bar = fileUpload.addButtonBar();
		BlueImpFileUploadTable table = fileUpload.addDisplayTable();

		bar.addAddButton("btn btn-success", "glyphicon glyphicon-plus", "Add Files...", true);
		bar.addStartButton("btn btn-primary", "glyphicon glyphicon-upload", "Start Upload");
		bar.addCancelButton("btn btn-warning", "glyphicon glyphicon-ban-circle", "Cancel Upload");
		bar.addGlobalFileProcessingState();*/

		add(fileUpload);
	}
}
