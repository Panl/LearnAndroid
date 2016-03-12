package com.smartalk.learnandroid.imageloader.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by panl on 16/3/11.
 * contact panlei106@gmail.com
 */
public class ImageResizer {
    private  final String TAG = getClass().getSimpleName();

    public ImageResizer(){

    }

    public Bitmap decodeSampledBitmapFromResource(Resources resources,int resId, int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,resId,options);

        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }

    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fileDescriptor,int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor,null,options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor,null,options);
    }
    public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        if (reqWidth == 0 || reqHeight == 0){
            return 1;
        }
        final int height = options.outHeight;
        final int width = options.outWidth;
        int sampleSize = Math.min(width/reqWidth,height/reqHeight);
        if (sampleSize < 1){
            return 1;
        }
        return sampleSize;
    }
}
