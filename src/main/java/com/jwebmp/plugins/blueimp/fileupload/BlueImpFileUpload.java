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

import com.jwebmp.core.plugins.ComponentInformation;
import com.jwebmp.plugins.blueimp.fileupload.parts.BlueImpUploadForm;

@ComponentInformation(name = "Angular File Upload",
		description = "File Upload widget with multiple file selection, drag&drop support, progress bar, validation and preview images, audio and video for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads. ",
		url = "https://github.com/GedMarc/JWebSwing-AngularFileUpload")
public class BlueImpFileUpload<J extends BlueImpFileUpload<J>>
		extends BlueImpUploadForm<J>
{
	/**
	 * Configures the page for this component
	 */
	public BlueImpFileUpload()
	{
		//Nothing Needed
	}

}
