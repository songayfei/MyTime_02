package com.atguigu.mytime.entity;

import java.util.List;

/**
 * @Author YfSong
 * @Time 20:12
 * Created by YfSong on 2016/4/12.
 */
public class SerachGoodsBean {

    /**
     * brand : {"brandList":[{"brandId":2,"name":"影时光"},{"brandId":22,"name":"FUNKO"}],"isShowBrand":true}
     * category : {"categoryFilter":"category1","categoryList":[{"categoryId":8,"name":"数码周边"},{"categoryId":42,"name":"服饰箱包"},{"categoryId":43,"name":"居家生活"},{"categoryId":25,"name":"玩具模型"}]}
     * goods : {"goodsList":[{"background":"#28C8DC","goodsId":101319,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/08/25/194017.59965372_294X294X4.jpg","marketPrice":38800,"minSalePrice":14900,"name":"大眼萌小黄人充电宝","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101319/"},{"background":"#FF0000","goodsId":101407,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/09/10/163813.64233507_294X294X4.jpg","marketPrice":49900,"minSalePrice":38900,"name":"小黄人抱熊跳豆语音玩具","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101407/"},{"background":"#FF0000","goodsId":101331,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/115252.11785115_294X294X4.jpg","marketPrice":12900,"minSalePrice":2000,"name":"大眼萌小黄人双层吸管杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101331/?pvkey=405_1246"},{"background":"#FF0000","goodsId":101332,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/131801.30942455_294X294X4.jpg","marketPrice":8000,"minSalePrice":1900,"name":"大眼萌小黄人吸管杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101332/?pvkey=405_1246"},{"background":"#FF0000","goodsId":101332,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/131906.99798670_294X294X4.jpg","marketPrice":8000,"minSalePrice":1900,"name":"大眼萌小黄人吸管杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101332/?pvkey=405_1250"},{"background":"#FF0000","goodsId":101331,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/115326.37177005_294X294X4.jpg","marketPrice":12900,"minSalePrice":2000,"name":"大眼萌小黄人双层吸管杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101331/?pvkey=405_1250"},{"background":"#28C8DC","goodsId":101368,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/09/18/172808.78929656_294X294X4.jpg","marketPrice":12900,"minSalePrice":3500,"name":"大眼萌小黄人立体饮料杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101368/?pvkey=405_1246"},{"background":"#28C8DC","goodsId":101368,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/09/18/172829.20210031_294X294X4.jpg","marketPrice":12900,"minSalePrice":3500,"name":"大眼萌小黄人立体饮料杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101368/?pvkey=405_1250"},{"background":"#FF0000","goodsId":101330,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/163655.13972083_294X294X4.jpg","marketPrice":37900,"minSalePrice":11900,"name":"大眼萌小黄人耳机 A款","primaryPropertyID":502,"primaryPropertyValueID":804,"url":"http://m.mtime.cn/#!/commerce/item/101330/?pvkey=502_804"},{"background":"#28C8DC","goodsId":101229,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/160950.92907664_294X294X4.jpg","marketPrice":25900,"minSalePrice":9900,"name":"大眼萌小黄人开心套装","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101229/"}]}
     * showTopic : {"topicList":[{"img":"http://img31.mtime.cn/mg/2015/01/12/170607.90475724_50X50X4.jpg","relatedType":0,"title":"小黄人","topicId":17},{"img":"http://img31.mtime.cn/mg/2015/03/12/164547.18855926_50X50X4.jpg","relatedType":0,"title":"卡通系列","topicId":25}],"topicMovieList":[],"topicRoleList":[]}
     */

