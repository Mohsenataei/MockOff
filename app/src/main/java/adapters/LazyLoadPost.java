package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.*;
import com.example.deathstroke.uniqueoff1.PostPage;
import com.example.deathstroke.uniqueoff1.R;

import java.util.ArrayList;
import java.util.List;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import entities.Pics;
import entities.Post;
import interfaces.LoadMore;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LazyLoadPost extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<Post> postList = new ArrayList<>();
    private Context context;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private static final String TAG = "aghamohsen";

    private LoadMore mLoadMore;


    public LazyLoadPost (List<Post> posts, RecyclerView recyclerView, Context context){
        this.postList = posts;

        this.context = context;
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();



            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = layoutManager.getItemCount();
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                    if(!loading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                        // End has been reached
                        // Do something
                        if (mLoadMore != null) {
                            mLoadMore.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.regular_home_page_post_recyclerview,parent,false);

            viewHolder = new NewPostViewHolder(v);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.regular_home_page_post_recyclerview,parent,false);

            viewHolder = new ProgressBarViewHolder(v);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mholder, int position) {

        if(mholder instanceof NewPostViewHolder){
            Post model = postList.get(position);
            
         RecyclerView.ViewHolder holder = ((NewPostViewHolder) mholder);
         

            ((NewPostViewHolder) holder).post_title.setText(model.getTitle());
            ((NewPostViewHolder) holder).shop_name.setText(model.getShop_name());
            ((NewPostViewHolder) holder).discount.setText(String.valueOf(model.getDiscount())+context.getString(R.string.percentage));
            ((NewPostViewHolder) holder).original_price.setText(String.valueOf(model.getPrice())+context.getString(R.string.toman));
            ((NewPostViewHolder) holder).original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            String tmp = String.valueOf(Integer.parseInt(model.getPrice()) - ((Integer.parseInt(model.getPrice()) * model.getDiscount())/100)) + context.getString(R.string.toman);
            ((NewPostViewHolder) holder).price_with_discount.setText(tmp);
            Picasso.get().load(model.getPics().get(0).getThumblink()).into(((NewPostViewHolder) holder).post_imageview);

            ((NewPostViewHolder) holder).location.setText(model.getAddress());

            ((NewPostViewHolder) holder).bookmark.setOnClickListener(view -> {
                if(!((NewPostViewHolder) holder).flag) {
                    ((NewPostViewHolder) holder).flag = true;
                    bookmarkaPost(((NewPostViewHolder) holder).bookmark,model.getId());
                    Toast.makeText(context, "BookMarked!", Toast.LENGTH_SHORT).show();
                }else {
                    deleteBookmark(((NewPostViewHolder) holder).bookmark,model.getId());
                    ((NewPostViewHolder) holder).flag = false;
                    Toast.makeText(context, "BookMark deleted.", Toast.LENGTH_SHORT).show();
                }
            } );

            ((NewPostViewHolder) holder).main.setOnClickListener(view->{
                Intent intent = new Intent(context,PostPage.class);
                intent.putExtra("post_title",model.getTitle());
                intent.putExtra("quantity",String.valueOf(model.getQuantity()));
                List<Pics> pics = model.getPics();
                String[] headerimgs = new String[pics.size()-1];
                for (int i=1;i<pics.size()-1;i++){
                    headerimgs[i] = pics.get(i).getThumblink();
                }
                intent.putExtra("img_urls",headerimgs);
                Log.d(TAG, "onBindViewHolder: quantity" + model.getQuantity());
                context.startActivity(intent);
            });

        }else {
            ((ProgressBarViewHolder) mholder).progressBar.setIndeterminate(true);
        }

    }

    private void bookmarkaPost(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark_onclick);
        Call<String> call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(context),post_id);

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
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
       // return super.getItemViewType(position);
        return postList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setLoadMore (LoadMore loadMore){
        this.mLoadMore = loadMore;
    }

    private class NewPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView post_title, shop_name, discount, original_price, price_with_discount, location;
        ImageView post_imageview,bookmark;
        boolean flag = false;
        OnPostClickListener onPostClickListener;
        ConstraintLayout main;
        public NewPostViewHolder(View itemView) {
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

        @Override
        public void onClick(View view) {

        }
    }

    private class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressBarViewHolder(View v) {
            super(v);

            progressBar = v.findViewById(R.id.progressBar);

        }
    }

    public interface OnPostClickListener {
        void onPostClick(View view, int position);
    }
}
