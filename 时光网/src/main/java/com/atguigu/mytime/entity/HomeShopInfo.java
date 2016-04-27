package com.atguigu.mytime.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 * 主页商品信息实体类
 */
public class HomeShopInfo {


    /**
     * newsId : 0
     * topCover : http://img31.mtime.cn/mg/2016/03/20/112629.61804111.jpg
     * title :
     * movie : {}
     */

    private HotMovieEntity hotMovie;
    /**
     * subFirst : {"id":17728960,"goodsId":101319,"image":"","title":"限时闪购","titleColor":"#e75959","titleSmall":"小黄人充电宝","image2":"http://img31.mtime.cn/mg/2016/04/13/104634.82946858.jpg","gotoPage":{"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_1#!/commerce/item/101319/","parameters":{"goodsId":101319},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}
     * subSecond : {"id":17304115,"goodsId":102202,"image":"","title":"原创设计","titleColor":"#2b88ca","titleSmall":"蝙超大战手机壳","image2":"http://img31.mtime.cn/mg/2016/04/13/104716.74006789.jpg","gotoPage":{"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_2#!/commerce/item/102202/","parameters":{"goodsId":102202},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}
     * subThird : {"id":17304116,"goodsId":102194,"image":"","title":"炫酷型包","titleSmall":"凹造型必备！","titleColor":"#e45e5e","image2":"http://img31.mtime.cn/mg/2016/04/13/104755.46293077.jpg","gotoPage":{"gotoType":"gotogoodslist","url":"http://mall.wv.mtime.cn/?utm_source=app_home_5&q=%E8%83%8C%E5%8C%85&bid=2&utm_source=app_home_3#!/commerce/list/","parameters":{"keyword":0},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}
     * subFourth : {"goodsId":0,"image":"http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg","image2":"http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.cn/mobile/item/2015/preironman/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}
     * subFifth : {"id":18159583,"goodsId":0,"image":"","title":"蝙超周边","titleColor":"#9950D8","titleSmall":"造型经典 玩家必BUY","image2":"http://img31.mtime.cn/mg/2016/04/13/104521.35341013.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.com/mobile/item/2016/0401_bs/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}
     */

    private AreaSecondEntity areaSecond;
    /**
     * url : http://movie.mtime.com/207872/
     * img : http://img31.mtime.cn/mg/2016/04/12/084545.75284854.jpg
     * img2 : http://img31.mtime.cn/mg/2016/04/12/084550.29389093.jpg
     * gotoPage : {"gotoType":"gotomovie","url":"http://movie.mtime.com/207872/","parameters":{"movieId":207872},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
     */

    private List<AdvListEntity> advList;
    /**
     * id : 1554113
     * type : 0
     * tag :
     * title : 专访《伦敦陷落》五大主创
     * desc : 杰拉德巴特勒不认老 最爱周星驰电影
     * img : http://img31.mtime.cn/mg/2016/04/09/093329.66771815.jpg
     * img2 :
     * img3 :
     * publishTime : 1460191496
     * commentCount : 48
     */

