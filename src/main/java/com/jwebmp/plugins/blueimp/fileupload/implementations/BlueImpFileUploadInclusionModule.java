package com.jwebmp.plugins.blueimp.fileupload.implementations;

import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
public class BlueImpFileUploadInclusionModule  implements IGuiceScanModuleInclusions<BlueImpFileUploadInclusionModule>
{
	@Override
	public @NotNull Set<String> includeModules()
	{
		Set<String> set = new HashSet<>();
		set.add("com.jwebmp.plugins.blueimp.fileupload");
		return set;
	}
}
