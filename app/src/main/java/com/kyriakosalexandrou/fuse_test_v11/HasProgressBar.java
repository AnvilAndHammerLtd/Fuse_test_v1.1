package com.kyriakosalexandrou.fuse_test_v11;

/**
 * Created by Kyriakos on 12/12/2015.
 * <p/>
 * have an activity implement this interface to handle the progressBar logic.
 * In this way it's possible to encapsulate the progress bar logic to it's main class and
 * at the same time to provide access to access the progress bar from other classes
 * such as fragments
 */
public interface HasProgressBar {

    /**
     * shows progress bar
     */
    void showProgressBar();

    /**
     * hides progress bar
     */
    void hideProgressBar();
}