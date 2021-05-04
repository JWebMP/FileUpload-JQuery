package com.jwebmp.plugins.blueimp.fileupload.intercepters;

import com.guicedee.guicedinjection.interfaces.IDefaultService;
import com.jwebmp.plugins.blueimp.fileupload.parts.json.JsonFile;

/**
 * Interceptor for when a file is uploaded.
 * <p>
 * Occurs after placed into the session storage (remember to clear session storage properties
 */
@FunctionalInterface
public interface OnFileUploadInterceptor<J extends OnFileUploadInterceptor<J>> extends IDefaultService<J>
{

	/**
	 * Occurs when a file has been successfully uploaded
	 *
	 * @param file
	 */
	void onUploadCompleted(JsonFile file);
}