    private List<HotPointsEntity> hotPoints;
    /**
     * advList : [{"url":"http://movie.mtime.com/207872/","img":"http://img31.mtime.cn/mg/2016/04/12/084545.75284854.jpg","img2":"http://img31.mtime.cn/mg/2016/04/12/084550.29389093.jpg","gotoPage":{"gotoType":"gotomovie","url":"http://movie.mtime.com/207872/","parameters":{"movieId":207872},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},{"url":"http://feature.mtime.cn/mobile/movie/2016/pufa/index.html","img":"http://img31.mtime.cn/mg/2016/04/11/084555.21779082.jpg","img2":"http://img31.mtime.cn/mg/2016/04/11/084559.36284274.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.cn/mobile/movie/2016/pufa/index.html","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},{"url":"http://mall.wv.mtime.cn/#!/commerce/item/102202/","img":"http://img31.mtime.cn/mg/2016/04/13/084322.19228026.jpg","img2":"http://img31.mtime.cn/mg/2016/04/13/084325.10601191.jpg","gotoPage":{"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/#!/commerce/item/102202/","parameters":{"goodsId":102202},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}]
     * hotPoints : [{"id":1554113,"type":0,"tag":"","title":"专访《伦敦陷落》五大主创","desc":"杰拉德巴特勒不认老 最爱周星驰电影","img":"http://img31.mtime.cn/mg/2016/04/09/093329.66771815.jpg","img2":"","img3":"","publishTime":1460191496,"commentCount":48},{"id":1554243,"type":0,"tag":"","title":"\"复联\"战友加盟《新蜘蛛侠》","desc":"多位漫威电影超级英雄将客串","img":"http://img31.mtime.cn/mg/2016/04/09/105751.45355345.jpg","img2":"","img3":"","publishTime":1460197901,"commentCount":177},{"id":1554253,"type":2,"tag":"","title":"\"冰火'第六季首曝片段","desc":"冰原狼白灵现身守护雪诺","img":"http://img31.mtime.cn/mg/2016/04/09/150155.37643263.jpg","img2":"","img3":"","publishTime":1460213381,"commentCount":104}]
     * hotPerson : {}
     * hotMovie : {"newsId":0,"topCover":"http://img31.mtime.cn/mg/2016/03/20/112629.61804111.jpg","title":"","movie":{}}
     * areaFirst : {}
     * areaSecond : {"subFirst":{"id":17728960,"goodsId":101319,"image":"","title":"限时闪购","titleColor":"#e75959","titleSmall":"小黄人充电宝","image2":"http://img31.mtime.cn/mg/2016/04/13/104634.82946858.jpg","gotoPage":{"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_1#!/commerce/item/101319/","parameters":{"goodsId":101319},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},"subSecond":{"id":17304115,"goodsId":102202,"image":"","title":"原创设计","titleColor":"#2b88ca","titleSmall":"蝙超大战手机壳","image2":"http://img31.mtime.cn/mg/2016/04/13/104716.74006789.jpg","gotoPage":{"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_2#!/commerce/item/102202/","parameters":{"goodsId":102202},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},"subThird":{"id":17304116,"goodsId":102194,"image":"","title":"炫酷型包","titleSmall":"凹造型必备！","titleColor":"#e45e5e","image2":"http://img31.mtime.cn/mg/2016/04/13/104755.46293077.jpg","gotoPage":{"gotoType":"gotogoodslist","url":"http://mall.wv.mtime.cn/?utm_source=app_home_5&q=%E8%83%8C%E5%8C%85&bid=2&utm_source=app_home_3#!/commerce/list/","parameters":{"keyword":0},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},"subFourth":{"goodsId":0,"image":"http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg","image2":"http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.cn/mobile/item/2015/preironman/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},"subFifth":{"id":18159583,"goodsId":0,"image":"","title":"蝙超周边","titleColor":"#9950D8","titleSmall":"造型经典 玩家必BUY","image2":"http://img31.mtime.cn/mg/2016/04/13/104521.35341013.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.com/mobile/item/2016/0401_bs/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}}
     * mallEntrys : []
     * topPosters : [{"url":"http://m.mtime.cn/#!/news/movie/1554371/","keyColor":"#888888","img":"http://img31.mtime.cn/mg/2016/04/13/130654.37623811.jpg","gotoPage":{"gotoType":"gotonews","url":"http://m.mtime.cn/#!/news/movie/1554371/","parameters":{"newId":1554371},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},{"url":"http://m.mtime.cn/#!/news/movie/1554280/","keyColor":"#888888","img":"http://img31.mtime.cn/mg/2016/04/12/100136.76177442.jpg","gotoPage":{"gotoType":"gotonews","url":"http://m.mtime.cn/#!/news/movie/1554280/","parameters":{"newId":1554280},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},{"url":"http://feature.mtime.cn/mobile/item/2016/0401_bs/","keyColor":"#000066","img":"http://img31.mtime.cn/mg/2016/04/08/114137.25391081.jpg","gotoPage":{"gotoType":"gotourl","url":"http://feature.mtime.cn/mobile/item/2016/0401_bs/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}},{"url":"http://m.mtime.cn/#!/mall/index/","keyColor":"#888888","img":"http://img31.mtime.cn/mg/2016/04/08/195528.10653431.jpg","gotoPage":{"gotoType":"gotomallindex","url":"http://m.mtime.cn/#!/mall/index/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}}]
     */

