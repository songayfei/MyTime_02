package com.atguigu.mytime.entity;

import java.util.List;

/**
 * @Author YfSong
 * @Time 20:55
 * Created by YfSong on 2016/4/19.
 */
public class PriceBean {


    /**
     * errcode : 获取商品数量:32，获取商品价格信息数量:36
     * success : true
     * result : [{"id":101744,"price":8000,"has_activity":false,"tag":"","market_price":11900,"isnew":false,"mainprop":[{"mainprop_id":1414,"mainprop_price":8000},{"mainprop_id":1415,"mainprop_price":8000}]},{"id":101835,"price":5000,"has_activity":false,"tag":"","market_price":7000,"isnew":false,"mainprop":[]},{"id":102431,"price":36900,"has_activity":false,"tag":"","market_price":36900,"isnew":true,"mainprop":[]},{"id":102378,"price":8800,"has_activity":false,"tag":"","market_price":8800,"isnew":true,"mainprop":[{"mainprop_id":1429,"mainprop_price":8800},{"mainprop_id":1815,"mainprop_price":8800},{"mainprop_id":1814,"mainprop_price":8800}]},{"id":101783,"price":4500,"has_activity":false,"tag":"","market_price":0,"isnew":false,"mainprop":[]},{"id":101838,"price":8800,"has_activity":true,"tag":"立减","market_price":14500,"isnew":false,"mainprop":[]},{"id":100939,"price":19800,"has_activity":false,"tag":"","market_price":26800,"isnew":false,"mainprop":[{"mainprop_id":515,"mainprop_price":23800},{"mainprop_id":412,"mainprop_price":19800},{"mainprop_id":460,"mainprop_price":19800},{"mainprop_id":413,"mainprop_price":19800},{"mainprop_id":734,"mainprop_price":26800}]},{"id":100418,"price":12900,"has_activity":false,"tag":"","market_price":19800,"isnew":false,"mainprop":[{"mainprop_id":411,"mainprop_price":12900},{"mainprop_id":412,"mainprop_price":12900},{"mainprop_id":460,"mainprop_price":12900},{"mainprop_id":413,"mainprop_price":12900}]},{"id":100944,"price":9900,"has_activity":false,"tag":"","market_price":9900,"isnew":false,"mainprop":[]},{"id":102641,"price":17500,"has_activity":true,"tag":"预售","market_price":17500,"isnew":false,"mainprop":[]},{"id":100934,"price":7900,"has_activity":false,"tag":"","market_price":8900,"isnew":false,"mainprop":[{"mainprop_id":515,"mainprop_price":7900},{"mainprop_id":411,"mainprop_price":7900},{"mainprop_id":412,"mainprop_price":7900},{"mainprop_id":413,"mainprop_price":7900}]},{"id":100414,"price":14900,"has_activity":false,"tag":"","market_price":19900,"isnew":false,"mainprop":[{"mainprop_id":413,"mainprop_price":14900}]},{"id":102308,"price":19900,"has_activity":false,"tag":"","market_price":27900,"isnew":true,"mainprop":[]},{"id":102314,"price":9900,"has_activity":false,"tag":"","market_price":13900,"isnew":true,"mainprop":[]},{"id":102558,"price":68800,"has_activity":false,"tag":"","market_price":68800,"isnew":false,"mainprop":[]},{"id":102419,"price":17500,"has_activity":true,"tag":"预售","market_price":17500,"isnew":false,"mainprop":[]},{"id":102198,"price":4900,"has_activity":false,"tag":"","market_price":6900,"isnew":true,"mainprop":[]},{"id":102306,"price":7800,"has_activity":false,"tag":"","market_price":12900,"isnew":false,"mainprop":[]},{"id":101828,"price":7800,"has_activity":false,"tag":"","market_price":12900,"isnew":false,"mainprop":[]},{"id":101702,"price":8800,"has_activity":false,"tag":"","market_price":8800,"isnew":false,"mainprop":[{"mainprop_id":1344,"mainprop_price":8800},{"mainprop_id":1379,"mainprop_price":8800},{"mainprop_id":1380,"mainprop_price":8800},{"mainprop_id":1381,"mainprop_price":8800},{"mainprop_id":1382,"mainprop_price":8800},{"mainprop_id":1383,"mainprop_price":8800},{"mainprop_id":1384,"mainprop_price":8800},{"mainprop_id":1385,"mainprop_price":8800},{"mainprop_id":1386,"mainprop_price":8800},{"mainprop_id":1387,"mainprop_price":8800},{"mainprop_id":1388,"mainprop_price":8800}]},{"id":101829,"price":39900,"has_activity":false,"tag":"","market_price":55900,"isnew":false,"mainprop":[]},{"id":101654,"price":119800,"has_activity":false,"tag":"","market_price":179700,"isnew":false,"mainprop":[]},{"id":101638,"price":4900,"has_activity":false,"tag":"","market_price":6900,"isnew":false,"mainprop":[]},{"id":101734,"price":34900,"has_activity":false,"tag":"","market_price":34900,"isnew":false,"mainprop":[]},{"id":102419,"price":17500,"has_activity":true,"tag":"预售","market_price":17500,"isnew":false,"mainprop":[]},{"id":102561,"price":19900,"has_activity":false,"tag":"","market_price":24900,"isnew":false,"mainprop":[{"mainprop_id":804,"mainprop_price":19900},{"mainprop_id":805,"mainprop_price":19900}]},{"id":101654,"price":119800,"has_activity":false,"tag":"","market_price":179700,"isnew":false,"mainprop":[]},{"id":102173,"price":7800,"has_activity":false,"tag":"","market_price":12900,"isnew":true,"mainprop":[]},{"id":101319,"price":14900,"has_activity":false,"tag":"","market_price":38800,"isnew":false,"mainprop":[]},{"id":102202,"price":4500,"has_activity":false,"tag":"","market_price":7900,"isnew":true,"mainprop":[]},{"id":102198,"price":4900,"has_activity":false,"tag":"","market_price":6900,"isnew":true,"mainprop":[]},{"id":102314,"price":9900,"has_activity":false,"tag":"","market_price":13900,"isnew":true,"mainprop":[]},{"id":102557,"price":68800,"has_activity":false,"tag":"","market_price":68800,"isnew":false,"mainprop":[]},{"id":102149,"price":8800,"has_activity":false,"tag":"","market_price":12900,"isnew":true,"mainprop":[]},{"id":101483,"price":5900,"has_activity":true,"tag":"立减","market_price":6900,"isnew":false,"mainprop":[]},{"id":102190,"price":3900,"has_activity":false,"tag":"","market_price":3900,"isnew":true,"mainprop":[{"mainprop_id":410,"mainprop_price":3900},{"mainprop_id":415,"mainprop_price":3900}]}]
     */

