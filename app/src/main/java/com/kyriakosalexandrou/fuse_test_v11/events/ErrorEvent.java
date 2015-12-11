package com.kyriakosalexandrou.fuse_test_v11.events;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class ErrorEvent {
    private String mErrorMessage;

    public ErrorEvent(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
