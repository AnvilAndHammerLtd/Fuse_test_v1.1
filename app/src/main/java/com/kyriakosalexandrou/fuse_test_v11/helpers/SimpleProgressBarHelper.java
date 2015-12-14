package com.kyriakosalexandrou.fuse_test_v11.helpers;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.kyriakosalexandrou.fuse_test_v11.R;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.HasProgressBar;

/**
 * Created by Kyriakos on 14/12/2015.
 * <p/>
 * Preferring composition over inheritance.
 * Simply create an instance of this class to the class
 * that requires a progressBar and the logic would be handled logic from here.
 */
public class SimpleProgressBarHelper implements HasProgressBar {
    private static final String TAG = SimpleProgressBarHelper.class.getSimpleName();
    private ProgressBar mProgressBar;

    /**
     * prepares the progressBar object
     *
     * @param view a view that contains a progressBar with the property android:id="@+id/progress"
     */
    public SimpleProgressBarHelper(View view) {
        if (view != null) {
            mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        } else {
            Log.v(TAG, "The view parameter was null");
        }
    }

    @Override
    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
