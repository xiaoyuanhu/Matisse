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
import android.net.Uri;

import androidx.annotation.NonNull;


public interface ICrop {

    /***
     * start crop operation.
     *
     * @param cropConfig  {@link CropOption}
     * @param uri        the uri of media.
     * @param requestCode request code for the crop.
     */
    void onStartCrop(Activity activity, @NonNull CropOption cropConfig,
                     @NonNull Uri uri, int requestCode);


    /**
     * get the result of cropping.
     *
     * @param resultCode the code in {@link android.app.Activity#onActivityResult(int, int, Intent)}
     * @param data       the data intent
     * @return the cropped image uri.
     */
    Uri onCropFinish(int resultCode, Intent data);
}
