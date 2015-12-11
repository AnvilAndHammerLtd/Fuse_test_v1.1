package com.kyriakosalexandrou.fuse_test_v11;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.events.CompanyEvent;
import com.kyriakosalexandrou.fuse_test_v11.events.ErrorEvent;
import com.kyriakosalexandrou.fuse_test_v11.models.Company;
import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity implements CommonUiLogicHelper {
    public static final String BASE_URL = "https:/";
    private static final String TAG = MainActivity.class.getName();
    private EditText mCompanyNameUserInput;
    private RestAdapter mRestAdapter;


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
    }

    @Override
    public void setListeners() {
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

    private void getCompany(String companyName) {
        if (companyName.length() > 0) {
            CompanyService mCompanyService = new CompanyService(mRestAdapter);
            ErrorEvent errorEvent = new ErrorEvent(getResources().getString(R.string.get_company_request_failure));
            mCompanyService.getCompanyRequest(companyName, new CompanyEvent(errorEvent));
        }
    }

    public void onEventMainThread(CompanyEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        Company company = event.getCompany();
        mCompanyNameUserInput.setText(company.getName());
        mCompanyNameUserInput.setBackgroundColor(Color.GREEN);
    }

    public void onEventMainThread(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        Util.showToastMessageCentered(this, event.getErrorMessage());
        mCompanyNameUserInput.setBackgroundColor(Color.RED);
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