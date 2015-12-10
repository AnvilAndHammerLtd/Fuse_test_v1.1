package com.kyriakosalexandrou.fuse_test_v11;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class Util {

    public static String cleanTextFromSpaces(TextView view) {
        String textWithoutSpaces = null;

        if (view != null) {
            textWithoutSpaces = view.getText().toString();
            textWithoutSpaces.trim();
        }

        return textWithoutSpaces;
    }

    public static void showToastMessageCentered(Context context, int message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
