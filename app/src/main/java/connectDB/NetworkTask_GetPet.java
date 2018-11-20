package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import object.PetData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTask_GetPet extends AsyncTask<PetData, Void, PetData> {
    @Override
    protected PetData doInBackground(PetData... voids) {
        String url = "http://192.168.0.6:8080/Pet/GetPet";
        String queryurl = url + "?userid=" + voids[0].getUserid() + "&name=" + voids[0].getName() + "&birth=" + voids[0].getBirth() + "&kind=" + voids[0].getKind() + "&flag=" + voids[0].getFlag() + "&sex=" + voids[0].getSex();
        String bodyStr = "";
        PetData result;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(queryurl)
                .build();
        Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            bodyStr = response.body().string();
            result = gson.fromJson(bodyStr, PetData.class);
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;

    }
}
