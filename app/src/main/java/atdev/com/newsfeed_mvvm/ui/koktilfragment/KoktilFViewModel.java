package atdev.com.newsfeed_mvvm.ui.koktilfragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import atdev.com.newsfeed_mvvm.pojo.repository.KoktilRepo;

public class KoktilFViewModel extends ViewModel {

    KoktilRepo koktilRepo;
    MutableLiveData<ArticleList> mutableLiveData = new MutableLiveData<>();

    public void init() {

        if (koktilRepo != null) {
            return;
        }
        koktilRepo =  KoktilRepo.getInstance();
        mutableLiveData = koktilRepo.getArtilceList();
    }


    public LiveData<ArticleList> getArtileList() {
        return mutableLiveData;
    }

}
