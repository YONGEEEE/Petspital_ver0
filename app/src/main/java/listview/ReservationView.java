package listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camping.seoul.seoulcamp.R;

public class ReservationView  extends LinearLayout {
    TextView txt_petspital_name;
    TextView txt_regdate;
    TextView txt_name;
    TextView txt_user_id;

    public ReservationView(Context context) {
        super(context);

        init(context);
    }

    public ReservationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_reservation, this, true);

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_petspital_name = (TextView) findViewById(R.id.txt_petspital_name);
        txt_regdate = (TextView) findViewById(R.id.txt_regdate);
        txt_user_id = findViewById(R.id.txt_user_id);


    }

    public void setTxt_petspital_name(String id) {
        txt_petspital_name.setText(id);
    }

    public void setTxt_regdate(String comment) {
        txt_regdate.setText(comment);
    }

    public void setTxt_name(String name) {
        txt_name.setText(name);
    }

    public void setTxt_user_id(String user_id) { txt_user_id.setText(user_id);}


}
