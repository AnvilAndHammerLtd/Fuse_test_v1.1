package com.kyriakosalexandrou.fuse_test_v11;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kyriakos on 10/12/2015.
 * <p/>
 * Useful generic re-usable methods
 */
public class Util {

    public static String cleanTextFromSpaces(TextView view) {
        String textWithoutSpaces = null;

        if (view != null) {
            textWithoutSpaces = view.getText().toString();
            textWithoutSpaces = textWithoutSpaces.trim();
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

    public static void showToastMessageCentered(Context context, String message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void dismissSoftKeyBoard(Activity activity) {
        View view = activity.getWindow().getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static float getResFloatValue(Context context, int dimenFloatResId) {
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(dimenFloatResId, typedValue, true);
        return typedValue.getFloat();
    }

}
