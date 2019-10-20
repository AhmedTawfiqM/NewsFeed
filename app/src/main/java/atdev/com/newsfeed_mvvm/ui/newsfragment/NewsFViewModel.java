package atdev.com.newsfeed_mvvm.ui.newsfragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import atdev.com.newsfeed_mvvm.pojo.repository.NewsRepo;

public class NewsFViewModel extends ViewModel {

    private MutableLiveData<ArticleList> listLiveData ;
    private NewsRepo newsRepo;

    public void init(Context context) {

        if (newsRepo != null) {
            return;
        }

        newsRepo = NewsRepo.getinstance();
        listLiveData = newsRepo.getArticleList(context);
    }

    public LiveData<ArticleList> getArticles() {

        return listLiveData;
    }

}
