package apiParser;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import object.CampData;
import object.PetspitalData;

public class JsonParser extends AsyncTask<Void, Void, Map<String, PetspitalData>> {
    String str, recieveMsg;

    Map<String, PetspitalData> datamap = new HashMap<String, PetspitalData>();

    protected Map<String, PetspitalData> doInBackground(Void... voids) {
        String xmlurl = "http://openapi.seoul.go.kr:8088/5a4c614447736b7938385752674d4d/json/vtrHospitalInfo/1/10/";
        URL url = null;
        try {
            url = new URL(xmlurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader temp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(temp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                recieveMsg = buffer.toString();
                datamap = jsonparse(recieveMsg);
                Log.d("test",recieveMsg);
                reader.close();
            }
        } catch (Exception e) {

        }
        return datamap;
    }

    public Map<String, PetspitalData> jsonparse(String jsonString) {

        PetspitalData data;
        Map<String, PetspitalData> tmp = new HashMap<String, PetspitalData>();
        String ID,NM,ADDR_OLD,ADDR,STATE,TEL,XCODE,YCODE,PERMISSON_NO;

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONObject("vtrHospitalInfo").getJSONArray("row");
            for (int i = 0; i < jarray.length(); i++) {
                data = new PetspitalData();
                JSONObject jObject = jarray.getJSONObject(i);

                ID = jObject.optString("ID");
                ADDR_OLD = jObject.optString("ADDR_OLD");//주소
                ADDR = jObject.optString("ADDR");
                XCODE = jObject.optString("XCODE");
                YCODE = jObject.optString("YCODE");
                NM = jObject.optString("NM");
                TEL = jObject.optString("TEL");
                PERMISSON_NO = jObject.optString("PERMISSION_NO");

                tmp.put(ID, data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tmp;
    }
}
