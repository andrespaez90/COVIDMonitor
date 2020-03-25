package com.health.monitor.utils.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.media.ExifInterface.TAG_ORIENTATION;

public class ImageUtil {

    public static final String IMAGE_JPG = "image/jpeg";
    private static final String REDUCED_PREFIX = "reduced_";

    public static Drawable getDrawableVector(Context context, @DrawableRes int srcImage) {
        return AppCompatResources.getDrawable(context, srcImage);
    }

    /**
     * the final image is going to have the same name as the original file but with the reduced prefix
     */
    public static File resizeImage(@NonNull File file, int newWidth, int newHeight) {
        Bitmap scaledBitmap;
        File finalFile = null;

        try {
            Bitmap unscaledBitmap = ScalingUtilities.decodeFile(file.getAbsolutePath(), newWidth, newHeight, ScalingUtilities.ScalingLogic.FIT);

            if (!(unscaledBitmap.getWidth() <= newWidth && unscaledBitmap.getHeight() <= newHeight)) {
                scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, newWidth, newHeight, ScalingUtilities.ScalingLogic.FIT);

                finalFile = new File(file.getParentFile().getAbsolutePath(), REDUCED_PREFIX + file.getName().trim());

                FileOutputStream fileOutputStream;

                fileOutputStream = new FileOutputStream(finalFile);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

                scaledBitmap.recycle();

            } else {
                unscaledBitmap.recycle();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (finalFile == null) {
            finalFile = file;
        } else {
            saveImageOrientation(file, finalFile);

            file.delete();
        }

        return finalFile;

    }

    private static void saveImageOrientation(File file, File finalFile) {
        try {
            String orientation = new ExifInterface(file.getPath()).getAttribute(TAG_ORIENTATION);

            ExifInterface exifNew = new ExifInterface(finalFile.getPath());

            exifNew.setAttribute(TAG_ORIENTATION, orientation);

            exifNew.saveAttributes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
