package com.example.sargis.sharedprefrecyclerview.utilities;

import android.widget.TextView;

public final class Utility {
    public static String getText(TextView textView) {
        String text = "";
        if (textView != null) {
            text = textView.getText().toString();
        }
        return text;
    }
}