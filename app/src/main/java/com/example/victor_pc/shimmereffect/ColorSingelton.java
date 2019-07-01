package com.example.victor_pc.shimmereffect;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ColorSingelton {
    private static final ColorSingelton ourInstance = new ColorSingelton();
    private List<int[]> color = new ArrayList<>();

    public static ColorSingelton getInstance() {
        return ourInstance;
    }

    private ColorSingelton() {
        color.add(new int[] {Color.parseColor("#0F2027"), Color.parseColor("#2C5364")});
        color.add(new int[] {Color.parseColor("#4AC29A"), Color.parseColor("#BDFFF3")});
        color.add(new int[] {Color.parseColor("#f12711"), Color.parseColor("#f5af19")});
        color.add(new int[] {Color.parseColor("#240b36"), Color.parseColor("#c31432")});
        color.add(new int[] {Color.parseColor("#1f4037"), Color.parseColor("#99f2c8")});
        color.add(new int[] {Color.parseColor("#544a7d"), Color.parseColor("#ffd452")});
        color.add(new int[] {Color.parseColor("#654ea3"), Color.parseColor("#eaafc8")});
        color.add(new int[] {Color.parseColor("#333333"), Color.parseColor("#dd1818")});
        color.add(new int[] {Color.parseColor("#636363"), Color.parseColor("#a2ab58")});
        color.add(new int[] {Color.parseColor("#3f2b96"), Color.parseColor("#a8c0ff")});
        color.add(new int[] {Color.parseColor("#BA5370"), Color.parseColor("#F4E2D8")});
        color.add(new int[] {Color.parseColor("#11998e"), Color.parseColor("#38ef7d")});
        color.add(new int[] {Color.parseColor("#00b09b"), Color.parseColor("#96c93d")});
        color.add(new int[] {Color.parseColor("#D1913C"), Color.parseColor("#FFD194")});
        color.add(new int[] {Color.parseColor("#215f00"), Color.parseColor("#e4e4d9")});
        color.add(new int[] {Color.parseColor("#ff5e62"), Color.parseColor("#ff9966")});
        color.add(new int[] {Color.parseColor("#29323c"), Color.parseColor("#485563")});
        color.add(new int[] {Color.parseColor("#000046"), Color.parseColor("#1CB5E0")});
        color.add(new int[] {Color.parseColor("#20002c"), Color.parseColor("#cbb4d4")});
        color.add(new int[] {Color.parseColor("#0f3443"), Color.parseColor("#34e89e")});
        color.add(new int[] {Color.parseColor("#45B649"), Color.parseColor("#DCE35B")});
        color.add(new int[] {Color.parseColor("#ff7e5f"), Color.parseColor("#feb47b")});
        color.add(new int[] {Color.parseColor("#BE93C5"), Color.parseColor("#7BC6CC")});
        color.add(new int[] {Color.parseColor("#808080"), Color.parseColor("#3fada8")});
        color.add(new int[] {Color.parseColor("#42275a"), Color.parseColor("#734b6d")});
    }

    public GradientDrawable generateGradientDrawable()
    {
        Random rand = new Random();
        int randNumber = rand.nextInt(24);
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                color.get(randNumber));
        gd.setCornerRadius(0f);
        return gd;
    }
}
