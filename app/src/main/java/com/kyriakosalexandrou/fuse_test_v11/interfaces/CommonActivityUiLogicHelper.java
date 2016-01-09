package com.kyriakosalexandrou.fuse_test_v11.interfaces;

import android.os.Bundle;

/**
 * Created by Kyriakos on 10/12/2015.
 * <p/>
 * Have an activity implement this for UI logic help
 */
public interface CommonActivityUiLogicHelper {
    /**
     *  * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState
     * @param layoutId the layout id to load to the activity
     */
    void onCreate(Bundle savedInstanceState, int layoutId);

    void bindViews();

    void setListeners();
}
