package com.kyriakosalexandrou.fuse_test_v11.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyriakos on 10/12/2015.
 */
public class Company {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("custom_color")
    @Expose
    private String customColor;
    @SerializedName("password_changing")
    @Expose
    private PasswordChanging passwordChanging;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return The customColor
     */
    public String getCustomColor() {
        return customColor;
    }

    /**
     * @param customColor The custom_color
     */
    public void setCustomColor(String customColor) {
        this.customColor = customColor;
    }

    /**
     * @return The passwordChanging
     */
    public PasswordChanging getPasswordChanging() {
        return passwordChanging;
    }

    /**
     * @param passwordChanging The password_changing
     */
    public void setPasswordChanging(PasswordChanging passwordChanging) {
        this.passwordChanging = passwordChanging;
    }
}