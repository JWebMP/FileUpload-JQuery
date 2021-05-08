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

package com.jwebmp.plugins.blueimp.fileupload.intercepters;

import com.guicedee.guicedinjection.interfaces.IDefaultService;
import com.guicedee.guicedinjection.pairing.Pair;

import java.io.InputStream;

/**
 * Specifies the get file interceptor
 */
public interface OnGetFileDownloadInterceptor<J extends OnGetFileDownloadInterceptor<J>> extends IDefaultService<J>
{
	/**
	 * The name of the uploader to use - identifies which interceptor to use
	 *
	 * @return
	 */
	default String name()
	{
		return "";
	}
	/**
	 * Returns the stream for the the output filename
	 *
	 * @param filename
	 *
	 * @return
	 */
	Pair<String, InputStream> onGetFileDownload(String filename);
}