    private List<?> mallEntrys;
    /**
     * url : http://m.mtime.cn/#!/news/movie/1554371/
     * keyColor : #888888
     * img : http://img31.mtime.cn/mg/2016/04/13/130654.37623811.jpg
     * gotoPage : {"gotoType":"gotonews","url":"http://m.mtime.cn/#!/news/movie/1554371/","parameters":{"newId":1554371},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
     */

    private List<TopPostersEntity> topPosters;

    public void setHotMovie(HotMovieEntity hotMovie) {
        this.hotMovie = hotMovie;
    }

    public void setAreaSecond(AreaSecondEntity areaSecond) {
        this.areaSecond = areaSecond;
    }

    public void setAdvList(List<AdvListEntity> advList) {
        this.advList = advList;
    }

    public void setHotPoints(List<HotPointsEntity> hotPoints) {
        this.hotPoints = hotPoints;
    }

    public void setMallEntrys(List<?> mallEntrys) {
        this.mallEntrys = mallEntrys;
    }

    public void setTopPosters(List<TopPostersEntity> topPosters) {
        this.topPosters = topPosters;
    }

    public HotMovieEntity getHotMovie() {
        return hotMovie;
    }

    public AreaSecondEntity getAreaSecond() {
        return areaSecond;
    }

    public List<AdvListEntity> getAdvList() {
        return advList;
    }

    public List<HotPointsEntity> getHotPoints() {
        return hotPoints;
    }

    public List<?> getMallEntrys() {
        return mallEntrys;
    }

    public List<TopPostersEntity> getTopPosters() {
        return topPosters;
    }

