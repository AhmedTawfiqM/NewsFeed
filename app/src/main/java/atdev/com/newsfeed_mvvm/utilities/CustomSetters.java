package atdev.com.newsfeed_mvvm.utilities;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Random;

import atdev.com.newsfeed_mvvm.R;

public class CustomSetters {

    @BindingAdapter("bind:imageUrl")
    public static void SetImgSrc(ImageView view, String imgSrcUrl) {


        //Log.d("picasso", imgSrcUrl);

        if (imgSrcUrl==null){
            imgSrcUrl=getUrl();
        }
        Picasso.get().load(imgSrcUrl)
                .placeholder(R.drawable.star_yes)
                .fit()
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("picasso", "Success");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("picasso", e.getMessage());
                    }
                });
    }


    public static String getUrl() {

        String[] urls = new String[8];
        urls[0] = "https://mediaaws.almasryalyoum.com/news/verylarge/2019/10/19/980798_0.jpg";
        urls[1]="https://img.youm7.com/large/201902240417121712.jpg";
        urls[2]="https://www.skynewsarabia.com/images/v1/2019/10/20/1291902/1200/630/1-1291902.jpg";
        urls[3]="https://www.elbalad.news/upload/photo/news/402/5/560x292o/356.jpg";
        urls[4]="https://www.elbalad.news/upload/photo/news/402/5/560x292o/310.jpg";
        urls[5]="https://media.linkonlineworld.com/img/large/2019/10/20/2019_10_20_3_18_37_946.jpg";
        urls[6]="https://media.linkonlineworld.com/img/large/2019/10/20/2019_10_20_1_42_37_646.jpg";
        urls[7]="https://www.elbalad.news/upload/photo/news/402/5/560x292o/267.jpg";

        return urls[new Random().nextInt(urls.length)];
    }

}
