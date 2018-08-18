/*
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jwebmp.plugins.blueimp.fileupload.parts.json;

import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;

import java.io.InputStream;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@JsonAutoDetect(fieldVisibility = ANY,
		getterVisibility = NONE,
		setterVisibility = NONE)
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFile
		extends JavaScriptPart<JsonFile>
{

	private String name;
	private String type;

	private Long size;
	@JsonProperty("url")
	private String downloadUrl;
	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;
	@JsonProperty("deleteUrl")
	private String deleteUrl;
	@JsonProperty("deleteType")
	private String deleteType;

	@JsonIgnore
	private boolean deletable = true;
	@JsonIgnore
	private transient InputStream content;

	public JsonFile()
	{
		deleteType = "GET";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getSize()
	{
		return size;
	}

	public void setSize(Long size)
	{
		this.size = size;
	}

	public String getDownloadUrl()
	{
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl)
	{
		this.downloadUrl = downloadUrl;
	}

	public String getThumbnailUrl()
	{
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl)
	{
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl()
	{
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl)
	{
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType()
	{
		return deleteType;
	}

	public void setDeleteType(String deleteType)
	{
		this.deleteType = deleteType;
	}

	public boolean isDeletable()
	{
		return deletable;
	}

	public void setDeletable(boolean deletable)
	{
		this.deletable = deletable;
	}

	public InputStream getContent()
	{
		return content;
	}

	public void setContent(InputStream content)
	{
		this.content = content;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
