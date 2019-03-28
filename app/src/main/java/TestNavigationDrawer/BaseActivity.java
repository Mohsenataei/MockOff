package TestNavigationDrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.deathstroke.uniqueoff1.R;

public class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Base", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    protected void initAddlayout(int layout) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);
        ((FrameLayout) findViewById(R.id.main_content_below)).addView(view);

    }
}
