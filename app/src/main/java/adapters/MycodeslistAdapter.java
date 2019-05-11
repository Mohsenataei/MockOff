package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.deathstroke.uniqueoff1.QRCode;
import com.example.deathstroke.uniqueoff1.R;

import java.util.List;

import Service.Utils;
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
    public MycodesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View viewitem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_codes_recycler_leyout,viewGroup,false);
        return new MycodesViewHolder(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull MycodesViewHolder mycodesViewHolder, int i) {

        final MycodesViewHolder holder = mycodesViewHolder;
        Code model = mycodes.get(i);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        requestOptions.timeout(3000);

        Glide.with(context)
                .load(model.getPic())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.code_image);

        mycodesViewHolder.code_title_text_view.setText(model.getTitle());

        mycodesViewHolder.off_price_text_view.setText("قیمت با تخفیف :"+ model.getDiscount_price());

        mycodesViewHolder.originsl_price_text_view.setText("قیمت بدون تخفیف : "+model.getPrice());

        mycodesViewHolder.count_text_view.setText(String.valueOf("تعداد : "+model.getCount()));

        mycodesViewHolder.total_price_text_view.setText("جمع کل : "+model.getTotal_price());

        mycodesViewHolder.buy_date_text_view.setText("تاریخ خرید"+model.getOrder_date());

        mycodesViewHolder.expiration_date_text_view.setText("تاریخ انقضاء"+model.getE_date_use());

        mycodesViewHolder.shop_name_ext_view.setText(model.getShop_id());
        Toast.makeText(context, "in adapter", Toast.LENGTH_SHORT).show();

        mycodesViewHolder.showcodebtn.setOnClickListener(view->{
            Toast.makeText(context, "it it working ?" + model.getType_id(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,QRCode.class);
            intent.putExtra("qr_code",model.getQr_code());
            intent.putExtra("ticket_status", String.valueOf( model.getType_id()));
            intent.putExtra("count",String.valueOf(model.getCount()));

            mycodesViewHolder.context.startActivity(intent);

        });
//        if (getItemCount()>0) {
//            for (int j =0;j<getItemCount();i++){
//                if (j/ 2 == 1)
//                    mycodesViewHolder.viewmycodes.setBackground(context.getDrawable(R.drawable.my_codes_shadow_1));
//            }
//        }


    }

    @Override
    public int getItemCount() {
        if (mycodes == null) return 0;
        return mycodes.size();
        //return mycodes == null ? mycodes.size() : 0;
    }

    public static class MycodesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        LinearLayout viewmycodes;
        ImageView code_image;
        Typeface typeface;
        TextView code_title_text_view;
        TextView originsl_price_text_view;
        TextView off_price_text_view;
        TextView count_text_view;
        TextView total_price_text_view;
        TextView buy_date_text_view;
        TextView expiration_date_text_view;
        TextView shop_name_ext_view;
        Button showcodebtn;
        Context context;
        OnItemClickListener onItemClickListener;


        MycodesViewHolder(View viewitem){
            super(viewitem);
            shop_name_ext_view = viewitem.findViewById(R.id.recycler_shop_name);
            originsl_price_text_view = viewitem.findViewById(R.id.my_codes_list_price_without_off);
            off_price_text_view = viewitem.findViewById(R.id.my_codes_list_price_with_off);
            count_text_view = viewitem.findViewById(R.id.my_codes_list_code_count);
            total_price_text_view = viewitem.findViewById(R.id.my_codes_list_total);
            buy_date_text_view = viewitem.findViewById(R.id.my_codes_list_code_buy_date);
            expiration_date_text_view = viewitem.findViewById(R.id.my_codes_list_code_expiration_date);
            shop_name_ext_view = viewitem.findViewById(R.id.recycler_shop_name);
            showcodebtn = viewitem.findViewById(R.id.my_codes_list_show_code_btn);
            code_image = viewitem.findViewById(R.id.my_codes_list_image_view);
            code_title_text_view = viewitem.findViewById(R.id.code_title);
            viewmycodes = itemView.findViewById(R.id.my_codes_list);
            typeface = Typeface.createFromAsset(viewitem.getContext().getAssets(),"fonts/B Yekan+.ttf");
            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context,QRCode.class));

        }
    }
    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
