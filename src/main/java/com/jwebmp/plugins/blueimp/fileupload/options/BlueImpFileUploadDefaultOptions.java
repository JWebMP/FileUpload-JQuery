package com.jwebmp.plugins.blueimp.fileupload.options;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * The default options settings for the blue imp file uploader
 *
 * @param <J>
 */
@JsonAutoDetect(fieldVisibility = ANY,
		getterVisibility = NONE,
		setterVisibility = NONE)
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlueImpFileUploadDefaultOptions<J extends BlueImpFileUploadDefaultOptions<J>>
		extends BlueImpFileUploadOptions<BlueImpFileUploadDefaultOptions<J>>
{

	/**
	 * Constructs a new instance of the default options
	 */
	public BlueImpFileUploadDefaultOptions()
	{
		setMaxFileSize(99999999);
		setMaxChunkSize(204800);
		setDisableImageResize("/Android(?!.*Chrome)|Opera/" + ".test(window.navigator.userAgent)");
	}
}
