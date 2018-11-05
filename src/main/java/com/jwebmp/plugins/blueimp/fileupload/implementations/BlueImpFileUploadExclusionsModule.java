package com.jwebmp.plugins.blueimp.fileupload.implementations;

import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class BlueImpFileUploadExclusionsModule
		implements IGuiceScanModuleExclusions<BlueImpFileUploadExclusionsModule>,
				           IGuiceScanJarExclusions<BlueImpFileUploadExclusionsModule>
{

	@Override
	public @NotNull Set<String> excludeJars()
	{
		Set<String> strings = new HashSet<>();
		strings.add("jwebmp-blueimp-fileupload-*");
		strings.add("tika-core-*");
		strings.add("commons-io-*");
		strings.add("commons-fileupload-*");
		return strings;
	}

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
