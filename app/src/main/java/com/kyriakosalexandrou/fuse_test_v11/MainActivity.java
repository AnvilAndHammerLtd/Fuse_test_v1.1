package com.kyriakosalexandrou.fuse_test_v11;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kyriakosalexandrou.fuse_test_v11.fragments.CompanyFragment;
import com.kyriakosalexandrou.fuse_test_v11.helpers.SimpleProgressBarHelper;


public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);
        goToCompanyFragment();
    }

    private void goToCompanyFragment() {
        FragmentManager fm = getSupportFragmentManager();
        CompanyFragment companyFragment = CompanyFragment.newInstance();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, companyFragment, CompanyFragment.TAG);
        ft.commit();
        fm.executePendingTransactions();
    }
}