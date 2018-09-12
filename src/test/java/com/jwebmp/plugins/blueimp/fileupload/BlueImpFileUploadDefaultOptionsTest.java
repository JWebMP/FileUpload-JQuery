package com.jwebmp.plugins.blueimp.fileupload;

import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadDefaultOptions;
import org.junit.jupiter.api.Test;

public class BlueImpFileUploadDefaultOptionsTest
{

	@Test
	public void testOptions()
	{
		BlueImpFileUploadDefaultOptions opts = new BlueImpFileUploadDefaultOptions();
		System.out.println(opts.toString());

		AngularFileUploadTestPage testPage = new AngularFileUploadTestPage();
		System.out.println(testPage.toString(0));
	}
}
