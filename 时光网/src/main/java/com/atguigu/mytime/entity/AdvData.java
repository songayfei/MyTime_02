package com.atguigu.mytime.entity;

/**
 * Created by Administrator on 2016/4/14.
 */
public class AdvData {

    /**
     * advId : 0
     * advType : 0
     * url :
     * bigImg :
     * styleImg :
     * img :
     * gotoPage : {}
     * title :
     * tag :
     * isHorizontalScreen : false
     * isOpenH5 : false
     */

    private int advId;
    private int advType;
    private String url;
    private String bigImg;
    private String styleImg;
    private String img;
    private String title;
    private String tag;
    private boolean isHorizontalScreen;
    private boolean isOpenH5;

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public void setAdvType(int advType) {
        this.advType = advType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public void setStyleImg(String styleImg) {
        this.styleImg = styleImg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setIsHorizontalScreen(boolean isHorizontalScreen) {
        this.isHorizontalScreen = isHorizontalScreen;
    }

    public void setIsOpenH5(boolean isOpenH5) {
        this.isOpenH5 = isOpenH5;
    }

    public int getAdvId() {
        return advId;
    }

    public int getAdvType() {
        return advType;
    }

    public String getUrl() {
        return url;
    }

    public String getBigImg() {
        return bigImg;
    }

    public String getStyleImg() {
        return styleImg;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public boolean isIsHorizontalScreen() {
        return isHorizontalScreen;
    }

    public boolean isIsOpenH5() {
        return isOpenH5;
    }
}
