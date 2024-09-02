package com.jwebmp.plugins.blueimp.fileupload.parts;

import com.jwebmp.core.base.angular.client.annotations.structures.NgField;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.html.Form;
import com.jwebmp.core.base.html.TableBodyGroup;
import com.jwebmp.core.base.html.interfaces.children.FormChildren;
import com.jwebmp.plugins.blueimp.fileupload.options.BlueImpFileUploadOptions;

import java.util.List;

/**
 * The file upload form used as target for the file upload widget
 *
 * @param <J>
 */

@NgField("files : any = [];")
public class BlueImpUploadForm<J extends BlueImpUploadForm<J>>
        extends Form<J> implements FormChildren, INgComponent<J>
{
    private final String collectorName;
    private boolean uploadTemplate = true;
    private boolean downloadTemplate = true;
    private BlueImpFileUploadOptions<?> options = new BlueImpFileUploadOptions<>();

    public BlueImpUploadForm()
    {
        this("");
    }

    public BlueImpUploadForm(String collectorName)
    {
        this.collectorName = collectorName;
        addAttribute("action", "/" + "blueimpangularfileupload" + "?uploadCollectorName=" + collectorName);
        addAttribute("method", "POST");
        addAttribute("enctype", "multipart/form-data");
    }

    @Override
    public List<String> globalFields()
    {
        List<String> out = INgComponent.super.globalFields();
        out.add("declare var $:any;");
        return out;
    }

    @Override
    public List<String> onInit()
    {
        List<String> out = INgComponent.super.onInit();
        String requiredString = getJQueryID() + "fileupload(";
        requiredString += getOptions().toString();
        requiredString += ");" + getNewLine();
        out.add(requiredString);
        return out;
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
    protected void init()
    { /*if(uploadTemplate)
		add(new Paragraph<>("<!-- The template to display files available for upload -->\n" +
		                    "    <script id=\"template-upload\" type=\"text/x-tmpl\">\n" +
		                    "      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
		                    "          <tr class=\"template-upload fade {%=o.options.loadImageFileTypes.test(file.type)?' image':''%}\">\n" +
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
		                    "    </script>\n").setTextOnly(true));
		if (downloadTemplate)
		{
			add(new Paragraph<>(
					"    <!-- The template to display files available for download -->\n" +
					"    <script id=\"template-download\" type=\"text/x-tmpl\">\n" +
					"      {% for (var i=0, file; file=o.files[i]; i++) { %}\n" +
					"          <tr class=\"template-download fade{%=file.thumbnailUrl?' image':''%}\">\n" +
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
					"    </script>").setTextOnly(true));*/
        //	}
        super.init();
    }

    public String getCollectorName()
    {
        return collectorName;
    }

    public boolean isUploadTemplate()
    {
        return uploadTemplate;
    }

    public BlueImpUploadForm<J> setUploadTemplate(boolean uploadTemplate)
    {
        this.uploadTemplate = uploadTemplate;
        return this;
    }

    public boolean isDownloadTemplate()
    {
        return downloadTemplate;
    }

    public BlueImpUploadForm<J> setDownloadTemplate(boolean downloadTemplate)
    {
        this.downloadTemplate = downloadTemplate;
        return this;
    }

    public BlueImpUploadForm<J> setOptions(BlueImpFileUploadOptions<?> options)
    {
        this.options = options;
        return this;
    }
}
