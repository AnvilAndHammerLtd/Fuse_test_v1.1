package com.kyriakosalexandrou.fuse_test_v11.services;

import android.util.Log;

import com.kyriakosalexandrou.fuse_test_v11.MainActivity;
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

    /**
     * sends a request to retrieve a Company's data.
     *<p>
     * on success the {@link MainActivity#onEventMainThread(CompanyEvent event)}
     * <br/>
     * on failure the {@link MainActivity#onEventMainThread(ErrorEvent event)}
     *
     * @param companyName the company to get the data for
     * @param event the CompanyEvent to store the retrieved data
     */
    public void getCompanyRequest(final String companyName, final CompanyEvent event) {

        mService.getCompany(companyName, new Callback<Company>() {

                    @Override
                    public void success(Company company, Response response) {
                        Log.v(TAG, "getCompanyRequest success");
                        event.setCompany(company);
                        EventBus.getDefault().postSticky(event);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v(TAG, "getCompanyRequest failure");
                        EventBus.getDefault().postSticky(event.getErrorEvent());
                    }
                }
        );
    }

    public interface ICompanyService {
        @GET("//{companyName}.fusion-universal.com/api/v1/company.json")
        void getCompany(@Path("companyName") String companyName, Callback<Company> response);
    }

}
