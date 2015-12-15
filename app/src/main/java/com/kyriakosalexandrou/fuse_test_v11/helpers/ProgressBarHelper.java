package com.kyriakosalexandrou.fuse_test_v11.helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kyriakosalexandrou.fuse_test_v11.R;
import com.kyriakosalexandrou.fuse_test_v11.Util;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.HasProgressBar;

/**
 * Created by Kyriakos on 14/12/2015.
 * <p/>
 * Preferring composition over inheritance.
 * Simply create an instance of this class to the class
 * that requires a progressBar and the logic would be handled from here.
 */
public class ProgressBarHelper implements HasProgressBar {
    private static final String TAG = ProgressBarHelper.class.getSimpleName();
    private ProgressBar mProgressBar;
    private View mProgressBarContainer;

    /**
     * prepares the progressBar object
     *
     * @param context the context
     */
    public ProgressBarHelper(Context context, ProgressBarSize progressBarSize) {
        if (context != null) {
            mProgressBarContainer = createProgressBarContainer(context, progressBarSize);
            mProgressBar = createProgressBar(mProgressBarContainer);
        } else {
            Log.v(TAG, "The context parameter was null");
        }
    }

    /**
     * this can be used to make changes to the actual progress bar during runtime such as change state, drawable etc.
     *
     * @return the progress bar
     */
    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    private View createProgressBarContainer(Context context, ProgressBarSize progressBarSize) {
        LayoutInflater layoutInflater = Util.getLayoutInflater(context);
        View progressBarContainer = inflateProgressBarView(progressBarSize, layoutInflater);

        ViewGroup layout = Util.getRootView(context);
        layout.addView(progressBarContainer);

        return progressBarContainer;
    }

    private View inflateProgressBarView(ProgressBarSize progressBarSize, LayoutInflater layoutInflater) {
        View progressBarContainer;

        switch (progressBarSize) {
            case SMALL:
                progressBarContainer = layoutInflater.inflate(R.layout.progress_bar_small, null);
                break;

            case LARGE:
                progressBarContainer = layoutInflater.inflate(R.layout.progress_bar_large, null);
                break;

            case FULL_SCREEN:
                progressBarContainer = layoutInflater.inflate(R.layout.progress_bar_full_screen, null);
                break;

            default:
                progressBarContainer = layoutInflater.inflate(R.layout.progress_bar_small, null);
        }
        return progressBarContainer;
    }

    private ProgressBar createProgressBar(View progressBarContainer) {
        ProgressBar progressBar = (ProgressBar) progressBarContainer.findViewById(R.id.progress);
        return progressBar;
    }

    @Override
    public void showProgressBar() {
        if (mProgressBarContainer != null) {
            mProgressBarContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBarContainer != null) {
            mProgressBarContainer.setVisibility(View.GONE);
        }
    }

    /**
     * different progress bar sizes
     */
    public enum ProgressBarSize {
        SMALL, LARGE, FULL_SCREEN
    }
}
