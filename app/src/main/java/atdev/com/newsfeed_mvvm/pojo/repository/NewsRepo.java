package atdev.com.newsfeed_mvvm.pojo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import atdev.com.newsfeed_mvvm.pojo.network.APIClient;
import atdev.com.newsfeed_mvvm.pojo.network.APIFeeds;
import atdev.com.newsfeed_mvvm.utilities.APIKeys;
import atdev.com.newsfeed_mvvm.utilities.Messages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {

    private static NewsRepo newsRepo;

    public MutableLiveData<ArticleList> getArticleList(final Context context) {

        final MutableLiveData<ArticleList> mutableLiveData = new MutableLiveData<>();

        APIFeeds apiFeeds = APIClient.getRetrofit().create(APIFeeds.class);
        apiFeeds.getArticles("eg",  APIKeys.apiKey).enqueue(new Callback<ArticleList>() {

            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                    Messages.showLOG("Success:: " + response.body().getArticles().get(0).getTitle());
                } else {
                    Messages.showLOG("Failed");
                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

                Messages.showToast(context, "Error");
                Messages.showLOG(t.getMessage());
            }
        });

        return mutableLiveData;

    }


    public static NewsRepo getinstance() {
        if (newsRepo == null) {
            newsRepo = new NewsRepo();
        }
        return newsRepo;
    }
}
