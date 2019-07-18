package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathstroke.uniqueoff1.PostPage;
import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import entities.Pics;
import entities.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class RegPostAdapter extends RecyclerView.Adapter<RegPostAdapter.PostViewHolder> implements Filterable {
    private List<Post> posts;
    private List<Post> postfull;
    private Context context;
    private OnPostClickListener onPostClickListener;
    private static final String TAG = "aghamohsenheader";

    public RegPostAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.postfull = new ArrayList<>(posts);
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.regular_home_page_post_recyclerview,parent,false);
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        return new PostViewHolder(viewItem,onPostClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: "+ position);
        final PostViewHolder postViewHolder = holder;
        Post model = posts.get(position);

        if(model.getTitle().length() > 18){
            Log.d("check size", "onBindViewHolder: title size is bigger than 18 ");
            String tmp = model.getTitle().substring(0,17);
            tmp = tmp.concat("...");
            holder.post_title.setText(tmp);
        }else {
            Log.d("check size", "onBindViewHolder: title size is smaller than 18 ");
            holder.post_title.setText(model.getTitle());
        }

        holder.shop_name.setText(model.getShop_name());

        holder.discount.setText(String.valueOf(model.getDiscount())+context.getString(R.string.percentage));
        holder.original_price.setText(String.valueOf(model.getPrice())+context.getString(R.string.toman));

        holder.original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        String tmp = String.valueOf(Integer.parseInt(model.getPrice()) - ((Integer.parseInt(model.getPrice()) * model.getDiscount())/100));
        holder.price_with_discount.setText(tmp + context.getString(R.string.toman));
        Picasso.get().load(model.getPics().get(0).getThumblink()).into(holder.post_imageview);

        holder.location.setText(model.getAddress());

        holder.bookmark.setOnClickListener(view -> {
            if(!holder.flag) {
                holder.flag = true;
                bookmarkaPost(holder.bookmark,model.getId());
                Toast.makeText(context, "BookMarked!", Toast.LENGTH_SHORT).show();
            }else {
                deleteBookmark(holder.bookmark,model.getId());
                holder.flag = false;
                Toast.makeText(context, "BookMark deleted.", Toast.LENGTH_SHORT).show();
            }
        } );

        holder.main.setOnClickListener(view->{
            Intent intent = new Intent(context,PostPage.class);
            intent.putExtra("post_title",model.getTitle());
            intent.putExtra("quantity",String.valueOf(model.getQuantity()));
            intent.putExtra("price",tmp);
            intent.putExtra("discount",String.valueOf(model.getDiscount()));
            Log.d("PostPage", "Reg post adapter -> onBindViewHolder: " + model.getPrice());
            intent.putExtra("post_id",model.getId());
            intent.putExtra("e_date_use",model.getE_date_show());
            intent.putExtra("e_date_show",model.getE_date_use());
            intent.putExtra("shop_name",model.getShop_name());
            List<Pics> pics = model.getPics();
            String[] headerimgs = new String[pics.size()-1];

            Log.d("headerimgs", "onBindViewHolder: pic size is : "+pics.size());
            for (int i=1;i<=pics.size()-1;i++){
                headerimgs[i-1] = pics.get(i).getThumblink();
                Log.d("headerimgs", "onBindViewHolder: "+headerimgs[i-1]);
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
        return posts.size();
    }

    public void setOnPostClickListener(OnPostClickListener onItemClickListener) {
        this.onPostClickListener = onItemClickListener;
    }


    public interface OnPostClickListener {
        void onPostClick(View view, int position);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView post_title, shop_name, discount, original_price, price_with_discount, location;
        ImageView post_imageview,bookmark;
        boolean flag = false;
        OnPostClickListener onPostClickListener;
        ConstraintLayout main;

        public PostViewHolder(@NonNull View itemView, OnPostClickListener onPostClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);

            post_title = itemView.findViewById(R.id.post_title);
            shop_name = itemView.findViewById(R.id.shop_name);
            discount = itemView.findViewById(R.id.post_discount_percentage);
            original_price = itemView.findViewById(R.id.post_old_price);
            price_with_discount = itemView.findViewById(R.id.post_price);
            location = itemView.findViewById(R.id.post_shop_location);
            bookmark = itemView.findViewById(R.id.post_bookmark);
            post_imageview = itemView.findViewById(R.id.post_image);
            main = itemView.findViewById(R.id.main_post_constraint_layout);
            this.onPostClickListener = onPostClickListener;
        }

//        @Override
//        public void onPostClick(View view, int position) {
//
//        }

        @Override
        public void onClick(View view) {
            onPostClickListener.onPostClick(view, getAdapterPosition());
        }
    }
    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Post> filteredlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredlist.addAll(postfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Post post : postfull){
                    if (post.getTitle().toLowerCase().contains(filterPattern)){
                        filteredlist.add(post);
                    }
                }


            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            posts.clear();
            posts.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public void addPosts(List<Post> newposts){

        for(Post post : newposts){
            posts.add(post);
        }
        notifyDataSetChanged();


    }


}
