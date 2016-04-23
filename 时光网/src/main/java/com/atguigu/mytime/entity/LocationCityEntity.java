package com.atguigu.mytime.entity;

/**
 * 作者: Administrator on 2016/4/20 13:11
 * 说明: 定位城市返回实体类
 */
public class LocationCityEntity {

    /**
     * cityId : 290
     * name : 北京
     * pinyinShort : bj
     */

    private int cityId;
    private String name;
    private String pinyinShort;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }
}
