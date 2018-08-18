package com.jwebmp.plugins.blueimp.fileupload.intercepters;

import com.jwebmp.guicedinjection.pairing.Pair;

import java.io.InputStream;

/**
 * Specifies the get file interceptor
 */
@FunctionalInterface
public interface OnGetFileInterceptor
{

	/**
	 * Returns the stream for the the output filename
	 *
	 * @param filename
	 *
	 * @return
	 */
	Pair<String, InputStream> onGetFile(String filename);
}
