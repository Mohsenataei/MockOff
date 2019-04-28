package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import entities.Code;

public class MycodeslistAdapter extends RecyclerView.Adapter<MycodeslistAdapter.MycodesViewHolder>{

    private List<Code> mycodes;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public MycodeslistAdapter(List<Code> mycodes, Context context) {
        this.mycodes = mycodes;
        this.context= context;
    }

    @NonNull
    @Override
    public MycodesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewitem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_codes_recycler_leyout,viewGroup,false);
        return new MycodesViewHolder(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull MycodesViewHolder mycodesViewHolder, int i) {


        mycodesViewHolder.code_name_text_view.setText(mycodes.get(i).getCode_name());
        mycodesViewHolder.off_price_text_view.setText("قیمت با تخفیف :"+ mycodes.get(i).getPrice_with_off());
        mycodesViewHolder.originsl_price_text_view.setText(mycodes.get(i).getOriginal_price());
        mycodesViewHolder.count_text_view.setText(mycodes.get(i).getCount());
        mycodesViewHolder.total_price_text_view.setText(mycodes.get(i).getTotal_price());
        mycodesViewHolder.buy_date_text_view.setText(mycodes.get(i).getBuy_date());
        mycodesViewHolder.expiration_date_text_view.setText(mycodes.get(i).getExpiration_date());
        mycodesViewHolder.shop_name_ext_view.setText(mycodes.get(i).getShop_name());

    }

    @Override
    public int getItemCount() {
        if (mycodes == null) return 0;
        return mycodes.size();
        //return mycodes == null ? mycodes.size() : 0;
    }

    public static class MycodesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.my_codes_list)
        LinearLayout viewmycodes;

        @Bind(R.id.my_codes_list_image_view)
        ImageView code_image;

        @Bind(R.id.my_codes_list_code_name)
        TextView code_name_text_view;

        @Bind(R.id.my_codes_list_price_with_off)
        TextView originsl_price_text_view;

        @Bind(R.id.my_codes_list_price_without_off)
        TextView off_price_text_view;

        @Bind(R.id.my_codes_list_code_count)
        TextView count_text_view;

        @Bind(R.id.my_codes_list_total)
        TextView total_price_text_view;

        @Bind(R.id.my_codes_list_code_buy_date)
        TextView buy_date_text_view;

        @Bind(R.id.my_codes_list_code_expiration_date)
        TextView expiration_date_text_view;

        @Bind(R.id.recycler_shop_name)
        TextView shop_name_ext_view;

        @Bind(R.id.my_codes_list_show_code_btn)
        Button showcodebtn;


        MycodesViewHolder(View viewitem){
            super(viewitem);
            ButterKnife.bind(this,viewitem);
            //shop_name_ext_view = viewitem.findViewById(R.id.recycler_shop_name);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
