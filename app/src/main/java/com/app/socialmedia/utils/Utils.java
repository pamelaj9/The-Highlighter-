package com.app.socialmedia.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Utils {
    public static Bitmap getBitmapWithTransparentBG(Bitmap srcBitmap, int mColor) {
        Bitmap result = srcBitmap.copy(Bitmap.Config.ARGB_8888, true);
        int nWidth = result.getWidth();
        int nHeight = result.getHeight();
        for (int y = 0; y < nHeight; ++y)
            for (int x = 0; x < nWidth; ++x) {
                int nPixelColor = result.getPixel(x, y);
                if (nPixelColor == Color.WHITE)
                    result.setPixel(x, y, mColor);
            }
        return result;
    }

    public static Bitmap bitmap(Context context, int res, int color) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),res);
        return getBitmapWithTransparentBG(bitmap,color);
    }
}
