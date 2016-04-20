package com.atguigu.mytime.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 * 城市信息实体类
 */
public class SelectorCityInfo implements Serializable{
    private static final long serialVersionUID=123L;
    /**
     * count : 192
     * id : 292
     * n : 上海
     * pinyinFull : Shanghai
     * pinyinShort : sh
     */

    private List<PEntity> p;

    public void setP(List<PEntity> p) {
        this.p = p;
    }

    public List<PEntity> getP() {
        return p;
    }

    public static class PEntity implements Serializable{
        private static final long serialVersionUID=1234L;
        private int count;
        private int id;
        private String n;
        private String pinyinFull;
        private String pinyinShort;

        public void setCount(int count) {
            this.count = count;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setN(String n) {
            this.n = n;
        }

        public void setPinyinFull(String pinyinFull) {
            this.pinyinFull = pinyinFull;
        }

        public void setPinyinShort(String pinyinShort) {
            this.pinyinShort = pinyinShort;
        }

        public int getCount() {
            return count;
        }

        public int getId() {
            return id;
        }

        public String getN() {
            return n;
        }

        public String getPinyinFull() {
            return pinyinFull;
        }

        public String getPinyinShort() {
            return pinyinShort;
        }
    }
}
