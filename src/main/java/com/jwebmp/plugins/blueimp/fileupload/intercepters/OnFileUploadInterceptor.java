package com.jwebmp.plugins.blueimp.fileupload.intercepters;

import com.jwebmp.plugins.blueimp.fileupload.parts.json.JsonFile;

/**
 * Interceptor for when a file is uploaded.
 * <p>
 * Occurs after placed into the session storage (remember to clear session storage properties
 */
@FunctionalInterface
public interface OnFileUploadInterceptor
{

	/**
	 * Occurs when a file has been successfully uploaded
	 *
	 * @param file
	 */
	void onUploadCompleted(JsonFile file);
}
