package atdev.com.newsfeed_mvvm.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Messages {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLOG(String message) {
        Log.d("MYErorr", message);
    }
}