    private ContentEntity content;
    /**
     * brands : [{"Alt":"影时光","ID":2,"Logo":"http://img31.mtime.cn/mg/2015/01/14/140131.76844476_135X135X4.jpg","NameCN":"影时光","NameEN":"Mtime","Story":"<p>创建于2005年，致力于为电影爱好者提供一站式的电影生活服务。<\/p><p>· 获得了迪士尼、漫威、DC、环球等品牌的正版授权，与多家顶级授权商和知名品..."},{"Alt":"FUNKO","ID":22,"Logo":"http://img31.mtime.cn/mg/2015/02/26/145401.52842933_135X135X4.jpg","NameCN":"FUNKO","NameEN":"FUNKO","Story":"funko"}]
     * content : {"brand":{"brandList":[{"brandId":2,"name":"影时光"},{"brandId":22,"name":"FUNKO"}],"isShowBrand":true},"category":{"categoryFilter":"category1","categoryList":[{"categoryId":8,"name":"数码周边"},{"categoryId":42,"name":"服饰箱包"},{"categoryId":43,"name":"居家生活"},{"categoryId":25,"name":"玩具模型"}]},"goods":{"goodsList":[{"background":"#28C8DC","goodsId":101319,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/08/25/194017.59965372_294X294X4.jpg","marketPrice":38800,"minSalePrice":14900,"name":"大眼萌小黄人充电宝","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101319/"},{"background":"#FF0000","goodsId":101407,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/09/10/163813.64233507_294X294X4.jpg","marketPrice":49900,"minSalePrice":38900,"name":"小黄人抱熊跳豆语音玩具","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101407/"},{"background":"#FF0000","goodsId":101331,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/115252.11785115_294X294X4.jpg","marketPrice":12900,"minSalePrice":2000,"name":"大眼萌小黄人双层吸管杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101331/?pvkey=405_1246"},{"background":"#FF0000","goodsId":101332,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/131801.30942455_294X294X4.jpg","marketPrice":8000,"minSalePrice":1900,"name":"大眼萌小黄人吸管杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101332/?pvkey=405_1246"},{"background":"#FF0000","goodsId":101332,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/131906.99798670_294X294X4.jpg","marketPrice":8000,"minSalePrice":1900,"name":"大眼萌小黄人吸管杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101332/?pvkey=405_1250"},{"background":"#FF0000","goodsId":101331,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/115326.37177005_294X294X4.jpg","marketPrice":12900,"minSalePrice":2000,"name":"大眼萌小黄人双层吸管杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101331/?pvkey=405_1250"},{"background":"#28C8DC","goodsId":101368,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/09/18/172808.78929656_294X294X4.jpg","marketPrice":12900,"minSalePrice":3500,"name":"大眼萌小黄人立体饮料杯 小黄人海盗","primaryPropertyID":405,"primaryPropertyValueID":1246,"url":"http://m.mtime.cn/#!/commerce/item/101368/?pvkey=405_1246"},{"background":"#28C8DC","goodsId":101368,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/09/18/172829.20210031_294X294X4.jpg","marketPrice":12900,"minSalePrice":3500,"name":"大眼萌小黄人立体饮料杯 小黄人古埃及","primaryPropertyID":405,"primaryPropertyValueID":1250,"url":"http://m.mtime.cn/#!/commerce/item/101368/?pvkey=405_1250"},{"background":"#FF0000","goodsId":101330,"iconText":"立减","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/163655.13972083_294X294X4.jpg","marketPrice":37900,"minSalePrice":11900,"name":"大眼萌小黄人耳机 A款","primaryPropertyID":502,"primaryPropertyValueID":804,"url":"http://m.mtime.cn/#!/commerce/item/101330/?pvkey=502_804"},{"background":"#28C8DC","goodsId":101229,"iconText":"","imageSrc":"http://img31.mtime.cn/mg/2015/08/24/160950.92907664_294X294X4.jpg","marketPrice":25900,"minSalePrice":9900,"name":"大眼萌小黄人开心套装","primaryPropertyID":0,"primaryPropertyValueID":0,"url":"http://m.mtime.cn/#!/commerce/item/101229/"}]},"showTopic":{"topicList":[{"img":"http://img31.mtime.cn/mg/2015/01/12/170607.90475724_50X50X4.jpg","relatedType":0,"title":"小黄人","topicId":17},{"img":"http://img31.mtime.cn/mg/2015/03/12/164547.18855926_50X50X4.jpg","relatedType":0,"title":"卡通系列","topicId":25}],"topicMovieList":[],"topicRoleList":[]}}
     * pageCount : 5
     * pageSize : 10
     * pageTitle : 小黄人
     * recommends : []
     * searchUrl :
     * topics : [{"Alt":"小黄人","ID":17,"Logo":"http://img31.mtime.cn/mg/2015/02/10/110235.90300948_135X135X4.jpg","ShortTitle":"小黄人","Summary":"小黄人是格鲁的黄色小兵军团，他们思想怪异，行为古怪，但是拥有一颗善良的心。因为是香蕉泥组成的，所以小黄人们酷爱香蕉。"},{"Alt":"卡通系列","ID":25,"Logo":"http://img31.mtime.cn/mg/2015/03/12/170152.15810764_135X135X4.jpg","ShortTitle":"卡通系列","Summary":"冰雪奇缘、神偷奶爸、HelloKitty等风靡全球的卡通形象，他们出现在荧屏各界。老少皆宜，深受喜爱。"}]
     * totalCount : 49
     */

