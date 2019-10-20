package atdev.com.newsfeed_mvvm.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atdev.com.newsfeed_mvvm.R;
import atdev.com.newsfeed_mvvm.databinding.ArticlesDataBinding;
import atdev.com.newsfeed_mvvm.pojo.models.model_article.Article;
import atdev.com.newsfeed_mvvm.utilities.Messages;
import atdev.com.newsfeed_mvvm.utilities.RowListnerNews;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArticlesDataBinding articlesDataBinding;
    private List<Article> articles;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Article> articles) {

        this.articles = new ArrayList<>();
        this.articles = articles;
        this.context = context;
    }


    public void SetArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        articlesDataBinding = DataBindingUtil.inflate(inflater, R.layout.single_new, viewGroup, false);

        return new ViewHolder(articlesDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        final Article article = articles.get(i);

        viewHolder.bind(article);
        Messages.showLOG("Adapter : " + i + " : " + article.getUrlToimage());

        final ArticlesDataBinding dataBinding = viewHolder.getArticleBind();

        dataBinding.setListener(new RowListnerNews() {
            @Override
            public void onRowClick() {

                Messages.showToast(context, article.getTitle());
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    //
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ArticlesDataBinding articlesDataBinding;

        public ViewHolder(ArticlesDataBinding articlesDataBinding) {
            super(articlesDataBinding.getRoot());

            this.articlesDataBinding = articlesDataBinding;

        }


        public void bind(Article article) {
            this.articlesDataBinding.setArticlemodel(article);
        }


        public ArticlesDataBinding getArticleBind() {
            return this.articlesDataBinding;
        }
    }
}
