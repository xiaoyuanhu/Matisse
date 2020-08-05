/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.zhihu.matisse.ucrop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.model.AspectRatio;


public class MatisseCrop implements ICrop {
    private static final MatisseCrop INSTANCE = new MatisseCrop();

    public static MatisseCrop getInstance() {
        return INSTANCE;
    }

    private  MatisseCrop() {
    }

    @Override
    public void onStartCrop(Activity activity, @NonNull CropOption cropConfig, @NonNull Uri uri, int requestCode) {
        UCrop.Options crop = new UCrop.Options();
        // do not copy exif information to crop pictures
        // because png do not have exif and png is not Distinguishable
        AspectRatio ratio=new AspectRatio((int)cropConfig.getAspectRatioX()+":"+(int)cropConfig.getAspectRatioY(),cropConfig.getAspectRatioX(),cropConfig.getAspectRatioY());
        crop.setAspectRatioOptions(0,ratio);
        crop.setHideBottomControls(true);
        crop.setCompressionFormat(Bitmap.CompressFormat.PNG);
        crop.withMaxResultSize(cropConfig.getMaxWidth(), cropConfig.getMaxHeight());
        crop.withAspectRatio(cropConfig.getAspectRatioX(),cropConfig.getAspectRatioY());
        crop.setToolbarColor(Color.parseColor("#1D282C"));
        crop.setStatusBarColor(Color.parseColor("#1D282C"));
        crop.setToolbarWidgetColor(Color.parseColor("#FFFFFF"));
        UCrop.of(uri, cropConfig.getDestination())
                .withOptions(crop)
                .start(activity, requestCode);
    }

    @Override
    public Uri onCropFinish(int resultCode, Intent data) {
        if (data == null) {
            return null;
        }
        Throwable throwable = UCrop.getError(data);
        if (throwable != null) {
            return null;
        }
        return UCrop.getOutput(data);
    }
}
