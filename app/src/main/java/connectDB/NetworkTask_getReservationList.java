package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import object.PetData;
import object.Reservation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTask_getReservationList extends AsyncTask<String, Void, List<Reservation>> {
    @Override
    protected List<Reservation> doInBackground(String... voids) {
        String url = "http://203.253.255.113:9090/reservation/getReservationList";
        String queryurl = url + "?userid=" + voids[0];
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
        Reservation[] array = gson.fromJson(bodyStr, Reservation[].class);
        List<Reservation> list = Arrays.asList(array);

        return list;
    }
}
