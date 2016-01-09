package com.kyriakosalexandrou.fuse_test_v11.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.BaseActivity;
import com.kyriakosalexandrou.fuse_test_v11.R;
import com.kyriakosalexandrou.fuse_test_v11.Util;
import com.kyriakosalexandrou.fuse_test_v11.events.CompanyEvent;
import com.kyriakosalexandrou.fuse_test_v11.events.ErrorEvent;
import com.kyriakosalexandrou.fuse_test_v11.helpers.BaseProgressBarHelper;
import com.kyriakosalexandrou.fuse_test_v11.models.Company;
import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;
import com.kyriakosalexandrou.fuse_test_v11.widgets.ClearableEditText;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;

/**
 * Created by Kyriakos on 12/12/2015.
 * <p/>
 * Responsible for all the Company logic
 */
public class CompanyFragment extends BaseFragment {
    public static final String TAG = CompanyFragment.class.getName();

    private static final int DEFAULT_COMPANY_BG_COLOR = Color.WHITE;
    private static final int VALID_COMPANY_BG_COLOR = Color.GREEN;
    private static final int INVALID_COMPANY_BG_COLOR = Color.RED;

    private ClearableEditText mCompanyNameClearableEditText;
    private ImageView mCompanyImage;

    public CompanyFragment() {
    }

    public static CompanyFragment newInstance(BaseProgressBarHelper baseProgressBarHelper) {
        CompanyFragment fragment = new CompanyFragment();

        fragment.setProgressBarHelper(baseProgressBarHelper);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        bindViews(view);
        setListeners();

        return view;
    }

    @Override
    public void bindViews(View view) {
        mCompanyNameClearableEditText = (ClearableEditText) view.findViewById(R.id.company_name_clearable_edittext);
        mCompanyImage = (ImageView) view.findViewById(R.id.company_image);
    }

    @Override
    public void setListeners() {
        mCompanyNameClearableEditText.setClearableEditTextListener(new ClearableEditText.ClearableEditTextCallbacks() {
            @Override
            public void afterTextChanged(Editable s) {
                setCompanyUiToDefault();
            }

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        String companyNameWithoutSpaces = Util.cleanTextFromSpaces(v);
                        getCompany(companyNameWithoutSpaces);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setCompanyUiToDefault() {
        mCompanyNameClearableEditText.getEditText().setBackgroundColor(DEFAULT_COMPANY_BG_COLOR);
        mCompanyImage.setVisibility(View.INVISIBLE);
    }

    private void getCompany(String companyName) {
        if (companyName.length() > 0) {
            Util.dismissSoftKeyBoard(getActivity());
            getProgressBarHelper().showProgressBar();
            CompanyService mCompanyService = new CompanyService(BaseActivity.REST_ADAPTER);
            ErrorEvent errorEvent = new ErrorEvent(getResources().getString(R.string.get_company_request_failure));
            mCompanyService.getCompanyRequest(companyName, new CompanyEvent(errorEvent));
        }
    }

    public void onEventMainThread(CompanyEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        setCompanyUiToValid(event.getCompany());
        getProgressBarHelper().hideProgressBar();
    }

    private void setCompanyUiToValid(Company company) {
        setCompanyTextToValid(company.getName());
        setCompanyLogoToValid(company.getLogo());
    }

    private void setCompanyTextToValid(String companyName) {
        mCompanyNameClearableEditText.getEditText().setText(companyName);
        mCompanyNameClearableEditText.getEditText().setBackgroundColor(VALID_COMPANY_BG_COLOR);
    }

    private void setCompanyLogoToValid(String logoURL) {
        mCompanyImage.setVisibility(View.VISIBLE);
        Picasso.with(getContext())
                .load(logoURL)
                .into(mCompanyImage);
    }

    public void onEventMainThread(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        getProgressBarHelper().hideProgressBar();
        Util.showToastMessageCentered(getContext(), event.getErrorMessage());
        mCompanyNameClearableEditText.getEditText().setBackgroundColor(INVALID_COMPANY_BG_COLOR);
    }

    @Override
    public void onStart() {
        EventBus.getDefault().registerSticky(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
