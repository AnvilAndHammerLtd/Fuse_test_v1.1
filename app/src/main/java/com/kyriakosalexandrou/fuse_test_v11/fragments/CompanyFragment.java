package com.kyriakosalexandrou.fuse_test_v11.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.MainActivity;
import com.kyriakosalexandrou.fuse_test_v11.R;
import com.kyriakosalexandrou.fuse_test_v11.Util;
import com.kyriakosalexandrou.fuse_test_v11.events.CompanyEvent;
import com.kyriakosalexandrou.fuse_test_v11.events.ErrorEvent;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.CommonFragmentUiLogicHelper;
import com.kyriakosalexandrou.fuse_test_v11.interfaces.HasProgressBar;
import com.kyriakosalexandrou.fuse_test_v11.models.Company;
import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;

/**
 * Created by Kyriakos on 12/12/2015.
 * <p/>
 * Responsible for all the Company logic
 */
public class CompanyFragment extends Fragment implements CommonFragmentUiLogicHelper {
    public static final String TAG = CompanyFragment.class.getName();

    public static final int DEFAULT_COMPANY_BG_COLOR = Color.WHITE;
    public static final int VALID_COMPANY_BG_COLOR = Color.GREEN;
    public static final int INVALID_COMPANY_BG_COLOR = Color.RED;

    public static float ENABLED_CLEAR_BUTTON_ALPHA;
    public static float DISABLED_CLEAR_BUTTON_ALPHA;

    private EditText mCompanyNameUserInput;
    private ImageView mCompanyNameUserInputClearBtn;
    private ImageView mCompanyImage;
    private HasProgressBar mHasProgressBar;

    public CompanyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        ENABLED_CLEAR_BUTTON_ALPHA = Util.getResFloatValue(getContext(), R.dimen.enabled_clear_button_alpha);
        DISABLED_CLEAR_BUTTON_ALPHA = Util.getResFloatValue(getContext(), R.dimen.disabled_clear_button_alpha);

        bindViews(view);
        setListeners();

        return view;
    }

    @Override
    public void bindViews(View view) {
        mCompanyNameUserInput = (EditText) view.findViewById(R.id.company_name_user_input);
        mCompanyNameUserInputClearBtn = (ImageView) view.findViewById(R.id.company_name_user_input_clear_icon);
        mCompanyImage = (ImageView) view.findViewById(R.id.company_image);
        mHasProgressBar = (HasProgressBar) getActivity();
    }

    @Override
    public void setListeners() {
        setCompanyNameUserInputTextChangedListener();
        setCompanyNameUserInputOnEditorActionListener();
        setCompanyNameUserInputClearBtnOnClickListener();
    }

    private void setCompanyNameUserInputTextChangedListener() {
        mCompanyNameUserInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setCompanyUiToDefault();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                shouldShowClearBtn(count);
            }
        });
    }

    private void shouldShowClearBtn(int count) {
        if (count > 0) {
            mCompanyNameUserInputClearBtn.setAlpha(ENABLED_CLEAR_BUTTON_ALPHA);
        } else {
            mCompanyNameUserInputClearBtn.setAlpha(DISABLED_CLEAR_BUTTON_ALPHA);
        }
    }

    private void setCompanyUiToDefault() {
        mCompanyNameUserInput.setBackgroundColor(DEFAULT_COMPANY_BG_COLOR);
        mCompanyImage.setVisibility(View.INVISIBLE);
    }

    private void setCompanyNameUserInputOnEditorActionListener() {
        mCompanyNameUserInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {

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

    private void setCompanyNameUserInputClearBtnOnClickListener() {
        mCompanyNameUserInputClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompanyNameUserInput.setText("");
            }
        });
    }

    private void getCompany(String companyName) {
        if (companyName.length() > 0) {
            Util.dismissSoftKeyBoard(getActivity());
            mHasProgressBar.showProgressBar();
            CompanyService mCompanyService = new CompanyService(MainActivity.REST_ADAPTER);
            ErrorEvent errorEvent = new ErrorEvent(getResources().getString(R.string.get_company_request_failure));
            mCompanyService.getCompanyRequest(companyName, new CompanyEvent(errorEvent));
        }
    }

    public void onEventMainThread(CompanyEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        setCompanyUiToValid(event.getCompany());
        mHasProgressBar.hideProgressBar();
    }

    private void setCompanyUiToValid(Company company) {
        setCompanyTextToValid(company.getName());
        setCompanyLogoToValid(company.getLogo());
    }

    private void setCompanyTextToValid(String companyName) {
        mCompanyNameUserInput.setText(companyName);
        mCompanyNameUserInput.setBackgroundColor(VALID_COMPANY_BG_COLOR);
    }

    private void setCompanyLogoToValid(String logoURL) {
        mCompanyImage.setVisibility(View.VISIBLE);
        Picasso.with(getContext())
                .load(logoURL)
                .into(mCompanyImage);
    }

    public void onEventMainThread(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mHasProgressBar.hideProgressBar();
        Util.showToastMessageCentered(getContext(), event.getErrorMessage());
        mCompanyNameUserInput.setBackgroundColor(INVALID_COMPANY_BG_COLOR);
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
