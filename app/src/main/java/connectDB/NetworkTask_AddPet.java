package connectDB;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import listview.CommentItem;
import object.NowUser;
import object.PetData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_AddPet extends AsyncTask<PetData, Void, Integer> {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Integer doInBackground(PetData... voids) {

        String url = "http://개인 DB /comment/addList";
        /* Tojson */

        String json;

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("UserId", NowUser.id);
        object.addProperty("PetId", voids[0].getID());
        object.addProperty("Name", voids[0].getNM());
        object.addProperty("Age", voids[0].getAGE());
        object.addProperty("Weight", voids[0].getWEIGHT());
        object.addProperty("Birth", voids[0].getBIRTH());
        object.addProperty("Inform",voids[0].getINFORM());
        object.addProperty("KIND",voids[0].getKIND());
        object.addProperty("FLAG",voids[0].getFLAG());

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
