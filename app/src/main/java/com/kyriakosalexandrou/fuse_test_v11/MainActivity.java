package com.kyriakosalexandrou.fuse_test_v11;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.events.CompanyEvent;
import com.kyriakosalexandrou.fuse_test_v11.events.ErrorEvent;
import com.kyriakosalexandrou.fuse_test_v11.models.Company;
import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity implements CommonUiLogicHelper {
    private static final String TAG = MainActivity.class.getName();
    public static final String BASE_URL = "https:/";

    public static final int DEFAULT_COMPANY_BG_COLOR = Color.WHITE;
    public static final int VALID_COMPANY_BG_COLOR = Color.GREEN;
    public static final int INVALID_COMPANY_BG_COLOR = Color.RED;

    private EditText mCompanyNameUserInput;
    private RestAdapter mRestAdapter;
    private ImageView mCompanyImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setListeners();

        mRestAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();
    }

    @Override
    public void bindViews() {
        mCompanyNameUserInput = (EditText) findViewById(R.id.company_name_user_input);
        mCompanyImage = (ImageView) findViewById(R.id.company_image);
    }

    @Override
    public void setListeners() {

        mCompanyNameUserInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setCompanyUiToDefault();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

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

    private void setCompanyUiToDefault() {
        mCompanyNameUserInput.setBackgroundColor(DEFAULT_COMPANY_BG_COLOR);
        mCompanyImage.setVisibility(View.INVISIBLE);
    }

    private void getCompany(String companyName) {
        if (companyName.length() > 0) {
            CompanyService mCompanyService = new CompanyService(mRestAdapter);
            ErrorEvent errorEvent = new ErrorEvent(getResources().getString(R.string.get_company_request_failure));
            mCompanyService.getCompanyRequest(companyName, new CompanyEvent(errorEvent));
        }
    }

    public void onEventMainThread(CompanyEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        setCompanyUiToValid(event.getCompany());
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
        Picasso.with(this)
                .load(logoURL)
                .into(mCompanyImage);
    }

    public void onEventMainThread(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        Util.showToastMessageCentered(this, event.getErrorMessage());
        mCompanyNameUserInput.setBackgroundColor(INVALID_COMPANY_BG_COLOR);
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().registerSticky(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}