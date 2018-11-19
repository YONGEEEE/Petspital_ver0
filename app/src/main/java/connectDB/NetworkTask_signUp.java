package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import listview.CommentItem;
import object.Member;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_signUp extends AsyncTask<Member, Void, Integer>{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    String url = "http://localhost:8080/member/signIn";

    String json;

    @Override
    protected Integer doInBackground(Member... voids) {
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("id", voids[0].getId());
        object.addProperty("password", voids[0].getPassword());
        json = gson.toJson(object);
        RequestBody body = RequestBody.create(JSON, json);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30,TimeUnit.SECONDS).writeTimeout(15,TimeUnit.SECONDS).build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
