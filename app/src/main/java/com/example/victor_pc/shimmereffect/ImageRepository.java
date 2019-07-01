package com.example.victor_pc.shimmereffect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageRepository {

    private static final ImageRepository ourInstance = new ImageRepository();
    private List<Integer> image = new ArrayList<>();
    private Context context;

    public static ImageRepository getInstance() {
        return ourInstance;
    }

    private ImageRepository() {
        image.add(R.drawable.image_1);
        image.add(R.drawable.image_2);
        image.add(R.drawable.image_3);
        image.add(R.drawable.image_4);
        image.add(R.drawable.image_5);
        image.add(R.drawable.image_6);
    }

    public byte[] generateRandomImage() {
        Random random = new Random();
        Resources res = context.getResources();
        Drawable drawable = res.getDrawable(image.get(random.nextInt(image.size())));
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapData = stream.toByteArray();
        return bitmapData;
    }
}
