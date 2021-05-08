package com.jwebmp.plugins.blueimp.fileupload.parts;

import com.jwebmp.core.Feature;
import com.jwebmp.core.base.html.*;
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
	private final String collectorName;
	private BlueImpFileUploadOptions<?> options = new BlueImpFileUploadOptions<>();
	
	public BlueImpUploadForm()
	{
		this("");
	}
	
	public BlueImpUploadForm(String collectorName)
	{
		this.collectorName = collectorName;
		addAttribute("action", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?uploadCollectorName=" + collectorName);
		addAttribute("method", "POST");
		addAttribute("enc-type", "multipart/form-data");
		BlueImpUploadForm<?> me = this;
		addFeature(new Feature("blueimpfileupload")
		{
			
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
		bar.addAttribute("role", "presentation");
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
			addAttribute("action", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?uploadCollectorName=" + collectorName);
			var par = findParent(Form.class);
			if (par != null)
			{
				par.addAttribute("action", "/" + BlueImpFileUploadBinderGuiceSiteBinder.BLUEIMP_FILEUPLOAD_SERVLETURL + "?uploadCollectorName=" + collectorName);
				par.addAttribute("method", "POST");
				par.addAttribute("enc-type", "multipart/form-data");
				
				setID(par.getID());
				par.add(new Paragraph<>("<script id=\"template-upload\" class=\"template-upload\" type=\"text/x-tmpl\">\n" +
				                    "      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
				                    "          <tr class=\"template-upload {%=o.options.loadImageFileTypes.test(file.type)?' image':''%}\">\n" +
				                    "              <td>\n" +
				                    "                  <span class=\"preview\"></span>\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  <p class=\"name\">{%=file.name%}</p>\n" +
				                    "                  <strong class=\"error text-danger\"></strong>\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  <p class=\"size\">Processing...</p>\n" +
				                    "                  <div class=\"progress progress-striped active\" role=\"progressbar\" aria-valuemin=\"0\" aria-valuemax=\"100\" aria-valuenow=\"0\"><div class=\"progress-bar progress-bar-success\" style=\"width:0%;\"></div></div>\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  {% if (!o.options.autoUpload && o.options.edit && o.options.loadImageFileTypes.test(file.type)) { %}\n" +
				                    "                    <button class=\"btn btn-success edit\" data-index=\"{%=i%}\" disabled>\n" +
				                    "                        <i class=\"glyphicon glyphicon-edit\"></i>\n" +
				                    "                        <span>Edit</span>\n" +
				                    "                    </button>\n" +
				                    "                  {% } %}\n" +
				                    "                  {% if (!i && !o.options.autoUpload) { %}\n" +
				                    "                      <button class=\"btn btn-primary start\" disabled>\n" +
				                    "                          <i class=\"glyphicon glyphicon-upload\"></i>\n" +
				                    "                          <span>Start</span>\n" +
				                    "                      </button>\n" +
				                    "                  {% } %}\n" +
				                    "                  {% if (!i) { %}\n" +
				                    "                      <button class=\"btn btn-warning cancel\">\n" +
				                    "                          <i class=\"glyphicon glyphicon-ban-circle\"></i>\n" +
				                    "                          <span>Cancel</span>\n" +
				                    "                      </button>\n" +
				                    "                  {% } %}\n" +
				                    "              </td>\n" +
				                    "          </tr>\n" +
				                    "      {% } %}\n" +
				                    "    </script>\n" +
				                    "    <!-- The template to display files available for download -->\n" +
				                    "    <script id=\"template-download\" class=\"template-download\"  type=\"text/x-tmpl\">\n" +
				                    "      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
				                    "          <tr class=\"template-download {%=file.thumbnailUrl?' image':''%}\">\n" +
				                    "              <td>\n" +
				                    "                  <span class=\"preview\">\n" +
				                    "                      {% if (file.thumbnailUrl) { %}\n" +
				                    "                          <a href=\"{%=file.url%}\" title=\"{%=file.name%}\" download=\"{%=file.name%}\" data-gallery><img src=\"{%=file.thumbnailUrl%}\"></a>\n" +
				                    "                      {% } %}\n" +
				                    "                  </span>\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  <p class=\"name\">\n" +
				                    "                      {% if (file.url) { %}\n" +
				                    "                          <a href=\"{%=file.url%}\" title=\"{%=file.name%}\" download=\"{%=file.name%}\" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>\n" +
				                    "                      {% } else { %}\n" +
				                    "                          <span>{%=file.name%}</span>\n" +
				                    "                      {% } %}\n" +
				                    "                  </p>\n" +
				                    "                  {% if (file.error) { %}\n" +
				                    "                      <div><span class=\"label label-danger\">Error</span> {%=file.error%}</div>\n" +
				                    "                  {% } %}\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  <span class=\"size\">{%=o.formatFileSize(file.size)%}</span>\n" +
				                    "              </td>\n" +
				                    "              <td>\n" +
				                    "                  {% if (file.deleteUrl) { %}\n" +
				                    "                      <button class=\"btn btn-danger delete\" data-type=\"{%=file.deleteType%}\" data-url=\"{%=file.deleteUrl%}\"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{\"withCredentials\":true}'{% } %}>\n" +
				                    "                          <i class=\"glyphicon glyphicon-trash\"></i>\n" +
				                    "                          <span>Delete</span>\n" +
				                    "                      </button>\n" +
				                    "                      <input type=\"checkbox\" name=\"delete\" value=\"1\" class=\"toggle\">\n" +
				                    "                  {% } else { %}\n" +
				                    "                      <button class=\"btn btn-warning cancel\">\n" +
				                    "                          <i class=\"glyphicon glyphicon-ban-circle\"></i>\n" +
				                    "                          <span>Cancel</span>\n" +
				                    "                      </button>\n" +
				                    "                  {% } %}\n" +
				                    "              </td>\n" +
				                    "          </tr>\n" +
				                    "      {% } %}\n" +
				                    "    </script>").setTextOnly(true));
				
				
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
