package atdev.com.newsfeed_mvvm.ui.koktilfragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atdev.com.newsfeed_mvvm.R;
import atdev.com.newsfeed_mvvm.adapters.RecyclerViewAdapter;
import atdev.com.newsfeed_mvvm.databinding.FragmentKoktilBinding;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.ArticleList;

public class koktilFragment extends Fragment {

    // Vars
    FragmentKoktilBinding binding;
    KoktilFViewModel viewModel;
    RecyclerViewAdapter adapterNews;
    List<Article> articles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_koktil, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(KoktilFViewModel.class);

        viewModel.init();
        setUpRecyclerView();

        viewModel.getArtileList().observe(this, new Observer<ArticleList>() {
            @Override
            public void onChanged(@Nullable ArticleList articleList) {

                try {
                    articles.addAll(articleList.getArticles());
                    adapterNews.notifyDataSetChanged();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
