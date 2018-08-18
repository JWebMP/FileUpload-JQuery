package com.jwebmp.plugins.blueimp.fileupload;

import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadDefaultOptions;
import org.junit.jupiter.api.Test;

class BlueImpFileUploadDefaultOptionsTest
{

	@Test
	public void testOptions()
	{
		BlueImpFileUploadDefaultOptions opts = new BlueImpFileUploadDefaultOptions();
		System.out.println(opts.toString());
	}
}
