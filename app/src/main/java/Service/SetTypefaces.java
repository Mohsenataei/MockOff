package Service;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class SetTypefaces extends AppCompatActivity {


    public static void setButtonTypefaces(Typeface typeface, Button... buttons){
        for (Button btn : buttons){
            btn.setTypeface(typeface);
        }
    }

    public static  void SetTextviewTypefaces(Typeface typeface,TextView... textViews) {
        for (TextView tv : textViews) {
            tv.setTypeface(typeface);
        }
    }
}
