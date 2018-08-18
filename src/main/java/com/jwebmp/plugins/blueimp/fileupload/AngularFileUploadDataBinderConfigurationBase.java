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

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.angular.AngularReferenceBase;
import com.jwebmp.core.base.angular.services.IAngularConfiguration;
import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadDefaultOptions;

public class AngularFileUploadDataBinderConfigurationBase
		extends AngularReferenceBase
		implements IAngularConfiguration<AngularFileUploadDataBinderConfigurationBase>
{
	public AngularFileUploadDataBinderConfigurationBase()
	{
		super("AngularFileUploadDataBinderConfigurationBase");
	}

	@Override
	public String renderFunction()
	{
		String output;

		StringBuilder sb = FileTemplates.getFileTemplate(BlueImpFileUploadDefaultOptions.class, "FileUploadDefaultOptionsTemplate", "FileUploadDefaultOptionsTemplate");
		output = sb.toString()
		           .replace("FileUploadDefaultOptionsTemplateOptions", AngularFileUploadPageConfigurator.getDefaultOptions()
		                                                                                                .toString());

		return output;
	}
}
