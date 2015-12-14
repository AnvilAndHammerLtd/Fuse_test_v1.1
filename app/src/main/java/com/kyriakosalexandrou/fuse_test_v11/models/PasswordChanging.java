package com.kyriakosalexandrou.fuse_test_v11.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class PasswordChanging {

    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("secure_field")
    @Expose
    private Object secureField;

    /**
     * @return The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The secureField
     */
    public Object getSecureField() {
        return secureField;
    }

    /**
     * @param secureField The secure_field
     */
    public void setSecureField(Object secureField) {
        this.secureField = secureField;
    }

}
