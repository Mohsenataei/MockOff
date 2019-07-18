package adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathstroke.uniqueoff1.PostPage;
import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;
import entities.Pics;
import entities.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class HotPostsAdapter extends RecyclerView.Adapter<HotPostsAdapter.HotViewHolder> {
    private List<Post> hotPosts;
    private Context context;

    public HotPostsAdapter (List<Post> mPosts, Context ctx) {
        this.hotPosts = mPosts;
        context = ctx;
    }

    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_posts_list,parent,false);

        return new HotViewHolder(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull HotViewHolder holder, int position) {

        Post model = hotPosts.get(position);

        if(model.getTitle().length() > 15){
            Log.d("check size", "onBindViewHolder: title size is bigger than 18 ");
            String tmp = model.getTitle().substring(0,14);
            tmp = tmp.concat("...");
            holder.post_title.setText(tmp);
        }else {
            Log.d("check size", "onBindViewHolder: title size is smaller than 18 ");
            holder.post_title.setText(model.getTitle());
        }
        //holder.post_title.setText(model.getTitle());
        holder.shop_name.setText(model.getShop_name());
        holder.original_price.setText(String.valueOf(model.getPrice()) + context.getString(R.string.toman));
        holder.original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String tmp = String.valueOf(Integer.parseInt(model.getPrice()) - ((Integer.parseInt(model.getPrice()) * model.getDiscount())/100)) + context.getString(R.string.toman);
        holder.price_with_discount.setText(tmp);
        holder.discount.setText(String.valueOf(model.getDiscount()) + context.getString(R.string.percentage));

        //RoundedBitmapDrawable img = RoundedBitmapDrawableFactory.create();

        Picasso.get().load(model.getPics().get(0).getThumblink()).into(holder.post_imageview);
        //Picasso.with(context).load(model.getPics().get(0).getThumblink()).into(holder.post_imageview);

        holder.bookmark.setOnClickListener(view->{
            if(!holder.flag){
                holder.flag = true;
                bookmarkaPost(holder.bookmark, model.getId());
                Toast.makeText(context, "done!", Toast.LENGTH_SHORT).show();
            }else {
                holder.flag = false;
                deleteBookmark(holder.bookmark, model.getId());
                Toast.makeText(context, "undone!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.main.setOnClickListener(view ->{
            Intent intent = new Intent(context,PostPage.class);
            intent.putExtra("post_title",model.getTitle());
            intent.putExtra("quantity",String.valueOf(model.getQuantity()));
            intent.putExtra("price",model.getPrice());
            intent.putExtra("discount",String.valueOf(model.getDiscount()));
            Log.d("PostPage", "Reg post adapter -> onBindViewHolder: " + model.getPrice());
            intent.putExtra("post_id",model.getId());
            intent.putExtra("e_date_use",model.getE_date_show());
            intent.putExtra("e_date_show",model.getE_date_use());
            intent.putExtra("shop_name",model.getShop_name());
            List<Pics> pics = model.getPics();
            String[] headerimgs = new String[pics.size()];

            if (pics.size() == 1){
                headerimgs[0] = pics.get(0).getThumblink();
                Log.d("headerimgs", "onBindViewHolder: header pic size is 1");
            }else {
                Log.d("headerimgs", "onBindViewHolder: pic size is : "+pics.size());
                for (int i=0;i<pics.size();i++){
                    headerimgs[i] = pics.get(i).getThumblink();
                    Log.d("headerimgs", "onBindViewHolder: "+headerimgs[i]);
                }
            }

            intent.putExtra("img_urls",headerimgs);
            Log.d(TAG, "onBindViewHolder: quantity" + model.getQuantity());
            context.startActivity(intent);
        });

    }



    private void bookmarkaPost(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark_onclick);
        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(context),post_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(context, "in bookmarkaPost: this post bookmarked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });
    }

    private void deleteBookmark(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark);
        Call<ResponseBody>  call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(context),post_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(context, "in deletebookmark: this bookmark deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });

    }



    @Override
    public int getItemCount() {
        return hotPosts.size();
    }

    public static class HotViewHolder extends RecyclerView.ViewHolder {

        TextView post_title, shop_name, discount, original_price, price_with_discount;
        ImageView post_imageview,bookmark;
        boolean flag = false;
        LinearLayout main;

        public HotViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.hot_post_title);
            shop_name = itemView.findViewById(R.id.hot_post_shop_name);
            discount = itemView.findViewById(R.id.hot_discount);
            original_price = itemView.findViewById(R.id.hot_post_price);
            price_with_discount = itemView.findViewById(R.id.hot_post_original_price);
            post_imageview = itemView.findViewById(R.id.hot_post_image_view);
            bookmark = itemView.findViewById(R.id.hot_bookmark);
            main = itemView.findViewById(R.id.hot_posts_container);

        }
    }
}
