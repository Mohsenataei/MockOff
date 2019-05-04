package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import entities.SubShop;

public class SubShopsAdapter extends RecyclerView.Adapter<SubShopsAdapter.ShopViewHolder> {

    private List<SubShop> subShopList;
    private Context context;

    public SubShopsAdapter(List<SubShop> subShopList, Context context) {
        this.subShopList = subShopList;
        this.context = context;
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        ImageView shopImageView;
        TextView shopNameTextView;
        Typeface typeface;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImageView = itemView.findViewById(R.id.followed_shop_image);
            shopNameTextView = itemView.findViewById(R.id.followed_shop_name);
            typeface = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/B Yekan+.ttf");
        }
    }



        @NonNull
        @Override
        public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View viewitem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.followed_shop_list,viewGroup,false);
            return new ShopViewHolder(viewitem);
        }

        @Override
        public void onBindViewHolder(@NonNull ShopViewHolder shopViewHolder, int i) {

        final ShopViewHolder viewHolder = shopViewHolder;

        SubShop model = subShopList.get(i);

        shopViewHolder.shopNameTextView.setText(model.getName());
        shopViewHolder.shopNameTextView.setTypeface(shopViewHolder.typeface);

            Picasso.with(context).load(model.getPic()).into(shopViewHolder.shopImageView);

        }

        @Override
        public int getItemCount() {
            return subShopList.size();
        }
}
