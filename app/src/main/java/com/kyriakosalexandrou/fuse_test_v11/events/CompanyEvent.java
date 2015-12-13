package com.kyriakosalexandrou.fuse_test_v11.events;

import com.kyriakosalexandrou.fuse_test_v11.models.Company;
import com.kyriakosalexandrou.fuse_test_v11.services.CompanyService;

/**
 * Created by Kyriakos on 10/12/2015.
 * <p/>
 * The event where the response from the {@link CompanyService#getCompanyRequest} gets stored
 */
public class CompanyEvent {
    private Company mCompany;
    private ErrorEvent mErrorEvent;

    public CompanyEvent(ErrorEvent errorEvent) {
        mErrorEvent = errorEvent;
    }

    public ErrorEvent getErrorEvent() {
        return mErrorEvent;
    }

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company mCompany) {
        this.mCompany = mCompany;
    }
}