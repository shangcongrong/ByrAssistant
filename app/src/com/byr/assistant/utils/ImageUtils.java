package com.byr.assistant.utils;

import android.graphics.*;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.Color.WHITE;
import static android.graphics.PorterDuff.Mode.DST_IN;

/**
 * User: orange
 * Date: 13-9-12
 * Time: 下午9:56
 */
public class ImageUtils {

    private static String TAG = "ImageUtils";

    /**
     * Get a bitmap from the image path
     *
     * @param imagePath
     * @return
     */
    public static Bitmap getBitmap(final String imagePath) {
        return getBitmap(imagePath, 1);
    }

    /**
     * Get a bitmap from the image path.
     *
     * @param imagePath
     * @param sampleSize
     * @return bitmap and null if read fails
     */
    public static Bitmap getBitmap(final String imagePath, int sampleSize) {
        final Options options = new Options();
        options.inDither = false;
        options.inSampleSize = sampleSize;

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(imagePath, "r");
            return BitmapFactory.decodeFile(imagePath, options);
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        } finally {
            if (file != null)
                try {
                    file.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
        }
    }

    /**
     * Get a bitmap from the image
     *
     * @param image
     * @param sampleSize
     * @return bitmap and null if read fails
     */
    public static Bitmap getBitmap(final byte[] image, int sampleSize) {
        final Options options = new Options();
        options.inDither = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeByteArray(image, 0, image.length, options);
    }

    /**
     * Get scale for image of size and max height/width
     *
     * @param size
     * @param width
     * @param height
     * @return scale
     */
    public static int getScale(Point size, int width, int height) {
        if (size.x > width || size.y > height) {
            return Math.max(Math.round((float) size.x / (float) width), Math.round((float) size.y / (float) height));
        } else {
            return 1;
        }
    }

    /**
     * Get size of the image
     *
     * @param imagePath
     * @return size
     */
    public static Point getSize(final String imagePath) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;

        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(imagePath, "r");
            BitmapFactory.decodeFileDescriptor(file.getFD(), null, options);
            return new Point(options.outWidth, options.outHeight);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Get size of the image
     *
     * @param image
     * @return size
     */
    public static Point getSize(final byte[] image) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(image, 0, image.length, options);
        return new Point(options.outWidth, options.outHeight);
    }

    /**
     * Get bitmap wiht max width or height
     *
     * @param imagePath
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final String imagePath, int width, int height) {
        Point point = getSize(imagePath);
        return getBitmap(imagePath, getScale(point, width, height));
    }


    /**
     * Get bitmap with max width or height
     *
     * @param image
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final byte[] image, int width, int height) {
        Point point = getSize(image);
        return getBitmap(image, getScale(point, width, height));
    }

    /**
     * Get a bitmap from file image
     *
     * @param image
     * @param width
     * @param height
     * @return image
     */
    public static Bitmap getBitmap(final File image, int width, int height) {
        return getBitmap(image.getAbsoluteFile(), width, height);
    }

    /**
     * Get a bitmap from file image
     *
     * @param image
     * @return
     */
    public static Bitmap getBitmap(final File image) {
        return getBitmap(image.getAbsolutePath());
    }

    /**
     * Load a bitmap for the given file and set it on the given imageview
     *
     * @param image
     * @param view
     */
    public static void setImage(final File image, ImageView view) {
        Bitmap bitmap = getBitmap(image);
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
        }
    }

    /**
     * Round the corners of a bitmap
     *
     * @param source
     * @param radius
     * @return rounded corner bitmap
     */
    public static Bitmap roundCorners(final Bitmap source, final float radius) {
        int width = source.getWidth();
        int height = source.getHeight();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(WHITE);

        Bitmap clipped = Bitmap.createBitmap(width, height, ARGB_8888);
        Canvas canvas = new Canvas(clipped);
        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(DST_IN));

        Bitmap rounded = Bitmap.createBitmap(width, height, ARGB_8888);
        canvas = new Canvas(rounded);
        canvas.drawBitmap(source, 0, 0, null);
        canvas.drawBitmap(rounded, 0, 0, paint);

        source.recycle();
        clipped.recycle();

        return rounded;
    }

}
