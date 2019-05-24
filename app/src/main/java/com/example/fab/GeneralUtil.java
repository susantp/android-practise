package com.example.fab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class GeneralUtil {

    /**
     * to get bitmap from byteArray
     * @param byteArray
     * @return bitmap
     */
    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    /**
     * Blob to byteArray
     * @param bitmap
     * @return byte[]
     */
    public static byte[] getblob(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bArray = byteArrayOutputStream.toByteArray();
        return bArray;
    }
}
