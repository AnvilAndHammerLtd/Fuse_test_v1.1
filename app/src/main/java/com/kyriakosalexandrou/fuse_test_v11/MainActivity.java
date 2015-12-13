package com.kyriakosalexandrou.fuse_test_v11;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.kyriakosalexandrou.fuse_test_v11.fragments.CompanyFragment;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.CommonActivityUiLogicHelper;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.HasProgressBar;

import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity implements CommonActivityUiLogicHelper, HasProgressBar {
    private static final String TAG = MainActivity.class.getName();
    public static final String BASE_URL = "https:/";

    public static RestAdapter REST_ADAPTER;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        bindViews();
        setListeners();

        REST_ADAPTER = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();

        goToCompanyFragment();
    }

    @Override
    public void bindViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public void setListeners() {
    }

    private void goToCompanyFragment() {
        FragmentManager fm = getSupportFragmentManager();
        CompanyFragment companyFragment = new CompanyFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, companyFragment, CompanyFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
        fm.executePendingTransactions();
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