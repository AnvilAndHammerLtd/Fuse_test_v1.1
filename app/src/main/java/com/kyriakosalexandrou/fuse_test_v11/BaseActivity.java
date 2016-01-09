package com.kyriakosalexandrou.fuse_test_v11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kyriakosalexandrou.fuse_test_v11.helpers.ProgressBarHelperBase;
import com.kyriakosalexandrou.fuse_test_v11.helpers.SimpleProgressBarHelper;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.CommonActivityUiLogicHelper;

import retrofit.RestAdapter;

/**
 * Created by Kyriakos on 09/01/2016.
 */
public class BaseActivity extends AppCompatActivity implements CommonActivityUiLogicHelper {
    private static final String TAG = BaseActivity.class.getName();
    public static final String BASE_URL = "https:/";
    public static RestAdapter REST_ADAPTER;
    private ProgressBarHelperBase mProgressBarFullScreen;

    public ProgressBarHelperBase getProgressBarFullScreen() {
        return mProgressBarFullScreen;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        mProgressBarFullScreen = new SimpleProgressBarHelper(this, SimpleProgressBarHelper.ProgressBarSize.FULL_SCREEN);
        REST_ADAPTER = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();
    }

    @Override
    public void bindViews() {
    }

    @Override
    public void setListeners() {
    }
}