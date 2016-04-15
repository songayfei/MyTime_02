package com.atguigu.mytime.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13.
 * 广告页实体类
 */
public class AdvListInfo {

    /**
     * success : true
     * count : 10
     * advList : [{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1460505601,"endDate":1460591999,"url":"http://img31.mtime.cn/mg/2016/04/01/093840.79277362.jpg","image":"","tag":"奇幻森林","isOpenH5":false},{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1460592001,"endDate":1460678399,"url":"http://img31.mtime.cn/mg/2016/04/01/093855.93689328.jpg","image":"","tag":"奇幻森林","isOpenH5":false},{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1460678401,"endDate":1460764799,"url":"http://img31.mtime.cn/mg/2016/04/01/093910.76530611.jpg","image":"","tag":"奇幻森林","isOpenH5":false},{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1461196801,"endDate":1461283199,"url":"http://img31.mtime.cn/mg/2016/04/07/121854.64974467.jpg","image":"","tag":"猎神","isOpenH5":false},{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1461283201,"endDate":1461369599,"url":"http://img31.mtime.cn/mg/2016/04/07/121954.94333162.jpg","image":"","tag":"猎神","isOpenH5":false},{"type":"100","typeName":"开启页广告","isHorizontalScreen":false,"startDate":1461369601,"endDate":1461455999,"url":"http://img31.mtime.cn/mg/2016/04/07/122005.12276019.jpg","image":"","tag":"猎神","isOpenH5":false},{"type":"201","typeName":"即将上映banner","isHorizontalScreen":false,"startDate":1451606401,"endDate":1478044799,"url":"http://static1.mtime.cn/feature/mobile/item/2016/banner/0401/750210.html","image":"","tag":"奇幻森林","isOpenH5":false},{"type":"301","typeName":"影院频道列表banner","isHorizontalScreen":false,"startDate":1451606401,"endDate":1482278399,"url":"http://static1.mtime.cn/feature/mobile/item/2016/banner/0413/mt0413175.html","image":"","tag":"商城蝙蝠侠大战超人手机壳 有价格","isOpenH5":false},{"type":"601","typeName":"排行榜页banner","isHorizontalScreen":false,"startDate":1451606401,"endDate":1478044799,"url":"http://static1.mtime.cn/tg/16/pufa750x175.html","image":"","tag":"浦发银行票务合作","isOpenH5":false},{"type":"605","typeName":"新闻列表第3条下banner","isHorizontalScreen":false,"startDate":1456790401,"endDate":1479686399,"url":"http://static1.mtime.cn/feature/mobile/item/2016/banner/0401/750210.html","image":"","tag":"奇幻森林","isOpenH5":false}]
     */

    private boolean success;
    private int count;
    /**
     * type : 100
     * typeName : 开启页广告
     * isHorizontalScreen : false
     * startDate : 1460505601
     * endDate : 1460591999
     * url : http://img31.mtime.cn/mg/2016/04/01/093840.79277362.jpg
     * image :asdfghjkkl
     * tag : 奇幻森林aga
     * isOpenH5 : false

     */

    private List<AdvListEntity> advList;



    public void setCount(int count) {
        this.count = count;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAdvList(List<AdvListEntity> advList) {
        this.advList = advList;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCount() {
        return count;
    }

    public List<AdvListEntity> getAdvList() {
        return advList;
    }

    public static class AdvListEntity {
        private String type;
        private String typeName;
        private boolean isHorizontalScreen;
        private int startDate;
        private int endDate;
        private String url;
        private String image;
        private String tag;
        private boolean isOpenH5;

        public void setType(String type) {
            this.type = type;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public void setIsHorizontalScreen(boolean isHorizontalScreen) {
            this.isHorizontalScreen = isHorizontalScreen;
        }

        public void setStartDate(int startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(int endDate) {
            this.endDate = endDate;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setIsOpenH5(boolean isOpenH5) {
            this.isOpenH5 = isOpenH5;
        }

        public String getType() {
            return type;
        }

        public String getTypeName() {
            return typeName;
        }

        public boolean isIsHorizontalScreen() {
            return isHorizontalScreen;
        }

        public int getStartDate() {
            return startDate;
        }

        public int getEndDate() {
            return endDate;
        }

        public String getUrl() {
            return url;
        }

        public String getImage() {
            return image;
        }

        public String getTag() {
            return tag;
        }

        public boolean isIsOpenH5() {
            return isOpenH5;
        }
    }
}
