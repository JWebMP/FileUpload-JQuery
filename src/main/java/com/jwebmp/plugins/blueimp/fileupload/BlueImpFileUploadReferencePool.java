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
package com.jwebmp.plugins.blueimp.fileupload;

import com.jwebmp.core.base.angular.client.annotations.angularconfig.*;
import com.jwebmp.core.base.references.CSSReference;
import com.jwebmp.core.base.references.JavascriptReference;
import com.jwebmp.core.base.servlets.interfaces.ReferencePool;

/**
 * Default reference pool structure
 *
 * @author GedMarc
 * @since 20 Apr 2016
 */
@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-tmpl/js/tmpl.js",sortOrder = 800)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/vendor/jquery.Jcrop.js",sortOrder = 805)
@NgStyleSheet("app/blueimp/blueimpfileupload/blueimp-load-image/css/vendor/jquery.Jcrop.css")


@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/load-image.all.min.js",sortOrder = 810)

/*

@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/load-image-exif.js",sortOrder = 815)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/load-image-meta.js",sortOrder = 815)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/load-image-exif-map.js",sortOrder = 820)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-load-image/js/load-image.js",sortOrder = 825)
*/

@NgScript(value = "app/blueimp/blueimpfileupload/blueimp-canvas-to-blob/js/canvas-to-blob.js",sortOrder = 830)

@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload.js",sortOrder = 835)
@NgStyleSheet("app/blueimp/blueimpfileupload/blueimpfileupload/css/jquery.fileupload.css")


//@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-audio.js",sortOrder = 840)

/*

@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-image.js",sortOrder = 845)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-process.js",sortOrder = 850)


@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-validate.js",sortOrder = 855)
*/


@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.iframe-transport.js",sortOrder = 860)

/*@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-video.js",sortOrder = 865)*/

//@NgScript(value = "app/blueimp/blueimpfileupload/blueimpfileupload/js/jquery.fileupload-ui.js",sortOrder = 870)



public enum BlueImpFileUploadReferencePool
{
//	FileUploadAngularReference(new JavascriptReference("FileUploadAngularReference", 9.22, "bower_components/blueimpfileupload/js/jquery.fileupload-angular.js"), null);
}
