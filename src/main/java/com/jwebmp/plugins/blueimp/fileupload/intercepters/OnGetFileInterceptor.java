package com.jwebmp.plugins.blueimp.fileupload.intercepters;

import com.guicedee.guicedinjection.interfaces.IDefaultService;
import com.guicedee.guicedinjection.pairing.Pair;

import java.io.InputStream;

/**
 * Specifies the get file interceptor
 */
public interface OnGetFileInterceptor<J extends OnGetFileInterceptor<J>> extends IDefaultService<J>
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
	Pair<String, InputStream> onGetFile(String filename);
}
