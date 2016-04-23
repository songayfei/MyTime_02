package com.atguigu.mytime.entity;

import java.util.List;

/**
 * 作者: Administrator on 2016/4/23 11:02
 * 说明: 简讯1
 */
public class NewsEntity {

    /**
     * type : 0
     * id : 1554716
     * title : 2015年全球票房383亿美元创新纪录
     * title2 : 亚太市场变全球最大票仓 中国观众贡献巨大
     * content : <div>&nbsp; &nbsp; &nbsp; <b>时光网讯</b> 日前，美国电影协会公布了2015年全球电影票房数据。数据显示，2015年全世界的电影票房收入总计383亿美元，创下新的历史纪录，较2014年上升了5%。其中，北美市场年度总票房111亿美元，国际市场（除美国之外的市场）年度总票房272亿美元，都有不同程度的增长。2015全球票房第一的影片是《星球大战：原力觉醒》，单片全球报收20亿美元。</div><div><br></div><div><img src="http://img31.mtime.cn/CMS/News/2016/04/22/153913.51217050_620X620.jpg"><br><div><span>2015全球票房前三名</span></div></div><div><span><br></span></div><div>&nbsp; &nbsp; &nbsp; 助推2015年全球电影票房高速增长的最大引擎在亚太市场。</div><div><br></div><div>&nbsp; &nbsp; &nbsp; 数据显示，去年亚太地区票房141亿美元，年增长率达到了13%，远远超过全球市场的增长速率。中国的总票房在2015年为68亿美元，较2014年增长了49%，超过全球第三大市场近50亿美元，并且占到了亚太地区年度总票房的50%。</div><div>&nbsp; &nbsp;&nbsp;</div><div>&nbsp; &nbsp; &nbsp; 美国电影协会主席克里斯托弗.多德表示，2015年对于全球电影产业都是非常重要的一年，中国将在未来几年内超越北美，成为世界上最大的电影票房市场。</div><div>&nbsp; &nbsp;&nbsp;</div><div>&nbsp; &nbsp; &nbsp; 亚太地区之外，拉丁美洲的票房也较2014年增长了13%，其中阿根廷年度总票房显著增加38%。与之形成鲜明对比的是，欧洲、中东、非洲的票房较2014年下滑了9%，法国、俄罗斯和西班牙等欧洲几大市场的年度票房均有所下滑。当然这其中不乏汇率浮动的原因——这些市场因为要与其他地区做比较，所以年度总票房都换算成了美元，浮动的汇率有很大影响，不少市场以当地货币统计年度票房是上升的。</div><div><br></div><div>&nbsp; &nbsp; &nbsp; 美国影院业主协会主席兼CEO约翰·菲西安表示，如果不是一些国家货币贬值，他相信2015年全球票房能超过400亿美元。</div>
     * time : 2016-4-22 15:33:04
     * source : Mtime时光网
     * author :
     * editor : 羊羊
     * commentCount : 69
     * url : http://news.mtime.com/2016/04/22/1554716.html
     * wapUrl : http://news.wap.mtime.com/2016/04/22/1554716.html
     * relations : [{"type":1,"id":192895,"name":"星球大战：原力觉醒","image":"http://img31.mtime.cn/mt/2015/12/02/103436.31724563_1280X720X2.jpg","year":"2015","rating":7.6,"scoreCount":8026,"releaseDate":"2016年1月9日","relaseLocation":"中国"}]
     * previousNewsID : 0
     * nextNewsID : 1554735
     */

    private int type;
    private int id;
    private String title;
    private String title2;
    private String content;
    private String time;
    private String source;
    private String author;
    private String editor;
    private int commentCount;
    private String url;
    private String wapUrl;
    private int previousNewsID;
    private int nextNewsID;
    /**
     * type : 1
     * id : 192895
     * name : 星球大战：原力觉醒
     * image : http://img31.mtime.cn/mt/2015/12/02/103436.31724563_1280X720X2.jpg
     * year : 2015
     * rating : 7.6
     * scoreCount : 8026
     * releaseDate : 2016年1月9日
     * relaseLocation : 中国
     */

    private List<RelationsEntity> relations;

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public void setPreviousNewsID(int previousNewsID) {
        this.previousNewsID = previousNewsID;
    }

    public void setNextNewsID(int nextNewsID) {
        this.nextNewsID = nextNewsID;
    }

    public void setRelations(List<RelationsEntity> relations) {
        this.relations = relations;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle2() {
        return title2;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getUrl() {
        return url;
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public int getPreviousNewsID() {
        return previousNewsID;
    }

    public int getNextNewsID() {
        return nextNewsID;
    }

    public List<RelationsEntity> getRelations() {
        return relations;
    }

    public static class RelationsEntity {
        private int type;
        private int id;
        private String name;
        private String image;
        private String year;
        private double rating;
        private int scoreCount;
        private String releaseDate;
        private String relaseLocation;

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setScoreCount(int scoreCount) {
            this.scoreCount = scoreCount;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRelaseLocation(String relaseLocation) {
            this.relaseLocation = relaseLocation;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getYear() {
            return year;
        }

        public double getRating() {
            return rating;
        }

        public int getScoreCount() {
            return scoreCount;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getRelaseLocation() {
            return relaseLocation;
        }
    }
}
