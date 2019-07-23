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

import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import entities.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import Service.RetrofitClient;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;


public class MarkedPostsAdapter extends RecyclerView.Adapter<MarkedPostsAdapter.MarkedPostViewHolder> {

    private List<Post> MarkedPosts;
    private Context context;

    public MarkedPostsAdapter(List<Post> posts, Context ctx){
        MarkedPosts = posts;
        context = ctx;
    }


    private void bookmarkaPost(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark_onclick);
        Call<String>  call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(context),post_id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(context, "in bookmarkaPost: this post bookmarked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });
    }

    private void deleteBookmark(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark);
        Call<String>  call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(context),post_id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(context, "in deletebookmark: this bookmark deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });

    }

    @NonNull
    @Override
    public MarkedPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarked_post_list,parent,false);
        return new MarkedPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkedPostViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Post model = MarkedPosts.get(position);
        holder.post_title.setText(model.getTitle());
        holder.shop_name.setText(model.getShop_name());
        holder.discount.setText(String.valueOf(model.getDiscount()) + context.getString(R.string.percentage));
        holder.original_price.setText(String.valueOf(model.getPrice()) + context.getString(R.string.toman));
        holder.original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        String tmp = String.valueOf(Integer.parseInt(model.getPrice()) - ((Integer.parseInt(model.getPrice()) * model.getDiscount())/100)) + context.getString(R.string.toman);
        holder.price_with_discount.setText(tmp);
        Picasso.get().load(model.getPics().get(0).getThumblink()).into(holder.post_imageview);
        holder.location.setText(model.getAddress());
        holder.bookmark.setOnClickListener(view -> {
            if (!holder.flag) {
                holder.flag=true;
                deleteBookmark(holder.bookmark,model.getId());
                Toast.makeText(context, "bookmark deleted", Toast.LENGTH_SHORT).show();


            }else {
                bookmarkaPost(holder.bookmark,model.getId());
                Toast.makeText(context, "bookmarked", Toast.LENGTH_SHORT).show();
                holder.flag=false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return MarkedPosts.size();
    }


    public static class MarkedPostViewHolder extends RecyclerView.ViewHolder{
        TextView post_title, shop_name, discount, original_price, price_with_discount, location;
        ImageView post_imageview,bookmark;
        Boolean flag = false;

        public MarkedPostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            shop_name = itemView.findViewById(R.id.shop_name);
            discount = itemView.findViewById(R.id.post_discount_percentage);
            original_price = itemView.findViewById(R.id.post_old_price);
            price_with_discount = itemView.findViewById(R.id.post_price);
            location = itemView.findViewById(R.id.post_shop_location);
            bookmark = itemView.findViewById(R.id.post_bookmark);
            post_imageview = itemView.findViewById(R.id.post_image);
        }
    }

    public interface OnMarkedPostListener{
        void onMarkedClick(int position);
    }
}
