package connectDB;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import listview.CommentItem;
import object.NowUser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_AddList extends AsyncTask<CommentItem, Void, Integer> {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Integer doInBackground(CommentItem... voids) {

        String url = "http://192.168.0.6:8080/comment/addList";
        /* Tojson */

        String json;

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("petspital", voids[0].getPetspital());
        object.addProperty("id", voids[0].getId());
        object.addProperty("name", NowUser.id);
        object.addProperty("text", voids[0].getText());
        object.addProperty("star", voids[0].getStar());


        json = gson.toJson(object);

        /*--------------*/

        OkHttpClient okHttpClient = new OkHttpClient();
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

        return null;
    }
}
