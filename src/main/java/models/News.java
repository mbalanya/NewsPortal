package models;

import java.util.Objects;

public class News {
    private String headline;
    private String newsArticle;
    private int id;
    private int departmentId;

    public News(String headline, String newsArticle, int departmentId) {
        this.headline = headline;
        this.newsArticle = newsArticle;
        this.departmentId = departmentId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(String newsArticle) {
        this.newsArticle = newsArticle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                departmentId == news.departmentId &&
                headline.equals(news.headline) &&
                newsArticle.equals(news.newsArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, newsArticle, id, departmentId);
    }
}
