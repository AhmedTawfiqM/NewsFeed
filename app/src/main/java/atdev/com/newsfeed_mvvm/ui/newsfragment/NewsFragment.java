package atdev.com.newsfeed_mvvm.ui.newsfragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atdev.com.newsfeed_mvvm.R;
import atdev.com.newsfeed_mvvm.adapters.RecyclerViewAdapter;
import atdev.com.newsfeed_mvvm.databinding.NewsFragmentBinding;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;
import atdev.com.newsfeed_mvvm.pojo.network.APIClient;
import atdev.com.newsfeed_mvvm.pojo.network.APIFeeds;
import atdev.com.newsfeed_mvvm.pojo.repository.NewsRepo;
import atdev.com.newsfeed_mvvm.utilities.Messages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    NewsFragmentBinding binding;
    NewsFViewModel newsFViewModel;
    RecyclerViewAdapter adapterNews;
    List<Article> articles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //List<Article> articles = NewsRepo.getArticleList(getContext());
        //Messages.showToast(getActivity(), articles.get(0).getTitle());

        newsFViewModel = ViewModelProviders.of(getActivity()).get(NewsFViewModel.class);
        //
        setUpRecyclerView();
        //
        newsFViewModel.init(getContext());
        // set Articles from DB
        newsFViewModel.getArticles().observe(this, new Observer<ArticleList>() {
            @Override
            public void onChanged(@Nullable ArticleList articleList) {

                Messages.showLOG("onChanged");
                if (articleList != null) {
                    //Messages.showLOG("adapter: "+articleList.getArticles().size());
                    articles.addAll(articleList.getArticles());
                    adapterNews.notifyDataSetChanged();

                } else {
                    Messages.showLOG("Null Articles");
                }

            }


        });


    }

    public void setUpRecyclerView() {

        if (adapterNews == null) {

            adapterNews = new RecyclerViewAdapter(getActivity(), articles);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerView.setAdapter(adapterNews);

        } else {
            adapterNews.notifyDataSetChanged();
        }

    }
}
