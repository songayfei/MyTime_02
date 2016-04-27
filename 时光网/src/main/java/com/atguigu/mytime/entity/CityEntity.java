package com.atguigu.mytime.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者: Administrator on 2016/4/20 19:55
 * 说明: 城市列表实体类
 */
@Table(name = "city_data")
public class CityEntity {

    @Column(name = "count")
    private int count;

    @Column(name = "id",isId = true,autoGen = true)
    private int id;

    @Column(name = "cityid")
    private int cityid;

    @Column(name = "cityname")
    private String cityname;

    @Column(name = "pinyinFull")
    private String pinyinFull;

    @Column(name = "pinyinShort")
    private String pinyinShort;


    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPinyinFull() {
        return pinyinFull;
    }

    public void setPinyinFull(String pinyinFull) {
        this.pinyinFull = pinyinFull;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "count=" + count +
                ", id=" + id +
                ", cityname='" + cityname + '\'' +
                ", pinyinFull='" + pinyinFull + '\'' +
                ", pinyinShort='" + pinyinShort + '\'' +
                '}';
    }
}
