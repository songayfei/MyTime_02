package com.atguigu.mytime.entity;

import java.util.List;

/**
 * 作者: Administrator on 2016/4/21 15:03
 * 说明:
 */
public class HomeSeekEntity {

    /**
     * suggestions : [{"id":44866,"type":1,"contentType":"电影","movieType":"惊悚 | 剧情 | 悬疑","isFilter":false,"titlecn":"好莱坞庄园","titleen":"Hollywoodland","rLocation":"美国","locationName":"美国","year":"2006","director":"艾伦·寇尔特","cover":"http://img31.mtime.cn/mt/2014/02/23/033626.96866518_1280X720X2.jpg"},{"id":215730,"type":1,"contentType":"电影","movieType":"犯罪 | 悬疑 | 惊悚","isFilter":false,"titlecn":"好好侦探","titleen":"The Nice Guys","rLocation":"英国","locationName":"英国","year":"2016","director":"沙恩·布莱克","cover":"http://img31.mtime.cn/mt/2016/03/19/122039.67051702_1280X720X2.jpg"},{"id":73246,"type":1,"contentType":"电影","movieType":"","isFilter":false,"titlecn":"好人好日","titleen":"Ko jin ko jitsu","rLocation":"日本","locationName":"日本","year":"1961","director":"涩谷实","cover":"http://img31.mtime.cn/mt/1246/73246/73246_1280X720X2.jpg"},{"id":5714,"type":2,"name":"好莱坞国际影城","address":"乌鲁木齐河南西路26号铁路局西单商场旁金实业..","cover":"http://img31.mtime.cn/t/2014/08/14/161449.43086409.jpg"}]
     * goodsCount : 5
     */

    private int goodsCount;
    /**
     * id : 44866
     * type : 1
     * contentType : 电影
     * movieType : 惊悚 | 剧情 | 悬疑
     * isFilter : false
     * titlecn : 好莱坞庄园
     * titleen : Hollywoodland
     * rLocation : 美国
     * locationName : 美国
     * year : 2006
     * director : 艾伦·寇尔特
     * cover : http://img31.mtime.cn/mt/2014/02/23/033626.96866518_1280X720X2.jpg
     */

    private List<SuggestionsEntity> suggestions;

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public void setSuggestions(List<SuggestionsEntity> suggestions) {
        this.suggestions = suggestions;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public List<SuggestionsEntity> getSuggestions() {
        return suggestions;
    }

    public static class SuggestionsEntity {
        private String name;//名称
        private String address;//地址

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private int id;
        private int type;
        private String contentType;
        private String movieType;
        private boolean isFilter;
        private String titlecn;
        private String titleen;
        private String rLocation;
        private String locationName;
        private String year;
        private String director;
        private String cover;

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public void setIsFilter(boolean isFilter) {
            this.isFilter = isFilter;
        }

        public void setTitlecn(String titlecn) {
            this.titlecn = titlecn;
        }

        public void setTitleen(String titleen) {
            this.titleen = titleen;
        }

        public void setRLocation(String rLocation) {
            this.rLocation = rLocation;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getContentType() {
            return contentType;
        }

        public String getMovieType() {
            return movieType;
        }

        public boolean isIsFilter() {
            return isFilter;
        }

        public String getTitlecn() {
            return titlecn;
        }

        public String getTitleen() {
            return titleen;
        }

        public String getRLocation() {
            return rLocation;
        }

        public String getLocationName() {
            return locationName;
        }

        public String getYear() {
            return year;
        }

        public String getDirector() {
            return director;
        }

        public String getCover() {
            return cover;
        }
    }
}
