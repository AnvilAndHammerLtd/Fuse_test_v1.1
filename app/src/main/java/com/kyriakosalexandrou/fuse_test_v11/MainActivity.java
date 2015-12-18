package com.kyriakosalexandrou.fuse_test_v11;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kyriakosalexandrou.fuse_test_v11.fragments.CompanyFragment;
import com.kyriakosalexandrou.fuse_test_v11.helpers.ProgressBarHelperBase;
import com.kyriakosalexandrou.fuse_test_v11.helpers.SimpleProgressBarHelper;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.CommonActivityUiLogicHelper;

import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity implements CommonActivityUiLogicHelper {
    private static final String TAG = MainActivity.class.getName();
    public static final String BASE_URL = "https:/";

    public static RestAdapter REST_ADAPTER;
    private ProgressBarHelperBase mSimpleProgressBarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setListeners();

        mSimpleProgressBarHelper = new SimpleProgressBarHelper(this, SimpleProgressBarHelper.ProgressBarSize.FULL_SCREEN);

        REST_ADAPTER = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();

        goToCompanyFragment();
    }

    @Override
    public void bindViews() {
    }

    @Override
    public void setListeners() {
    }

    private void goToCompanyFragment() {
        FragmentManager fm = getSupportFragmentManager();
        CompanyFragment companyFragment = CompanyFragment.newInstance(mSimpleProgressBarHelper);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, companyFragment, CompanyFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
        fm.executePendingTransactions();
    }
}