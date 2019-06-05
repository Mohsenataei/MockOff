package customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RialTextView extends TextView {

    String rawText;
    private static String[] persianNumbers = new String[]{ "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };


    public RialTextView(Context context) {
        super(context);

    }

    public RialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        rawText = text.toString();
        String prezzo = text.toString();
        try {

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            prezzo = decimalFormat.format(Integer.parseInt(text.toString()));
        }catch (Exception e){}

        String out = "";
        int length = prezzo.length();
        if (prezzo.length() == 0){
            super.setText(prezzo, type);
        }else{
            for(int i=0;i<length;i++){
                char ch = prezzo.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    int n = Integer.parseInt(String.valueOf(ch));
                    out += persianNumbers[n];
                }else if(ch == ','){
                    out += "،";
                }else{
                    out += ch;
                }
            }
            super.setText(out,type);
        }

    }

    @Override
    public CharSequence getText() {

        return rawText;
    }
}
