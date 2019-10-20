package atdev.com.newsfeed_mvvm.pojo.models.model_article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Article> articles;

    public ArticleList(String status, Integer totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        SetArticless(articles);
        this.articles = articles;
    }

    public ArticleList() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setArticleList(ArticleList SarticleList) {
        this.status = SarticleList.getStatus();
        this.totalResults = SarticleList.getTotalResults();
        this.articles = SarticleList.getArticles();
    }

    public void SetArticless(List<Article> articles) {

        this.articles.addAll(articles);

    }

}
