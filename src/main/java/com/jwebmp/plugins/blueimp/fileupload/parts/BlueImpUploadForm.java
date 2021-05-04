package com.jwebmp.plugins.blueimp.fileupload.parts;

import com.jwebmp.core.Feature;
import com.jwebmp.core.base.html.Form;
import com.jwebmp.core.base.html.TableBodyGroup;
import com.jwebmp.core.base.html.interfaces.children.FormChildren;
import com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadBinderGuiceSiteBinder;
import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadOptions;

/**
 * The file upload form used as target for the file upload widget
 *
 * @param <J>
 */
public class BlueImpUploadForm<J extends BlueImpUploadForm<J>>
		extends Form<J> implements FormChildren
{
	private BlueImpFileUploadOptions<?> options = new BlueImpFileUploadOptions<>();

	public BlueImpUploadForm()
	{
		addAttribute("action", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL);
		addAttribute("method", "POST");
		addAttribute("enc-type", "multipart/form-data");
		BlueImpUploadForm<?> me = this;
		addFeature(new Feature("blueimpfileupload"){
			
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery(me.getJQueryID() + "fileupload(" + me.getOptions() + ");");
			}
		});
	}

	public BlueImpUploadButtonBar<?> addButtonBar()
	{
		BlueImpUploadButtonBar<?> bar = new BlueImpUploadButtonBar<>();
		add(bar);
		return bar;
	}

	public BlueImpFileUploadTable<?> addDisplayTable()
	{
		BlueImpFileUploadTable<?> bar = new BlueImpFileUploadTable<>();
		bar.addClass("table table-striped");
		bar.addAttribute("role","presentation");
		bar.add(new TableBodyGroup<>().addClass("files"));
		add(bar);
		return bar;
	}
	
	@Override
	public BlueImpFileUploadOptions<?> getOptions()
	{
		if (options == null)
		{
			options = new BlueImpFileUploadOptions<>();
		}
		return options;
	}
	
	@Override
	public void init()
	{
		if (getParent() != null)
		{
			var par =findParent(Form.class);
			if (par != null)
			{
				par.addAttribute("action", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL);
				par.addAttribute("method", "POST");
				par.addAttribute("enc-type", "multipart/form-data");
				
				setID(par.getID());
			}
		}
		super.init();
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}
}
