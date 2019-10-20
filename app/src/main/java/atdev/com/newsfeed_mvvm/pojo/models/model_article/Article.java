package atdev.com.newsfeed_mvvm.pojo.models.model_article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {


    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("urlToimage")
    @Expose
    private String urlToimage;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("content")
    @Expose
    private String content;


    public Article(Source source, String author, String title, String description, String urlToimage, String url, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlToimage = urlToimage;
        this.url = url;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToimage() {
        return urlToimage;
    }

    public void setUrlToimage(String urlToimage) {
        this.urlToimage = urlToimage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void SetArticle(Article article){

        this.title = article.getTitle();
        this.description = article.getDescription();
        this.author = article.getAuthor();
        this.url = article.getUrl();
        this.urlToimage = article.getUrlToimage();
        this.content = article.getContent();
        this.publishedAt = article.getPublishedAt();
        this.source = article.getSource();

    }
}
