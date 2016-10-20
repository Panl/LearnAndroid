package com.smartalk.learnandroid;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by panl on 16/3/15.
 * contact panlei106@gmail.com
 */
public class DisplayUtil {

    private static final float DENSITY = Resources.getSystem()
            .getDisplayMetrics().density;

    public static int dp2px(int dp) {
        return Math.round(dp * DENSITY);
    }


    public static int px2dp(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
