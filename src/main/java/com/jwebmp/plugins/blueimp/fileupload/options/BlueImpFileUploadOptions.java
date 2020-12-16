/*
 * Copyright (C) 2017 GedMarc
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
package com.jwebmp.plugins.blueimp.fileupload.options;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;

import jakarta.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * All the options
 * <p>
 *
 * @author GedMarc
 * @version 1.0
 * 		<p>
 * 		<p>
 * @since Mar 4, 2015
 */
@JsonAutoDetect(fieldVisibility = ANY,
		getterVisibility = NONE,
		setterVisibility = NONE)
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class BlueImpFileUploadOptions<J extends BlueImpFileUploadOptions<J>>
		extends JavaScriptPart<J>
{
	private String url;
	@JsonProperty("type")
	private String urlType;
	private String dataType;
	private String dropZone;
	private String pasteZone;

	private String fileInput;
	private Boolean replaceFileInput;
	private String paramName;
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	private transient Charset formAcceptCharset;

	private Boolean singleFileUploads;
	private Integer limitMultiFileUploads;
	private Integer limitMultiFileUploadSize;
	private Integer limitMultiFileUploadSizeOverhead;
	private Boolean sequentialUploads;
	private Integer limitConcurrentUploads;
	private Boolean forceIframeTransport;
	private String initialIframeSrc;
	private String redirect;
	private String redirectParamName;
	private String postMessage;
	private Boolean multipart;
	private Integer maxChunkSize;
	private Integer uploadedBytes;
	private Boolean recalculateProgress;
	private Integer progressInterval;
	private Integer bitrateInterval;
	private Boolean autoUpload;

	private Boolean disableImageHead;
	private Boolean disableExif;
	private Boolean disableExifThumbnail;
	private Boolean disableExifSub;
	private Boolean disableExifGps;
	private Boolean disableImageMetaDataLoad;
	private Boolean disableImageMetaDataSave;
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	private Pattern loadImageFileTypes;
	private Integer loadImageMaxFileSize;
	private Boolean loadImageNoRevoke;
	private Boolean disableImageLoad;
	private Integer imageMaxWidth;
	private Integer imageMaxHeight;
	private Integer imageMinHeight;
	private Integer imageMinWidth;
	private Boolean imageCrop;
	private Integer imageOrientation;
	private Boolean imageForceResize;
	@JsonRawValue
	private String disableImageResize;
	private Float imageQuality;
	/**
	 * The image MIME Type
	 */
	private String imageType;
	private Integer previewMaxWidth;
	private Integer previewMaxHeight;
	private Integer previewMinWidth;
	private Integer previewMinHeight;
	private Boolean previewCrop;
	private Integer previewOrientation;
	private Boolean previewThumbnail;
	private Boolean previewCanvas;
	private String imagePreviewName;
	private Boolean disableImagePreview;
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	private Pattern loadAudioFileTypes;
	private Integer loadAudioMaxFileSize;
	private String audioPreviewName;
	private Boolean disableAudioPreview;
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	private Pattern loadVideoFileTypes;
	private Integer loadVideoMaxFileSize;
	private String videoPreviewName;
	private Boolean disableVideoPreview;

	@JsonSerialize(contentUsing = ToStringSerializer.class)
	private Pattern acceptFileTypes;
	private Integer maxFileSize;
	private Integer minFileSize;
	private Integer maxNumberOfFiles;
	private Boolean disableValidation;

	/**
	 * The options for the component
	 */
	public BlueImpFileUploadOptions()
	{
		//Not needed for construction
	}

	public String getUrl()
	{
		return url;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setUrl(String url)
	{
		this.url = url;
		return (J) this;
	}

	public String getUrlType()
	{
		return urlType;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setUrlType(String urlType)
	{
		this.urlType = urlType;
		return (J) this;
	}

	public String getDataType()
	{
		return dataType;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDataType(String dataType)
	{
		this.dataType = dataType;
		return (J) this;
	}

	public String getDropZone()
	{
		return dropZone;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDropZone(String dropZone)
	{
		this.dropZone = dropZone;
		return (J) this;
	}

	public String getPasteZone()
	{
		return pasteZone;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPasteZone(String pasteZone)
	{
		this.pasteZone = pasteZone;
		return (J) this;
	}

	public String getFileInput()
	{
		return fileInput;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setFileInput(String fileInput)
	{
		this.fileInput = fileInput;
		return (J) this;
	}

	public Boolean getReplaceFileInput()
	{
		return replaceFileInput;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setReplaceFileInput(Boolean replaceFileInput)
	{
		this.replaceFileInput = replaceFileInput;
		return (J) this;
	}

	public String getParamName()
	{
		return paramName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setParamName(String paramName)
	{
		this.paramName = paramName;
		return (J) this;
	}

	public Charset getFormAcceptCharset()
	{
		return formAcceptCharset;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setFormAcceptCharset(Charset formAcceptCharset)
	{
		this.formAcceptCharset = formAcceptCharset;
		return (J) this;
	}

	public Boolean getSingleFileUploads()
	{
		return singleFileUploads;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setSingleFileUploads(Boolean singleFileUploads)
	{
		this.singleFileUploads = singleFileUploads;
		return (J) this;
	}

	public Integer getLimitMultiFileUploads()
	{
		return limitMultiFileUploads;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLimitMultiFileUploads(Integer limitMultiFileUploads)
	{
		this.limitMultiFileUploads = limitMultiFileUploads;
		return (J) this;
	}

	public Integer getLimitMultiFileUploadSize()
	{
		return limitMultiFileUploadSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLimitMultiFileUploadSize(Integer limitMultiFileUploadSize)
	{
		this.limitMultiFileUploadSize = limitMultiFileUploadSize;
		return (J) this;
	}

	public Integer getLimitMultiFileUploadSizeOverhead()
	{
		return limitMultiFileUploadSizeOverhead;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLimitMultiFileUploadSizeOverhead(Integer limitMultiFileUploadSizeOverhead)
	{
		this.limitMultiFileUploadSizeOverhead = limitMultiFileUploadSizeOverhead;
		return (J) this;
	}

	public Boolean getSequentialUploads()
	{
		return sequentialUploads;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setSequentialUploads(Boolean sequentialUploads)
	{
		this.sequentialUploads = sequentialUploads;
		return (J) this;
	}

	public Integer getLimitConcurrentUploads()
	{
		return limitConcurrentUploads;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLimitConcurrentUploads(Integer limitConcurrentUploads)
	{
		this.limitConcurrentUploads = limitConcurrentUploads;
		return (J) this;
	}

	public Boolean getForceIframeTransport()
	{
		return forceIframeTransport;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setForceIframeTransport(Boolean forceIframeTransport)
	{
		this.forceIframeTransport = forceIframeTransport;
		return (J) this;
	}

	public String getInitialIframeSrc()
	{
		return initialIframeSrc;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setInitialIframeSrc(String initialIframeSrc)
	{
		this.initialIframeSrc = initialIframeSrc;
		return (J) this;
	}

	public String getRedirect()
	{
		return redirect;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setRedirect(String redirect)
	{
		this.redirect = redirect;
		return (J) this;
	}

	public String getRedirectParamName()
	{
		return redirectParamName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setRedirectParamName(String redirectParamName)
	{
		this.redirectParamName = redirectParamName;
		return (J) this;
	}

	public String getPostMessage()
	{
		return postMessage;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPostMessage(String postMessage)
	{
		this.postMessage = postMessage;
		return (J) this;
	}

	public Boolean getMultipart()
	{
		return multipart;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setMultipart(Boolean multipart)
	{
		this.multipart = multipart;
		return (J) this;
	}

	public Integer getMaxChunkSize()
	{
		return maxChunkSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setMaxChunkSize(Integer maxChunkSize)
	{
		this.maxChunkSize = maxChunkSize;
		return (J) this;
	}

	public Integer getUploadedBytes()
	{
		return uploadedBytes;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setUploadedBytes(Integer uploadedBytes)
	{
		this.uploadedBytes = uploadedBytes;
		return (J) this;
	}

	public Boolean getRecalculateProgress()
	{
		return recalculateProgress;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setRecalculateProgress(Boolean recalculateProgress)
	{
		this.recalculateProgress = recalculateProgress;
		return (J) this;
	}

	public Integer getProgressInterval()
	{
		return progressInterval;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setProgressInterval(Integer progressInterval)
	{
		this.progressInterval = progressInterval;
		return (J) this;
	}

	public Integer getBitrateInterval()
	{
		return bitrateInterval;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setBitrateInterval(Integer bitrateInterval)
	{
		this.bitrateInterval = bitrateInterval;
		return (J) this;
	}

	public Boolean getAutoUpload()
	{
		return autoUpload;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setAutoUpload(Boolean autoUpload)
	{
		this.autoUpload = autoUpload;
		return (J) this;
	}

	public Boolean getDisableImageHead()
	{
		return disableImageHead;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImageHead(Boolean disableImageHead)
	{
		this.disableImageHead = disableImageHead;
		return (J) this;
	}

	public Boolean getDisableExif()
	{
		return disableExif;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableExif(Boolean disableExif)
	{
		this.disableExif = disableExif;
		return (J) this;
	}

	public Boolean getDisableExifThumbnail()
	{
		return disableExifThumbnail;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableExifThumbnail(Boolean disableExifThumbnail)
	{
		this.disableExifThumbnail = disableExifThumbnail;
		return (J) this;
	}

	public Boolean getDisableExifSub()
	{
		return disableExifSub;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableExifSub(Boolean disableExifSub)
	{
		this.disableExifSub = disableExifSub;
		return (J) this;
	}

	public Boolean getDisableExifGps()
	{
		return disableExifGps;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableExifGps(Boolean disableExifGps)
	{
		this.disableExifGps = disableExifGps;
		return (J) this;
	}

	public Boolean getDisableImageMetaDataLoad()
	{
		return disableImageMetaDataLoad;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImageMetaDataLoad(Boolean disableImageMetaDataLoad)
	{
		this.disableImageMetaDataLoad = disableImageMetaDataLoad;
		return (J) this;
	}

	public Boolean getDisableImageMetaDataSave()
	{
		return disableImageMetaDataSave;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImageMetaDataSave(Boolean disableImageMetaDataSave)
	{
		this.disableImageMetaDataSave = disableImageMetaDataSave;
		return (J) this;
	}

	public Pattern getLoadImageFileTypes()
	{
		return loadImageFileTypes;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadImageFileTypes(Pattern loadImageFileTypes)
	{
		this.loadImageFileTypes = loadImageFileTypes;
		return (J) this;
	}

	public Integer getLoadImageMaxFileSize()
	{
		return loadImageMaxFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadImageMaxFileSize(Integer loadImageMaxFileSize)
	{
		this.loadImageMaxFileSize = loadImageMaxFileSize;
		return (J) this;
	}

	public Boolean getLoadImageNoRevoke()
	{
		return loadImageNoRevoke;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadImageNoRevoke(Boolean loadImageNoRevoke)
	{
		this.loadImageNoRevoke = loadImageNoRevoke;
		return (J) this;
	}

	public Boolean getDisableImageLoad()
	{
		return disableImageLoad;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImageLoad(Boolean disableImageLoad)
	{
		this.disableImageLoad = disableImageLoad;
		return (J) this;
	}

	public Integer getImageMaxWidth()
	{
		return imageMaxWidth;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageMaxWidth(Integer imageMaxWidth)
	{
		this.imageMaxWidth = imageMaxWidth;
		return (J) this;
	}

	public Integer getImageMaxHeight()
	{
		return imageMaxHeight;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageMaxHeight(Integer imageMaxHeight)
	{
		this.imageMaxHeight = imageMaxHeight;
		return (J) this;
	}

	public Integer getImageMinHeight()
	{
		return imageMinHeight;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageMinHeight(Integer imageMinHeight)
	{
		this.imageMinHeight = imageMinHeight;
		return (J) this;
	}

	public Integer getImageMinWidth()
	{
		return imageMinWidth;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageMinWidth(Integer imageMinWidth)
	{
		this.imageMinWidth = imageMinWidth;
		return (J) this;
	}

	public Boolean getImageCrop()
	{
		return imageCrop;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageCrop(Boolean imageCrop)
	{
		this.imageCrop = imageCrop;
		return (J) this;
	}

	public Integer getImageOrientation()
	{
		return imageOrientation;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageOrientation(Integer imageOrientation)
	{
		this.imageOrientation = imageOrientation;
		return (J) this;
	}

	public Boolean getImageForceResize()
	{
		return imageForceResize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageForceResize(Boolean imageForceResize)
	{
		this.imageForceResize = imageForceResize;
		return (J) this;
	}

	public String getDisableImageResize()
	{
		return disableImageResize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImageResize(String disableImageResize)
	{
		this.disableImageResize = disableImageResize;
		return (J) this;
	}

	public Float getImageQuality()
	{
		return imageQuality;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageQuality(Float imageQuality)
	{
		this.imageQuality = imageQuality;
		return (J) this;
	}

	public String getImageType()
	{
		return imageType;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImageType(String imageType)
	{
		this.imageType = imageType;
		return (J) this;
	}

	public Integer getPreviewMaxWidth()
	{
		return previewMaxWidth;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewMaxWidth(Integer previewMaxWidth)
	{
		this.previewMaxWidth = previewMaxWidth;
		return (J) this;
	}

	public Integer getPreviewMaxHeight()
	{
		return previewMaxHeight;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewMaxHeight(Integer previewMaxHeight)
	{
		this.previewMaxHeight = previewMaxHeight;
		return (J) this;
	}

	public Integer getPreviewMinWidth()
	{
		return previewMinWidth;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewMinWidth(Integer previewMinWidth)
	{
		this.previewMinWidth = previewMinWidth;
		return (J) this;
	}

	public Integer getPreviewMinHeight()
	{
		return previewMinHeight;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewMinHeight(Integer previewMinHeight)
	{
		this.previewMinHeight = previewMinHeight;
		return (J) this;
	}

	public Boolean getPreviewCrop()
	{
		return previewCrop;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewCrop(Boolean previewCrop)
	{
		this.previewCrop = previewCrop;
		return (J) this;
	}

	public Integer getPreviewOrientation()
	{
		return previewOrientation;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewOrientation(Integer previewOrientation)
	{
		this.previewOrientation = previewOrientation;
		return (J) this;
	}

	public Boolean getPreviewThumbnail()
	{
		return previewThumbnail;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewThumbnail(Boolean previewThumbnail)
	{
		this.previewThumbnail = previewThumbnail;
		return (J) this;
	}

	public Boolean getPreviewCanvas()
	{
		return previewCanvas;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setPreviewCanvas(Boolean previewCanvas)
	{
		this.previewCanvas = previewCanvas;
		return (J) this;
	}

	public String getImagePreviewName()
	{
		return imagePreviewName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setImagePreviewName(String imagePreviewName)
	{
		this.imagePreviewName = imagePreviewName;
		return (J) this;
	}

	public Boolean getDisableImagePreview()
	{
		return disableImagePreview;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableImagePreview(Boolean disableImagePreview)
	{
		this.disableImagePreview = disableImagePreview;
		return (J) this;
	}

	public Pattern getLoadAudioFileTypes()
	{
		return loadAudioFileTypes;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadAudioFileTypes(Pattern loadAudioFileTypes)
	{
		this.loadAudioFileTypes = loadAudioFileTypes;
		return (J) this;
	}

	public Integer getLoadAudioMaxFileSize()
	{
		return loadAudioMaxFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadAudioMaxFileSize(Integer loadAudioMaxFileSize)
	{
		this.loadAudioMaxFileSize = loadAudioMaxFileSize;
		return (J) this;
	}

	public String getAudioPreviewName()
	{
		return audioPreviewName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setAudioPreviewName(String audioPreviewName)
	{
		this.audioPreviewName = audioPreviewName;
		return (J) this;
	}

	public Boolean getDisableAudioPreview()
	{
		return disableAudioPreview;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableAudioPreview(Boolean disableAudioPreview)
	{
		this.disableAudioPreview = disableAudioPreview;
		return (J) this;
	}

	public Pattern getLoadVideoFileTypes()
	{
		return loadVideoFileTypes;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadVideoFileTypes(Pattern loadVideoFileTypes)
	{
		this.loadVideoFileTypes = loadVideoFileTypes;
		return (J) this;
	}

	public Integer getLoadVideoMaxFileSize()
	{
		return loadVideoMaxFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLoadVideoMaxFileSize(Integer loadVideoMaxFileSize)
	{
		this.loadVideoMaxFileSize = loadVideoMaxFileSize;
		return (J) this;
	}

	public String getVideoPreviewName()
	{
		return videoPreviewName;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setVideoPreviewName(String videoPreviewName)
	{
		this.videoPreviewName = videoPreviewName;
		return (J) this;
	}

	public Boolean getDisableVideoPreview()
	{
		return disableVideoPreview;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableVideoPreview(Boolean disableVideoPreview)
	{
		this.disableVideoPreview = disableVideoPreview;
		return (J) this;
	}

	public Pattern getAcceptFileTypes()
	{
		return acceptFileTypes;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setAcceptFileTypes(Pattern acceptFileTypes)
	{
		this.acceptFileTypes = acceptFileTypes;
		return (J) this;
	}

	public Integer getMaxFileSize()
	{
		return maxFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setMaxFileSize(Integer maxFileSize)
	{
		this.maxFileSize = maxFileSize;
		return (J) this;
	}

	public Integer getMinFileSize()
	{
		return minFileSize;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setMinFileSize(Integer minFileSize)
	{
		this.minFileSize = minFileSize;
		return (J) this;
	}

	public Integer getMaxNumberOfFiles()
	{
		return maxNumberOfFiles;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setMaxNumberOfFiles(Integer maxNumberOfFiles)
	{
		this.maxNumberOfFiles = maxNumberOfFiles;
		return (J) this;
	}

	public Boolean getDisableValidation()
	{
		return disableValidation;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setDisableValidation(Boolean disableValidation)
	{
		this.disableValidation = disableValidation;
		return (J) this;
	}
}