    private int pageCount;
    private int pageSize;
    private String pageTitle;
    private String searchUrl;
    private int totalCount;
    /**
     * Alt : 影时光
     * ID : 2
     * Logo : http://img31.mtime.cn/mg/2015/01/14/140131.76844476_135X135X4.jpg
     * NameCN : 影时光
     * NameEN : Mtime
     * Story : <p>创建于2005年，致力于为电影爱好者提供一站式的电影生活服务。</p><p>· 获得了迪士尼、漫威、DC、环球等品牌的正版授权，与多家顶级授权商和知名品...
     */

    private List<BrandsEntity> brands;
    private List<?> recommends;
    /**
     * Alt : 小黄人
     * ID : 17
     * Logo : http://img31.mtime.cn/mg/2015/02/10/110235.90300948_135X135X4.jpg
     * ShortTitle : 小黄人
     * Summary : 小黄人是格鲁的黄色小兵军团，他们思想怪异，行为古怪，但是拥有一颗善良的心。因为是香蕉泥组成的，所以小黄人们酷爱香蕉。
     */

    private List<TopicsEntity> topics;

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setBrands(List<BrandsEntity> brands) {
        this.brands = brands;
    }

    public void setRecommends(List<?> recommends) {
        this.recommends = recommends;
    }

    public void setTopics(List<TopicsEntity> topics) {
        this.topics = topics;
    }

