package customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;

import androidx.annotation.Nullable;

public class ValueSelector extends LinearLayout implements View.OnClickListener {

    View rootView;
    TextView valueText;
    View buttonPlus;
    View buttonMinus;
    TextView target;
    int price;

    TextView conrespondingTextView;
    int MIN_VALUE = 0;
    int MAX_VALUE = 50;

    public void setShits(TextView target, int price){
        this.price = price;
        this.target = target;
    }

    public ValueSelector(Context context) {
        super(context);
        init(context);
    }

    public ValueSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setConrespondingTextView(TextView conrespondingTextView) {
        this.conrespondingTextView = conrespondingTextView;
    }

    private void init(Context context){
        rootView = inflate(context, R.layout.value_selector,this);
        buttonMinus = rootView.findViewById(R.id.buttonMinus);
        buttonPlus = rootView.findViewById(R.id.buttonPlus);
        valueText = rootView.findViewById(R.id.textValue);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonMinus.getId()){
            decreamentValue();

        }else if (v.getId()==buttonPlus.getId()){
            increamentValue();
        }
    }

    private void decreamentValue(){
        int currentValue = getValue();
        setValue(currentValue-1);
        target.setText(String.valueOf(getValue()*price));
    }

    private void increamentValue(){
        int currentValue = getValue();
        setValue(currentValue+1);
        target.setText(String.valueOf(getValue()*price));
    }

    public int getValue(){
        String text = valueText.getText().toString();
        if(text.isEmpty()){
            text = "0";
            valueText.setText(text);
            return 0;
        }
        return Integer.valueOf(text);
    }

    public void setValue(int newvalue){
        if(newvalue > MAX_VALUE){
            valueText.setText(String.valueOf(MAX_VALUE));

        } else if(newvalue < MIN_VALUE){
            valueText.setText(String.valueOf(MIN_VALUE));
        }else{
            valueText.setText(String.valueOf(newvalue));
        }
    }



    public void updateCorespondingTextview(int price, TextView target){
        target.setText(String.valueOf(price*getValue()));
    }
}
