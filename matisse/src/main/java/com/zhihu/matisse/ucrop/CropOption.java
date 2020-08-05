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

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class CropOption implements Parcelable {
    private Uri mDestination;
    private float mAspectRatioX;
    private float mAspectRatioY;
    private int mMaxWidth;
    private int mMaxHeight;
    private int hideBottomControls;

    public CropOption(Uri destination) {
        this.mDestination = destination;
    }

    public static CropOption with(@NonNull Uri destination) {
        return new CropOption(destination);
    }

    public CropOption aspectRatio(float x, float y) {
        this.mAspectRatioX = x;
        this.mAspectRatioY = y;
        return this;
    }

    public CropOption useSourceImageAspectRatio() {
        this.mAspectRatioX = 0;
        this.mAspectRatioY = 0;
        return this;
    }

    public CropOption withMaxResultSize(int width, int height) {
        this.mMaxWidth = width;
        this.mMaxHeight = height;
        return this;
    }

    public CropOption hideBottomControls(boolean hide) {
        this.hideBottomControls = hide ? 1 : 0;
        return this;
    }

    public boolean isHideBottomControls() {
        return this.hideBottomControls == 1;
    }


    public float getAspectRatioX() {
        return mAspectRatioX;
    }

    public float getAspectRatioY() {
        return mAspectRatioY;
    }

    public int getMaxHeight() {
        return mMaxHeight;
    }

    public int getMaxWidth() {
        return mMaxWidth;
    }

    public Uri getDestination() {
        return mDestination;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDestination, flags);
        dest.writeFloat(this.mAspectRatioX);
        dest.writeFloat(this.mAspectRatioY);
        dest.writeInt(this.mMaxWidth);
        dest.writeInt(this.mMaxHeight);
        dest.writeInt(this.hideBottomControls);
    }

    CropOption(Parcel in) {
        this.mDestination = in.readParcelable(Uri.class.getClassLoader());
        this.mAspectRatioX = in.readFloat();
        this.mAspectRatioY = in.readFloat();
        this.mMaxWidth = in.readInt();
        this.mMaxHeight = in.readInt();
        this.hideBottomControls = in.readInt();
    }

    public static final Creator<CropOption> CREATOR = new Creator<CropOption>() {
        @Override
        public CropOption createFromParcel(Parcel source) {
            return new CropOption(source);
        }

        @Override
        public CropOption[] newArray(int size) {
            return new CropOption[size];
        }
    };
}