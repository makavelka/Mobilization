package com.example.mobilization;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static byte[] bitmapToByte(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