    private String errcode;
    private boolean success;
    /**
     * id : 101744
     * price : 8000
     * has_activity : false
     * tag :
     * market_price : 11900
     * isnew : false
     * mainprop : [{"mainprop_id":1414,"mainprop_price":8000},{"mainprop_id":1415,"mainprop_price":8000}]
     */

    private List<ResultEntity> result;

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getErrcode() {
        return errcode;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        private int id;
        private int price;
        private boolean has_activity;
        private String tag;
        private int market_price;
        private boolean isnew;
        /**
         * mainprop_id : 1414
         * mainprop_price : 8000
         */

        private List<MainpropEntity> mainprop;

        public void setId(int id) {
            this.id = id;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setHas_activity(boolean has_activity) {
            this.has_activity = has_activity;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public void setIsnew(boolean isnew) {
            this.isnew = isnew;
        }

        public void setMainprop(List<MainpropEntity> mainprop) {
            this.mainprop = mainprop;
        }

        public int getId() {
            return id;
        }

        public int getPrice() {
            return price;
        }

        public boolean isHas_activity() {
            return has_activity;
        }

        public String getTag() {
            return tag;
        }

        public int getMarket_price() {
            return market_price;
        }

        public boolean isIsnew() {
            return isnew;
        }

        public List<MainpropEntity> getMainprop() {
            return mainprop;
        }

        public static class MainpropEntity {
            private int mainprop_id;
            private int mainprop_price;

            public void setMainprop_id(int mainprop_id) {
                this.mainprop_id = mainprop_id;
            }

            public void setMainprop_price(int mainprop_price) {
                this.mainprop_price = mainprop_price;
            }

            public int getMainprop_id() {
                return mainprop_id;
            }

            public int getMainprop_price() {
                return mainprop_price;
            }
        }
    }
}
