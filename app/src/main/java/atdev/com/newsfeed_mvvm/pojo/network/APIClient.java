package atdev.com.newsfeed_mvvm.pojo.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit;
    private static String baseURl = "https://newsapi.org/v2/";


    public static Retrofit getRetrofit() {

        if (retrofit == null) {


            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseURl.trim())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static APIFeeds getAPINews() {

        return APIClient.getRetrofit().create(APIFeeds.class);
    }

}
