package com.kyriakosalexandrou.fuse_test_v11.services;

import android.app.usage.UsageEvents;
import android.util.Log;

import com.kyriakosalexandrou.fuse_test_v11.events.CompanyEvent;
import com.kyriakosalexandrou.fuse_test_v11.events.ErrorEvent;
import com.kyriakosalexandrou.fuse_test_v11.models.Company;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class CompanyService {
    private static final String TAG = CompanyService.class.getName();
    private ICompanyService mService;


    public CompanyService(RestAdapter restAdapter) {
        mService = restAdapter.create(ICompanyService.class);
    }

    public void getCompanyRequest(final String companyName, final CompanyEvent event) {

        mService.getCompany(companyName, new Callback<Company>() {

                    @Override
                    public void success(Company company, Response response) {
                        Log.v(TAG, "success");
                        event.setCompany(company);
                        EventBus.getDefault().postSticky(event);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v(TAG, "failure");
                        EventBus.getDefault().postSticky(new ErrorEvent("Failed to receive company details"));
                    }
                }
        );
    }

    public interface ICompanyService {
        @GET("//{companyName}.fusion-universal.com/api/v1/company.json")
        void getCompany(@Path("companyName") String companyName, Callback<Company> response);
    }

}