    public ContentEntity getContent() {
        return content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<BrandsEntity> getBrands() {
        return brands;
    }

    public List<?> getRecommends() {
        return recommends;
    }

    public List<TopicsEntity> getTopics() {
        return topics;
    }

    public static class ContentEntity {
        /**
         * brandList : [{"brandId":2,"name":"影时光"},{"brandId":22,"name":"FUNKO"}]
         * isShowBrand : true
         */

        private BrandEntity brand;
        /**
         * categoryFilter : category1
         * categoryList : [{"categoryId":8,"name":"数码周边"},{"categoryId":42,"name":"服饰箱包"},{"categoryId":43,"name":"居家生活"},{"categoryId":25,"name":"玩具模型"}]
         */

        private CategoryEntity category;
        private GoodsEntity goods;
        private ShowTopicEntity showTopic;

        public void setBrand(BrandEntity brand) {
            this.brand = brand;
        }

        public void setCategory(CategoryEntity category) {
            this.category = category;
        }

        public void setGoods(GoodsEntity goods) {
            this.goods = goods;
        }

        public void setShowTopic(ShowTopicEntity showTopic) {
            this.showTopic = showTopic;
        }

        public BrandEntity getBrand() {
            return brand;
        }

        public CategoryEntity getCategory() {
            return category;
        }

        public GoodsEntity getGoods() {
            return goods;
        }

        public ShowTopicEntity getShowTopic() {
            return showTopic;
        }

        public static class BrandEntity {
            private boolean isShowBrand;
            /**
             * brandId : 2
             * name : 影时光
             */

            private List<BrandListEntity> brandList;

            public void setIsShowBrand(boolean isShowBrand) {
                this.isShowBrand = isShowBrand;
            }

            public void setBrandList(List<BrandListEntity> brandList) {
                this.brandList = brandList;
            }

            public boolean isIsShowBrand() {
                return isShowBrand;
            }

            public List<BrandListEntity> getBrandList() {
                return brandList;
            }

            public static class BrandListEntity {
                private int brandId;
                private String name;

                public void setBrandId(int brandId) {
                    this.brandId = brandId;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getBrandId() {
                    return brandId;
                }

                public String getName() {
                    return name;
                }
            }
        }

        public static class CategoryEntity {
            private String categoryFilter;
            /**
             * categoryId : 8
             * name : 数码周边
             */

            private List<CategoryListEntity> categoryList;

            public void setCategoryFilter(String categoryFilter) {
                this.categoryFilter = categoryFilter;
            }

            public void setCategoryList(List<CategoryListEntity> categoryList) {
                this.categoryList = categoryList;
            }

            public String getCategoryFilter() {
                return categoryFilter;
            }

            public List<CategoryListEntity> getCategoryList() {
                return categoryList;
            }

            public static class CategoryListEntity {
                private int categoryId;
                private String name;

                public void setCategoryId(int categoryId) {
                    this.categoryId = categoryId;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getCategoryId() {
                    return categoryId;
                }

                public String getName() {
                    return name;
                }
            }
        }

        public static class GoodsEntity {
            /**
             * background : #28C8DC
             * goodsId : 101319
             * iconText :
             * imageSrc : http://img31.mtime.cn/mg/2015/08/25/194017.59965372_294X294X4.jpg
             * marketPrice : 38800
             * minSalePrice : 14900
             * name : 大眼萌小黄人充电宝
             * primaryPropertyID : 0
             * primaryPropertyValueID : 0
             * url : http://m.mtime.cn/#!/commerce/item/101319/
             */

            private List<GoodsListEntity> goodsList;

            public void setGoodsList(List<GoodsListEntity> goodsList) {
                this.goodsList = goodsList;
            }

            public List<GoodsListEntity> getGoodsList() {
                return goodsList;
            }

            public static class GoodsListEntity {
                private String background;
                private int goodsId;
                private String iconText;
                private String imageSrc;
                private int marketPrice;
                private int minSalePrice;
                private String name;
                private int primaryPropertyID;
                private int primaryPropertyValueID;
                private String url;

                public void setBackground(String background) {
                    this.background = background;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public void setIconText(String iconText) {
                    this.iconText = iconText;
                }

                public void setImageSrc(String imageSrc) {
                    this.imageSrc = imageSrc;
                }

                public void setMarketPrice(int marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public void setMinSalePrice(int minSalePrice) {
                    this.minSalePrice = minSalePrice;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setPrimaryPropertyID(int primaryPropertyID) {
                    this.primaryPropertyID = primaryPropertyID;
                }

                public void setPrimaryPropertyValueID(int primaryPropertyValueID) {
                    this.primaryPropertyValueID = primaryPropertyValueID;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getBackground() {
                    return background;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public String getIconText() {
                    return iconText;
                }

                public String getImageSrc() {
                    return imageSrc;
                }

                public int getMarketPrice() {
                    return marketPrice;
                }

                public int getMinSalePrice() {
                    return minSalePrice;
                }

                public String getName() {
                    return name;
                }

                public int getPrimaryPropertyID() {
                    return primaryPropertyID;
                }

                public int getPrimaryPropertyValueID() {
                    return primaryPropertyValueID;
                }

                public String getUrl() {
                    return url;
                }
            }
        }

        public static class ShowTopicEntity {
            /**
             * img : http://img31.mtime.cn/mg/2015/01/12/170607.90475724_50X50X4.jpg
             * relatedType : 0
             * title : 小黄人
             * topicId : 17
             */

            private List<TopicListEntity> topicList;
            private List<?> topicMovieList;
            private List<?> topicRoleList;

            public void setTopicList(List<TopicListEntity> topicList) {
                this.topicList = topicList;
            }

            public void setTopicMovieList(List<?> topicMovieList) {
                this.topicMovieList = topicMovieList;
            }

            public void setTopicRoleList(List<?> topicRoleList) {
                this.topicRoleList = topicRoleList;
            }

            public List<TopicListEntity> getTopicList() {
                return topicList;
            }

            public List<?> getTopicMovieList() {
                return topicMovieList;
            }

            public List<?> getTopicRoleList() {
                return topicRoleList;
            }

            public static class TopicListEntity {
                private String img;
                private int relatedType;
                private String title;
                private int topicId;

                public void setImg(String img) {
                    this.img = img;
                }

                public void setRelatedType(int relatedType) {
                    this.relatedType = relatedType;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setTopicId(int topicId) {
                    this.topicId = topicId;
                }

                public String getImg() {
                    return img;
                }

                public int getRelatedType() {
                    return relatedType;
                }

                public String getTitle() {
                    return title;
                }

                public int getTopicId() {
                    return topicId;
                }
            }
        }
    }

    public static class BrandsEntity {
        private String Alt;
        private int ID;
        private String Logo;
        private String NameCN;
        private String NameEN;
        private String Story;

        public void setAlt(String Alt) {
            this.Alt = Alt;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public void setNameCN(String NameCN) {
            this.NameCN = NameCN;
        }

        public void setNameEN(String NameEN) {
            this.NameEN = NameEN;
        }

        public void setStory(String Story) {
            this.Story = Story;
        }

        public String getAlt() {
            return Alt;
        }

        public int getID() {
            return ID;
        }

        public String getLogo() {
            return Logo;
        }

        public String getNameCN() {
            return NameCN;
        }

        public String getNameEN() {
            return NameEN;
        }

        public String getStory() {
            return Story;
        }
    }

    public static class TopicsEntity {
        private String Alt;
        private int ID;
        private String Logo;
        private String ShortTitle;
        private String Summary;

        public void setAlt(String Alt) {
            this.Alt = Alt;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public void setShortTitle(String ShortTitle) {
            this.ShortTitle = ShortTitle;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getAlt() {
            return Alt;
        }

        public int getID() {
            return ID;
        }

        public String getLogo() {
            return Logo;
        }

        public String getShortTitle() {
            return ShortTitle;
        }

        public String getSummary() {
            return Summary;
        }
    }
}
