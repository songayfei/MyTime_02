package com.atguigu.mytime.entity;

/**
 * Created by Administrator on 2016/4/9.
 */
public class Aaa {

    /**
     * newsID : 1554113
     * title : 专访《伦敦陷落》五大主创
     * type : 0
     * imageUrl : http://img31.mtime.cn/mg/2016/04/09/093104.72255528.jpg
     */

    private NewsEntity news;
    /**
     * trailerID : 59778
     * title : 《侠盗一号》中文版预告片
     * imageUrl : http://img31.mtime.cn/mg/2016/04/08/101426.95062026.jpg
     * mp4Url : http://vfx.mtime.cn/Video/2016/04/07/mp4/160407215402781505_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/04/07/mp4/160407215402781505.mp4
     * movieId : 214815
     */

    private TrailerEntity trailer;
    /**
     * reviewID : 7950788
     * title : 上古神话的好莱坞式解读
     * posterUrl : http://img31.mtime.cn/mt/2016/03/04/095907.69694487_1280X720X2.jpg
     * movieName : 神战：权力之眼
     * imageUrl : http://img31.mtime.cn/mg/2016/03/15/095415.85345975.jpg
     */

    private ReviewEntity review;
    /**
     * id : 10792
     * imageUrl : http://img31.mtime.cn/mg/2015/03/31/100230.43767720.jpg
     */

    private ViewingGuideEntity viewingGuide;
    /**
     * id : 1348
     * title : 亚视经典剧集回顾
     * imageUrl : http://img31.mtime.cn/mg/2016/03/05/111735.12026988.jpg
     * type : 0
     */

    private TopListEntity topList;

    public void setNews(NewsEntity news) {
        this.news = news;
    }

    public void setTrailer(TrailerEntity trailer) {
        this.trailer = trailer;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public void setViewingGuide(ViewingGuideEntity viewingGuide) {
        this.viewingGuide = viewingGuide;
    }

    public void setTopList(TopListEntity topList) {
        this.topList = topList;
    }

    public NewsEntity getNews() {
        return news;
    }

    public TrailerEntity getTrailer() {
        return trailer;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public ViewingGuideEntity getViewingGuide() {
        return viewingGuide;
    }

    public TopListEntity getTopList() {
        return topList;
    }

    public static class NewsEntity {
        private int newsID;
        private String title;
        private int type;
        private String imageUrl;

        public void setNewsID(int newsID) {
            this.newsID = newsID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getNewsID() {
            return newsID;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class TrailerEntity {
        private int trailerID;
        private String title;
        private String imageUrl;
        private String mp4Url;
        private String hightUrl;
        private int movieId;

        public void setTrailerID(int trailerID) {
            this.trailerID = trailerID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setMp4Url(String mp4Url) {
            this.mp4Url = mp4Url;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getTrailerID() {
            return trailerID;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getMp4Url() {
            return mp4Url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public int getMovieId() {
            return movieId;
        }
    }

    public static class ReviewEntity {
        private int reviewID;
        private String title;
        private String posterUrl;
        private String movieName;
        private String imageUrl;

        public void setReviewID(int reviewID) {
            this.reviewID = reviewID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getReviewID() {
            return reviewID;
        }

        public String getTitle() {
            return title;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class ViewingGuideEntity {
        private String id;
        private String imageUrl;

        public void setId(String id) {
            this.id = id;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class TopListEntity {
        private int id;
        private String title;
        private String imageUrl;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getType() {
            return type;
        }
    }
}
