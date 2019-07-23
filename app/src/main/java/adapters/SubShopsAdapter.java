package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import Service.RetrofitClient;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;
import com.example.deathstroke.uniqueoff1.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

import entities.Coordinate;
import entities.SubShop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubShopsAdapter extends RecyclerView.Adapter<SubShopsAdapter.ShopViewHolder> {

    private List<SubShop> subShopList;
    private Context context;
    private Coordinate coordinate;

    public SubShopsAdapter(List<SubShop> subShopList, Context context) {
        this.subShopList = subShopList;
        this.context = context;
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        ImageView shopImageView;
        TextView shopNameTextView;
        ConstraintLayout shops_holder;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImageView = itemView.findViewById(R.id.followed_shop_image);
            shopNameTextView = itemView.findViewById(R.id.followed_shop_name);
            shops_holder = itemView.findViewById(R.id.sub_shops_holder);
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

        Picasso.get().load(model.getPic()).into(shopViewHolder.shopImageView);

        getShopCoordinate(String.valueOf(model.getId()));

        shopViewHolder.shops_holder.setOnClickListener(view->{
            Intent intent = new Intent(context,Shop.class);
            intent.putExtra("shopname",model.getName());
            intent.putExtra("shopid",model.getId());
            intent.putExtra("latitude",coordinate.getLatitude());
            intent.putExtra("longitude",coordinate.getLongitude());
            context.startActivity(intent);
        });



        }

        @Override
        public int getItemCount() {
            return subShopList.size();
        }


        private void getShopCoordinate(String shop_id ){

        Call<Coordinate> coordinateCall = RetrofitClient.getmInstance().getApi().getShopCoordinate(shop_id);

        coordinateCall.enqueue(new Callback<Coordinate>() {
            @Override
            public void onResponse(Call<Coordinate> call, Response<Coordinate> response) {
                if(response.isSuccessful() && response.body() != null){
                    coordinate = response.body();
                }
            }

            @Override
            public void onFailure(Call<Coordinate> call, Throwable t) {

            }
        });

        }
}
