package com.kyriakosalexandrou.fuse_test_v11.events;

import com.kyriakosalexandrou.fuse_test_v11.models.Company;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class CompanyEvent{
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
