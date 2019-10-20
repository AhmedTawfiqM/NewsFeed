package atdev.com.newsfeed_mvvm.pojo.network;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIFeeds {

    @GET("top-headlines")
    Call<ArticleList> getArticles(@Query("country") String country,
                                  @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Observable<ArticleList> getArticleList(@Query("country") String country, @Query("apiKey") String apiKey);
}
