package com.ivann.kezaco;

import android.content.Context;

class UiHelper {
    public static int drawableIdFromFileName(String image, Context context) {
        String identifier = image.substring(0, image.lastIndexOf("."));
        return context.getResources().getIdentifier(identifier, "drawable", context.getPackageName());
    }

    public static String getMediaDifficulty(Media media){
        switch (media.theme) {
            case "sound" :
                return "12 - 24 mois";
            case "picture" :
                return "24 - 36 mois";
            case "shadow" :
            default :
                return "36 mois +";
        }
    }
}
