package adapters;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

        holder.post_title.setText(model.getTitle());
        holder.shop_name.setText(model.getShop_name());
        holder.original_price.setText(String.valueOf(model.getPrice()));
        holder.original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String tmp = String.valueOf(model.getPrice() - ((model.getPrice() * model.getDiscount())/100)) + context.getString(R.string.toman);
        holder.price_with_discount.setText(tmp);
        holder.discount.setText(String.valueOf(model.getDiscount()) + context.getString(R.string.percentage));
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

        public HotViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.hot_post_title);
            shop_name = itemView.findViewById(R.id.hot_post_shop_name);
            discount = itemView.findViewById(R.id.hot_discount);
            original_price = itemView.findViewById(R.id.hot_post_price);
            price_with_discount = itemView.findViewById(R.id.hot_post_original_price);
            post_imageview = itemView.findViewById(R.id.hot_post_image_view);
            bookmark = itemView.findViewById(R.id.hot_bookmark);
        }
    }
}