    public static class HotMovieEntity {
        private int newsId;
        private String topCover;
        private String title;

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public void setTopCover(String topCover) {
            this.topCover = topCover;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNewsId() {
            return newsId;
        }

        public String getTopCover() {
            return topCover;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class AreaSecondEntity {
        /**
         * id : 17728960
         * goodsId : 101319
         * image :
         * title : 限时闪购
         * titleColor : #e75959
         * titleSmall : 小黄人充电宝
         * image2 : http://img31.mtime.cn/mg/2016/04/13/104634.82946858.jpg
         * gotoPage : {"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_1#!/commerce/item/101319/","parameters":{"goodsId":101319},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
         */

        private SubFirstEntity subFirst;
        /**
         * id : 17304115
         * goodsId : 102202
         * image :
         * title : 原创设计
         * titleColor : #2b88ca
         * titleSmall : 蝙超大战手机壳
         * image2 : http://img31.mtime.cn/mg/2016/04/13/104716.74006789.jpg
         * gotoPage : {"gotoType":"gotogoodsinfo","url":"http://mall.wv.mtime.cn/?utm_source=app_home_2#!/commerce/item/102202/","parameters":{"goodsId":102202},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
         */

        private SubSecondEntity subSecond;
        /**
         * id : 17304116
         * goodsId : 102194
         * image :
         * title : 炫酷型包
         * titleSmall : 凹造型必备！
         * titleColor : #e45e5e
         * image2 : http://img31.mtime.cn/mg/2016/04/13/104755.46293077.jpg
         * gotoPage : {"gotoType":"gotogoodslist","url":"http://mall.wv.mtime.cn/?utm_source=app_home_5&q=%E8%83%8C%E5%8C%85&bid=2&utm_source=app_home_3#!/commerce/list/","parameters":{"keyword":0},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
         */

        private SubThirdEntity subThird;
        /**
         * goodsId : 0
         * image : http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg
         * image2 : http://img31.mtime.cn/mg/2016/03/18/105503.44806826.jpg
         * gotoPage : {"gotoType":"gotourl","url":"http://feature.mtime.cn/mobile/item/2015/preironman/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
         */

        private SubFourthEntity subFourth;
        /**
         * id : 18159583
         * goodsId : 0
         * image :
         * title : 蝙超周边
         * titleColor : #9950D8
         * titleSmall : 造型经典 玩家必BUY
         * image2 : http://img31.mtime.cn/mg/2016/04/13/104521.35341013.jpg
         * gotoPage : {"gotoType":"gotourl","url":"http://feature.mtime.com/mobile/item/2016/0401_bs/","parameters":{},"parameters1":{},"isGoH5":false,"relatedTypeUrl":""}
         */

        private SubFifthEntity subFifth;

        public void setSubFirst(SubFirstEntity subFirst) {
            this.subFirst = subFirst;
        }

        public void setSubSecond(SubSecondEntity subSecond) {
            this.subSecond = subSecond;
        }

        public void setSubThird(SubThirdEntity subThird) {
            this.subThird = subThird;
        }

        public void setSubFourth(SubFourthEntity subFourth) {
            this.subFourth = subFourth;
        }

        public void setSubFifth(SubFifthEntity subFifth) {
            this.subFifth = subFifth;
        }

        public SubFirstEntity getSubFirst() {
            return subFirst;
        }

        public SubSecondEntity getSubSecond() {
            return subSecond;
        }

        public SubThirdEntity getSubThird() {
            return subThird;
        }

        public SubFourthEntity getSubFourth() {
            return subFourth;
        }

        public SubFifthEntity getSubFifth() {
            return subFifth;
        }

        public static class SubFirstEntity {
            private int id;
            private int goodsId;
            private String image;
            private String title;
            private String titleColor;
            private String titleSmall;
            private String image2;
            /**
             * gotoType : gotogoodsinfo
             * url : http://mall.wv.mtime.cn/?utm_source=app_home_1#!/commerce/item/101319/
             * parameters : {"goodsId":101319}
             * parameters1 : {}
             * isGoH5 : false
             * relatedTypeUrl :
             */

            private GotoPageEntity gotoPage;

            public void setId(int id) {
                this.id = id;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public void setTitleSmall(String titleSmall) {
                this.titleSmall = titleSmall;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public void setGotoPage(GotoPageEntity gotoPage) {
                this.gotoPage = gotoPage;
            }

            public int getId() {
                return id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public String getTitleSmall() {
                return titleSmall;
            }

            public String getImage2() {
                return image2;
            }

            public GotoPageEntity getGotoPage() {
                return gotoPage;
            }

            public static class GotoPageEntity {
                private String gotoType;
                private String url;
                /**
                 * goodsId : 101319
                 */

                private ParametersEntity parameters;
                private Parameters1Entity parameters1;
                private boolean isGoH5;
                private String relatedTypeUrl;

                public void setGotoType(String gotoType) {
                    this.gotoType = gotoType;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setParameters(ParametersEntity parameters) {
                    this.parameters = parameters;
                }

                public void setParameters1(Parameters1Entity parameters1) {
                    this.parameters1 = parameters1;
                }

                public void setIsGoH5(boolean isGoH5) {
                    this.isGoH5 = isGoH5;
                }

                public void setRelatedTypeUrl(String relatedTypeUrl) {
                    this.relatedTypeUrl = relatedTypeUrl;
                }

                public String getGotoType() {
                    return gotoType;
                }

                public String getUrl() {
                    return url;
                }

                public ParametersEntity getParameters() {
                    return parameters;
                }

                public Parameters1Entity getParameters1() {
                    return parameters1;
                }

                public boolean isIsGoH5() {
                    return isGoH5;
                }

                public String getRelatedTypeUrl() {
                    return relatedTypeUrl;
                }

                public static class ParametersEntity {
                }

                public static class Parameters1Entity {
                }
            }
        }

        public static class SubSecondEntity {
            private int id;
            private int goodsId;
            private String image;
            private String title;
            private String titleColor;
            private String titleSmall;
            private String image2;
            /**
             * gotoType : gotogoodsinfo
             * url : http://mall.wv.mtime.cn/?utm_source=app_home_2#!/commerce/item/102202/
             * parameters : {"goodsId":102202}
             * parameters1 : {}
             * isGoH5 : false
             * relatedTypeUrl :
             */

            private GotoPageEntity gotoPage;

            public void setId(int id) {
                this.id = id;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public void setTitleSmall(String titleSmall) {
                this.titleSmall = titleSmall;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public void setGotoPage(GotoPageEntity gotoPage) {
                this.gotoPage = gotoPage;
            }

            public int getId() {
                return id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public String getTitleSmall() {
                return titleSmall;
            }

            public String getImage2() {
                return image2;
            }

            public GotoPageEntity getGotoPage() {
                return gotoPage;
            }

            public static class GotoPageEntity {
                private String gotoType;
                private String url;
                /**
                 * goodsId : 102202
                 */

                private ParametersEntity parameters;
                private Parameters1Entity parameters1;
                private boolean isGoH5;
                private String relatedTypeUrl;

                public void setGotoType(String gotoType) {
                    this.gotoType = gotoType;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setParameters(ParametersEntity parameters) {
                    this.parameters = parameters;
                }

                public void setParameters1(Parameters1Entity parameters1) {
                    this.parameters1 = parameters1;
                }

                public void setIsGoH5(boolean isGoH5) {
                    this.isGoH5 = isGoH5;
                }

                public void setRelatedTypeUrl(String relatedTypeUrl) {
                    this.relatedTypeUrl = relatedTypeUrl;
                }

                public String getGotoType() {
                    return gotoType;
                }

                public String getUrl() {
                    return url;
                }

                public ParametersEntity getParameters() {
                    return parameters;
                }

                public Parameters1Entity getParameters1() {
                    return parameters1;
                }

                public boolean isIsGoH5() {
                    return isGoH5;
                }

                public String getRelatedTypeUrl() {
                    return relatedTypeUrl;
                }

                public static class ParametersEntity {
                }

                public static class Parameters1Entity {
                }
            }
        }

        public static class SubThirdEntity {
            private int id;
            private int goodsId;
            private String image;
            private String title;
            private String titleSmall;
            private String titleColor;
            private String image2;
            /**
             * gotoType : gotogoodslist
             * url : http://mall.wv.mtime.cn/?utm_source=app_home_5&q=%E8%83%8C%E5%8C%85&bid=2&utm_source=app_home_3#!/commerce/list/
             * parameters : {"keyword":0}
             * parameters1 : {}
             * isGoH5 : false
             * relatedTypeUrl :
             */

            private GotoPageEntity gotoPage;

            public void setId(int id) {
                this.id = id;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTitleSmall(String titleSmall) {
                this.titleSmall = titleSmall;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public void setGotoPage(GotoPageEntity gotoPage) {
                this.gotoPage = gotoPage;
            }

            public int getId() {
                return id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getTitleSmall() {
                return titleSmall;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public String getImage2() {
                return image2;
            }

            public GotoPageEntity getGotoPage() {
                return gotoPage;
            }

            public static class GotoPageEntity {
                private String gotoType;
                private String url;
                /**
                 * keyword : 0
                 */

                private ParametersEntity parameters;
                private Parameters1Entity parameters1;
                private boolean isGoH5;
                private String relatedTypeUrl;

                public void setGotoType(String gotoType) {
                    this.gotoType = gotoType;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setParameters(ParametersEntity parameters) {
                    this.parameters = parameters;
                }

                public void setParameters1(Parameters1Entity parameters1) {
                    this.parameters1 = parameters1;
                }

                public void setIsGoH5(boolean isGoH5) {
                    this.isGoH5 = isGoH5;
                }

                public void setRelatedTypeUrl(String relatedTypeUrl) {
                    this.relatedTypeUrl = relatedTypeUrl;
                }

                public String getGotoType() {
                    return gotoType;
                }

                public String getUrl() {
                    return url;
                }

                public ParametersEntity getParameters() {
                    return parameters;
                }

                public Parameters1Entity getParameters1() {
                    return parameters1;
                }

                public boolean isIsGoH5() {
                    return isGoH5;
                }

                public String getRelatedTypeUrl() {
                    return relatedTypeUrl;
                }

                public static class ParametersEntity {
                }

                public static class Parameters1Entity {
                }
            }
        }

        public static class SubFourthEntity {
            private int goodsId;
            private String image;
            private String image2;
            /**
             * gotoType : gotourl
             * url : http://feature.mtime.cn/mobile/item/2015/preironman/
             * parameters : {}
             * parameters1 : {}
             * isGoH5 : false
             * relatedTypeUrl :
             */

            private GotoPageEntity gotoPage;

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public void setGotoPage(GotoPageEntity gotoPage) {
                this.gotoPage = gotoPage;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getImage2() {
                return image2;
            }

            public GotoPageEntity getGotoPage() {
                return gotoPage;
            }

            public static class GotoPageEntity {
                private String gotoType;
                private String url;
                private ParametersEntity parameters;
                private Parameters1Entity parameters1;
                private boolean isGoH5;
                private String relatedTypeUrl;

                public void setGotoType(String gotoType) {
                    this.gotoType = gotoType;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setParameters(ParametersEntity parameters) {
                    this.parameters = parameters;
                }

                public void setParameters1(Parameters1Entity parameters1) {
                    this.parameters1 = parameters1;
                }

                public void setIsGoH5(boolean isGoH5) {
                    this.isGoH5 = isGoH5;
                }

                public void setRelatedTypeUrl(String relatedTypeUrl) {
                    this.relatedTypeUrl = relatedTypeUrl;
                }

                public String getGotoType() {
                    return gotoType;
                }

                public String getUrl() {
                    return url;
                }

                public ParametersEntity getParameters() {
                    return parameters;
                }

                public Parameters1Entity getParameters1() {
                    return parameters1;
                }

                public boolean isIsGoH5() {
                    return isGoH5;
                }

                public String getRelatedTypeUrl() {
                    return relatedTypeUrl;
                }

                public static class ParametersEntity {
                }

                public static class Parameters1Entity {
                }
            }
        }

        public static class SubFifthEntity {
            private int id;
            private int goodsId;
            private String image;
            private String title;
            private String titleColor;
            private String titleSmall;
            private String image2;
            /**
             * gotoType : gotourl
             * url : http://feature.mtime.com/mobile/item/2016/0401_bs/
             * parameters : {}
             * parameters1 : {}
             * isGoH5 : false
             * relatedTypeUrl :
             */

            private GotoPageEntity gotoPage;

            public void setId(int id) {
                this.id = id;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public void setTitleSmall(String titleSmall) {
                this.titleSmall = titleSmall;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public void setGotoPage(GotoPageEntity gotoPage) {
                this.gotoPage = gotoPage;
            }

            public int getId() {
                return id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public String getTitleSmall() {
                return titleSmall;
            }

            public String getImage2() {
                return image2;
            }

            public GotoPageEntity getGotoPage() {
                return gotoPage;
            }

            public static class GotoPageEntity {
                private String gotoType;
                private String url;
                private ParametersEntity parameters;
                private Parameters1Entity parameters1;
                private boolean isGoH5;
                private String relatedTypeUrl;

                public void setGotoType(String gotoType) {
                    this.gotoType = gotoType;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setParameters(ParametersEntity parameters) {
                    this.parameters = parameters;
                }

                public void setParameters1(Parameters1Entity parameters1) {
                    this.parameters1 = parameters1;
                }

                public void setIsGoH5(boolean isGoH5) {
                    this.isGoH5 = isGoH5;
                }

                public void setRelatedTypeUrl(String relatedTypeUrl) {
                    this.relatedTypeUrl = relatedTypeUrl;
                }

                public String getGotoType() {
                    return gotoType;
                }

                public String getUrl() {
                    return url;
                }

                public ParametersEntity getParameters() {
                    return parameters;
                }

                public Parameters1Entity getParameters1() {
                    return parameters1;
                }

                public boolean isIsGoH5() {
                    return isGoH5;
                }

                public String getRelatedTypeUrl() {
                    return relatedTypeUrl;
                }

                public static class ParametersEntity {
                }

                public static class Parameters1Entity {
                }
            }
        }
    }

    public static class AdvListEntity {
        private String url;
        private String img;
        private String img2;
        /**
         * gotoType : gotomovie
         * url : http://movie.mtime.com/207872/
         * parameters : {"movieId":207872}
         * parameters1 : {}
         * isGoH5 : false
         * relatedTypeUrl :
         */

        private GotoPageEntity gotoPage;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public void setGotoPage(GotoPageEntity gotoPage) {
            this.gotoPage = gotoPage;
        }

        public String getUrl() {
            return url;
        }

        public String getImg() {
            return img;
        }

        public String getImg2() {
            return img2;
        }

        public GotoPageEntity getGotoPage() {
            return gotoPage;
        }

        public static class GotoPageEntity {
            private String gotoType;
            private String url;
            /**
             * movieId : 207872
             */

            private ParametersEntity parameters;
            private Parameters1Entity parameters1;
            private boolean isGoH5;
            private String relatedTypeUrl;

            public void setGotoType(String gotoType) {
                this.gotoType = gotoType;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setParameters(ParametersEntity parameters) {
                this.parameters = parameters;
            }

            public void setParameters1(Parameters1Entity parameters1) {
                this.parameters1 = parameters1;
            }

            public void setIsGoH5(boolean isGoH5) {
                this.isGoH5 = isGoH5;
            }

            public void setRelatedTypeUrl(String relatedTypeUrl) {
                this.relatedTypeUrl = relatedTypeUrl;
            }

            public String getGotoType() {
                return gotoType;
            }

            public String getUrl() {
                return url;
            }

            public ParametersEntity getParameters() {
                return parameters;
            }

            public Parameters1Entity getParameters1() {
                return parameters1;
            }

            public boolean isIsGoH5() {
                return isGoH5;
            }

            public String getRelatedTypeUrl() {
                return relatedTypeUrl;
            }

            public static class ParametersEntity {
            }

            public static class Parameters1Entity {
            }
        }
    }

    public static class HotPointsEntity {
        private int id;
        private int type;
        private String tag;
        private String title;
        private String desc;
        private String img;
        private String img2;
        private String img3;
        private int publishTime;
        private int commentCount;

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public void setPublishTime(int publishTime) {
            this.publishTime = publishTime;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getTag() {
            return tag;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getImg() {
            return img;
        }

        public String getImg2() {
            return img2;
        }

        public String getImg3() {
            return img3;
        }

        public int getPublishTime() {
            return publishTime;
        }

        public int getCommentCount() {
            return commentCount;
        }
    }

    public static class TopPostersEntity {
        private String url;
        private String keyColor;
        private String img;
        /**
         * gotoType : gotonews
         * url : http://m.mtime.cn/#!/news/movie/1554371/
         * parameters : {"newId":1554371}
         * parameters1 : {}
         * isGoH5 : false
         * relatedTypeUrl :
         */

        private GotoPageEntity gotoPage;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setKeyColor(String keyColor) {
            this.keyColor = keyColor;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setGotoPage(GotoPageEntity gotoPage) {
            this.gotoPage = gotoPage;
        }

        public String getUrl() {
            return url;
        }

        public String getKeyColor() {
            return keyColor;
        }

        public String getImg() {
            return img;
        }

        public GotoPageEntity getGotoPage() {
            return gotoPage;
        }

        public static class GotoPageEntity {
            private String gotoType;
            private String url;
            /**
             * newId : 1554371
             */

            private ParametersEntity parameters;
            private Parameters1Entity parameters1;
            private boolean isGoH5;
            private String relatedTypeUrl;

            public void setGotoType(String gotoType) {
                this.gotoType = gotoType;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setParameters(ParametersEntity parameters) {
                this.parameters = parameters;
            }

            public void setParameters1(Parameters1Entity parameters1) {
                this.parameters1 = parameters1;
            }

            public void setIsGoH5(boolean isGoH5) {
                this.isGoH5 = isGoH5;
            }

            public void setRelatedTypeUrl(String relatedTypeUrl) {
                this.relatedTypeUrl = relatedTypeUrl;
            }

            public String getGotoType() {
                return gotoType;
            }

            public String getUrl() {
                return url;
            }

            public ParametersEntity getParameters() {
                return parameters;
            }

            public Parameters1Entity getParameters1() {
                return parameters1;
            }

            public boolean isIsGoH5() {
                return isGoH5;
            }

            public String getRelatedTypeUrl() {
                return relatedTypeUrl;
            }

            public static class ParametersEntity {
            }

            public static class Parameters1Entity {
            }
        }
    }
}
