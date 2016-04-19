package com.atguigu.mytime.entity;

/**
 * Created by Administrator on 2016/4/14.
 */
public class OneGetData {
    private boolean isObtinData;

    public OneGetData(boolean isObtinData) {
        this.isObtinData = isObtinData;
    }

    public OneGetData() {
    }

    public boolean isObtinData() {
        return isObtinData;
    }

    public void setIsObtinData(boolean isObtinData) {
        this.isObtinData = isObtinData;
    }
}
