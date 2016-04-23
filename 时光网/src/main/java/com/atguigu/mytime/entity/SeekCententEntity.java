package com.atguigu.mytime.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者: Administrator on 2016/4/22 09:44
 * 说明:
 */
@Table(name = "Seek_data")
public class SeekCententEntity {
    @Column(name = "count")
    private String content;

    public SeekCententEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
