package com.zunke.shopmanager.vo;

/**
 * @author yuzhe
 * @version 1.0
 */
public class UserVO {
    private String mumberName;
    private String mumberPassword;

    public String getMumberName() {
        return mumberName;
    }

    public UserVO setMumberName(String mumberName) {
        this.mumberName = mumberName;
        return this;
    }

    public String getMumberPassword() {
        return mumberPassword;
    }

    public UserVO setMumberPassword(String mumberPassword) {
        this.mumberPassword = mumberPassword;
        return this;
    }
}
