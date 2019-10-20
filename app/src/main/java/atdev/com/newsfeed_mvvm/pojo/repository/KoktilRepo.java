package atdev.com.newsfeed_mvvm.pojo.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import atdev.com.newsfeed_mvvm.pojo.network.APIClient;
import atdev.com.newsfeed_mvvm.pojo.network.APIFeeds;
import atdev.com.newsfeed_mvvm.utilities.APIKeys;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class KoktilRepo {

    MutableLiveData<ArticleList> MainarticleList = new MutableLiveData<>();

    public List<Observable<ArticleList>> getRequests() {

        APIFeeds apiClient = APIClient.getAPINews();
        // aLL REQUETS
        // Make a collection of all requests you need to call at once,
        List<Observable<ArticleList>> requests = new ArrayList<>();

        requests.add(apiClient.getArticleList("eg", APIKeys.apiKey));
        requests.add(apiClient.getArticleList("be", APIKeys.apiKey));
        requests.add(apiClient.getArticleList("ar", APIKeys.apiKey));
        requests.add(apiClient.getArticleList("cz", APIKeys.apiKey));
        requests.add(apiClient.getArticleList("il", APIKeys.apiKey));
        requests.add(apiClient.getArticleList("cn", APIKeys.apiKey));

        return requests;
    }

    private static KoktilRepo koktilRepo;
    //

    public MutableLiveData<ArticleList> getArtilceList() {

        List<Observable<ArticleList>> requests = getRequests();
        //

        Observable.zip(
                requests, new Function<Object[], ArticleList>() {
                    @Override
                    public ArticleList apply(Object[] articleListsArray) throws Exception {

                        //
                        ArticleList[] articleLists = new ArticleList[articleListsArray.length];
                        for (int i = 0; i < articleLists.length; i++) {

                            articleLists[i] = (ArticleList) articleListsArray[i];
                        }

                        return OrderArticleListsArrayintoArticles(articleLists);
                    }


                }
        ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<ArticleList>() {
                    @Override
                    public void accept(ArticleList articleList) throws Exception {


                        MainarticleList.postValue(articleList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();

                        MainarticleList.postValue(null);
                    }
                });

        return MainarticleList;
    }


    private ArticleList OrderArticleListsArrayintoArticles(ArticleList[] articleListList) {

        List<Article> Mainarticles = new ArrayList<>();
        ArticleList MainarticleList = new ArticleList();


        for (ArticleList articleList1 : articleListList) {

            Mainarticles.addAll(articleList1.getArticles());
        }

        Collections.shuffle(Mainarticles);

        MainarticleList.setArticles(Mainarticles);

        return MainarticleList;
    }


    public static KoktilRepo getInstance() {

        if (koktilRepo == null) {
            koktilRepo = new KoktilRepo();
        }
        return koktilRepo;
    }
}
