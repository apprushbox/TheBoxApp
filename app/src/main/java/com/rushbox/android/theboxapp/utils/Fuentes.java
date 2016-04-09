package com.rushbox.android.theboxapp.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.rushbox.android.theboxapp.R;

/**
 * Created by Ronner on 06-04-2016.
 */
public class Fuentes {
    public static Typeface getFuenteRobotoBlack(Context context) {
        Typeface fuenteRobotoBlack = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fuenteRobotoBlack));
        return fuenteRobotoBlack;
    }

    public static Typeface getFuenteRobotoLight(Context context) {
        Typeface fuenteRobotoLight = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fuenteRobotoLight));
        return fuenteRobotoLight;
    }

    public static Typeface getFuenteRobotoMedium(Context context) {
        Typeface fuenteRobotoMedium = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fuenteRobotoMedium));
        return fuenteRobotoMedium;
    }


}
