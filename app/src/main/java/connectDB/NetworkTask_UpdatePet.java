package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import object.NowUser;
import object.PetData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_UpdatePet extends AsyncTask<PetData, Void, PetData> {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected PetData doInBackground(PetData... voids) {

        String bodyStr="";
        String url = "http://192.168.0.6:8080/Pet/UpdatePet";
        /* Tojson */

        String json;

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("userid", NowUser.id);
        object.addProperty("name", voids[0].getName());
        object.addProperty("age", voids[0].getAge());
        object.addProperty("weight", voids[0].getWeight());
        object.addProperty("inform", voids[0].getInform());

        json = gson.toJson(object);
        Log.d("JSON", json);
        /*--------------*/

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            bodyStr = response.body().string();
            Log.d("response", bodyStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PetData[] array = gson.fromJson(bodyStr, PetData[].class);
        List<PetData> list = Arrays.asList(array);
        return list.get(0);
    }
}
