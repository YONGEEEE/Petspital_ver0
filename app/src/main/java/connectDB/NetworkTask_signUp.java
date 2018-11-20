package connectDB;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import object.Member;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_signUp extends AsyncTask<Member, Void, Integer> {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Integer doInBackground(Member... voids) {

        String url = "http://192.168.0.6:8080/member/signUp";
        /* Tojson */

        String json;

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("name", voids[0].getName());
        object.addProperty("id", voids[0].getId());
        object.addProperty("password", voids[0].getPassword());
        object.addProperty("nickname", voids[0].getNickname());
        object.addProperty("tel", voids[0].getTel());

        json = gson.toJson(object);

        /*--------------*/

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(JSON, json);

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

        return 0;
    }
}


/* NetworkTask_signUp */