package com.kyriakosalexandrou.fuse_test_v11.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.kyriakosalexandrou.fuse_test_v11.helpers.BaseProgressBarHelper;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.CommonFragmentUiLogicHelper;

/**
 * Created by Kyriakos on 09/01/2016.
 */
public class BaseFragment extends Fragment implements CommonFragmentUiLogicHelper {
    private static final String TAG = BaseFragment.class.getName();
    private BaseProgressBarHelper mProgressBarHelper;

    public BaseFragment() {
    }

    protected BaseProgressBarHelper getProgressBarHelper() {
        return mProgressBarHelper;
    }

    protected void setProgressBarHelper(BaseProgressBarHelper baseProgressBarHelper) {
        mProgressBarHelper = baseProgressBarHelper;
    }

    @Override
    public void bindViews(View view) {
    }

    @Override
    public void setListeners() {
    }
}
