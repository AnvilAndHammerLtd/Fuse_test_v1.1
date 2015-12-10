package com.kyriakosalexandrou.fuse_test_v11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;

import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity implements IuiHelper {
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
            mCompanyService.getCompanyRequest(companyName);
        }
    }
}