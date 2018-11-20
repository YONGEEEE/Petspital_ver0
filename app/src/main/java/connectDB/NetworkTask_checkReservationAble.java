package connectDB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import object.Reservation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTask_checkReservationAble extends AsyncTask<Reservation, Void, Integer> {

    @Override
    protected Integer doInBackground(Reservation... voids) {
        String url = "http://192.168.0.6:8080/reservation/checkReservationAble";
        String queryurl = url + "?petspital="+voids[0].getPetspital()+"&regdate="+voids[0].getRegdate();
        String bodyStr = "";
        int result;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(queryurl)
                .build();
        Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            bodyStr = response.body().string();
            Log.d("bodyStr=",bodyStr);
            result = Integer.parseInt(bodyStr);
        } catch (IOException e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
}
