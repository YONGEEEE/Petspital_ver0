package connectDB;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import object.NowUser;
import object.Reservation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_insertReservation extends AsyncTask<Reservation, Void, Integer> {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Integer doInBackground(Reservation... voids) {

        String url = "http://203.253.255.113:9090/reservation/insertReservation";
        /* Tojson */

        String json;

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("petspital",voids[0].getPetspital());
        object.addProperty("regdate",voids[0].getRegdate());
        object.addProperty("userid", NowUser.id);
        object.addProperty("name", voids[0].getName());
        object.addProperty("age", voids[0].getAge());
        object.addProperty("weight", voids[0].getWeight());
        object.addProperty("birth", voids[0].getBirth());
        object.addProperty("inform", voids[0].getInform());
        object.addProperty("kind", voids[0].getKind());
        object.addProperty("flag", voids[0].getFlag());
        object.addProperty("sex", voids[0].getSex());
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
