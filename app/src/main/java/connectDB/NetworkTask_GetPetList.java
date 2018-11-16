package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import listview.CommentItem;
import object.PetData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTask_GetPetList extends AsyncTask<String, Void, List<PetData>> {
    @Override
    protected List<PetData> doInBackground(String... voids) {
        String url = "http://개인 DB/comment/listAll";
        String queryurl = url + "?camp=" + voids[0];
        String bodyStr = "";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(queryurl)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            bodyStr = response.body().string();
            Log.d("response", bodyStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        PetData[] array = gson.fromJson(bodyStr, PetData[].class);
        List<PetData> list = Arrays.asList(array);

        return list;
    }
}
