package com.zunke.shopmanager.dto.reportsForDMY;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/22 10:22
 */
public class MembersRegister {
    private String bywhen;
    private Integer registerNum;

    public String getBywhen() {
        return bywhen;
    }

    public void setBywhen(String bywhen) {
        this.bywhen = bywhen;
    }

    public Integer getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }
}
