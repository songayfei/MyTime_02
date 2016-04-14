package com.atguigu.mytime.entity;

import java.util.List;

/**
 * @Author YfSong
 * @Time 1:08
 * Created by YfSong on 2016/4/12.
 */
public class RecommendGoodsBean {


    /**
     * count : 60
     * goodsIds : 102149,101901,101900,101902,102171,102426,102173,102428,102431,102174,
     * goodsList : [{"background":"#28C8DC","goodsId":102149,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/03/154128.29013294.jpg","longName":"细致工艺 柔软亲肤 透气抗压 缓解疲劳","marketPrice":12900,"minSalePrice":8800,"name":"蝙蝠侠黑暗骑士抱枕","url":"http://mall.wv.mtime.cn/#!/commerce/item/102149/"},{"background":"#28C8DC","goodsId":101901,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/133207.64960580.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"联盟之剑i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101901/"},{"background":"#28C8DC","goodsId":101900,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/132017.25086601.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"魔兽海报i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101900/"},{"background":"#28C8DC","goodsId":101902,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/133643.69435093.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"毁灭之锤i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101902/"},{"background":"#28C8DC","goodsId":102171,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/06/134016.50806555.jpg","longName":"双层杯身 环保材质 个性图案 彰显品质","marketPrice":4900,"minSalePrice":3500,"name":"疯狂动物城吸管杯","url":"http://mall.wv.mtime.cn/#!/commerce/item/102171/"},{"background":"#28C8DC","goodsId":102426,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/25/161724.47493377.jpg","longName":"舒适面料 精细工艺 修身透气 潮流时尚","marketPrice":23900,"minSalePrice":23900,"name":"功夫熊猫迷彩圆领套头卫衣","url":"http://mall.wv.mtime.cn/#!/commerce/item/102426/"},{"background":"#28C8DC","goodsId":102173,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/06/113059.42741144.jpg","longName":"做工细致 经久耐用 质感表层 手感细腻","marketPrice":12900,"minSalePrice":7800,"name":"兔子警官i6/i6s/i6P手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/102173/"},{"background":"#28C8DC","goodsId":102428,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/25/194515.67710722.jpg","longName":"舒适面料 精细工艺 帅气迷彩设计 休闲时尚","marketPrice":19900,"minSalePrice":19900,"name":"功夫熊猫迷彩大印花长袖恤","url":"http://mall.wv.mtime.cn/#!/commerce/item/102428/"},{"background":"#28C8DC","goodsId":102431,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/25/200019.70344067.jpg","longName":"舒适面料 防风保暖 帅气迷彩设计 休闲时尚","marketPrice":36900,"minSalePrice":36900,"name":"功夫熊猫迷彩防风夹克","url":"http://mall.wv.mtime.cn/#!/commerce/item/102431/"},{"background":"#28C8DC","goodsId":102174,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/06/113909.26507299.jpg","longName":"小巧可爱 轻便易携 柔软材质 人气装备","marketPrice":1900,"minSalePrice":1000,"name":"疯狂动物城萌趣屏幕擦","url":"http://mall.wv.mtime.cn/#!/commerce/item/102174/"}]
     * pageCount : 6
     */

    private int count;
    private String goodsIds;
    private int pageCount;
    /**
     * background : #28C8DC
     * goodsId : 102149
     * iconText : 新品
     * image : http://img31.mtime.cn/mg/2016/03/03/154128.29013294.jpg
     * longName : 细致工艺 柔软亲肤 透气抗压 缓解疲劳
     * marketPrice : 12900
     * minSalePrice : 8800
     * name : 蝙蝠侠黑暗骑士抱枕
     * url : http://mall.wv.mtime.cn/#!/commerce/item/102149/
     */

    private List<GoodsListEntity> goodsList;

    public void setCount(int count) {
        this.count = count;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setGoodsList(List<GoodsListEntity> goodsList) {
        this.goodsList = goodsList;
    }

    public int getCount() {
        return count;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<GoodsListEntity> getGoodsList() {
        return goodsList;
    }

    public static class GoodsListEntity {
        private String background;
        private int goodsId;
        private String iconText;
        private String image;
        private String longName;
        private int marketPrice;
        private int minSalePrice;
        private String name;
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

        public void setImage(String image) {
            this.image = image;
        }

        public void setLongName(String longName) {
            this.longName = longName;
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

        public String getImage() {
            return image;
        }

        public String getLongName() {
            return longName;
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

        public String getUrl() {
            return url;
        }
    }
}
