package com.jwebmp.plugins.blueimp.fileupload.implementations;

import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class BlueImpFileUploadExclusionsModule
		implements IGuiceScanModuleExclusions<BlueImpFileUploadExclusionsModule>
{
	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.plugins.blueimp.fileupload");
		strings.add("org.apache.tika.core");
		strings.add("org.apache.commons.io");
		strings.add("commons.fileupload");
		return strings;
	}
}
