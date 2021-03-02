package br.com.flasco.leanworks.utils;

import android.content.Intent;

public class PresentationUtils {

    public static Intent shareIntent(String content){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        return shareIntent;
    }
}
