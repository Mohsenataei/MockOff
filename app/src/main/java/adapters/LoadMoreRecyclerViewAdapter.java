package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import entities.Pics;
import entities.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadMoreRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "aghamohsenheader";

    private List<Post> mPosts;
    private Context mcontext;
    private Activity mactivity;
    private OnItemClickListener listener;

    // for load more
    // for load more
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;


    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;



    public interface OnItemClickListener {
        void onItemClicked(Post mPost);

    }
    public interface OnLoadMoreListener {
        void onLoadMore();

    }
    public void add(int position, Post item) {
        mPosts.add(position, item);
        notifyItemInserted(position);
    }

    public void remove( Post item) {
        int position = mPosts.indexOf(item);
        mPosts.remove(position);
        notifyItemRemoved(position);
    }

    public LoadMoreRecyclerViewAdapter(Context ctx, List<Post> myPosts, RecyclerView recyclerView){
        mcontext = ctx;
        mactivity = (Activity)ctx;
        mPosts = myPosts;

        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mactivity).inflate(R.layout.regular_home_page_post_recyclerview, parent, false);
            return new ViewHolderRow(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mactivity).inflate(R.layout.item_loading, parent, false);
            return new ViewHolderLoading(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (holder instanceof ViewHolderRow) {
           Post post = mPosts.get(position);

            ViewHolderRow userViewHolder = (ViewHolderRow) holder;

            ((ViewHolderRow) holder).post_title.setText(post.getTitle());
            ((ViewHolderRow) holder).shop_name.setText(post.getShop_name());
            ((ViewHolderRow) holder).discount.setText(post.getDiscount());
            ((ViewHolderRow) holder).original_price.setText(String.valueOf(post.getPrice())+mcontext.getString(R.string.toman));
            ((ViewHolderRow) holder).original_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            String tmp = String.valueOf(Integer.parseInt(post.getPrice()) - ((Integer.parseInt(post.getPrice()) * post.getDiscount())/100)) + mcontext.getString(R.string.toman);
            ((ViewHolderRow) holder).price_with_discount.setText(tmp);

            Picasso.get().load(post.getPics().get(0).getThumblink()).into(((ViewHolderRow) holder).post_imageview);


            ((ViewHolderRow) holder).location.setText(post.getAddress());

            ((ViewHolderRow) holder).bookmark.setOnClickListener(view -> {
                if(!((ViewHolderRow) holder).flag) {
                    ((ViewHolderRow) holder).flag = true;
                    bookmarkaPost(((ViewHolderRow) holder).bookmark,post.getId());
                    Toast.makeText(mcontext, "BookMarked!", Toast.LENGTH_SHORT).show();
                }else {
                    bookmarkaPost(((ViewHolderRow) holder).bookmark,post.getId());
                    ((ViewHolderRow) holder).flag = false;
                    Toast.makeText(mcontext, "BookMark deleted.", Toast.LENGTH_SHORT).show();
                }
            } );




            ((ViewHolderRow) holder).main.setOnClickListener(view ->{
                Intent intent = new Intent(mcontext,PostPage.class);
                intent.putExtra("post_title",post.getTitle());
                intent.putExtra("quantity",String.valueOf(post.getQuantity()));
                List<Pics> pics = post.getPics();
                String[] headerimgs = new String[pics.size()-1];
                for (int i=1;i<pics.size()-1;i++){
                    headerimgs[i] = pics.get(i).getThumblink();
                }
                intent.putExtra("img_urls",headerimgs);
                Log.d(TAG, "onBindViewHolder: quantity" + post.getQuantity());
                mcontext.startActivity(intent);

            });
            // binding item click listner
            userViewHolder.bind(mPosts.get(position), listener);
        } else if (holder instanceof ViewHolderLoading) {
            ViewHolderLoading loadingViewHolder = (ViewHolderLoading) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPosts == null ? 0 : mPosts.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return mPosts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }


    private class ViewHolderLoading extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ViewHolderLoading(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    public class ViewHolderRow extends RecyclerView.ViewHolder {
        TextView post_title, shop_name, discount, original_price, price_with_discount, location;
        ImageView post_imageview,bookmark;
        boolean flag = false;
        RegPostAdapter.OnPostClickListener onPostClickListener;
        ConstraintLayout main;

        public ViewHolderRow(View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            shop_name = itemView.findViewById(R.id.shop_name);
            discount = itemView.findViewById(R.id.post_discount_percentage);
            original_price = itemView.findViewById(R.id.post_old_price);
            price_with_discount = itemView.findViewById(R.id.post_price);
            location = itemView.findViewById(R.id.post_shop_location);
            bookmark = itemView.findViewById(R.id.post_bookmark);
            post_imageview = itemView.findViewById(R.id.post_image);
            main = itemView.findViewById(R.id.main_post_constraint_layout);

        }

        public void bind(final Post model, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(model);
                }
            });
        }
    }

    private void bookmarkaPost(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark_onclick);
        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(mcontext),post_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(mcontext, "in bookmarkaPost: this post bookmarked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });
    }

    private void deleteBookmark(ImageView imageView, int post_id){
        imageView.setImageResource(R.drawable.ic_bookmark);
        Call<ResponseBody>  call = RetrofitClient.getmInstance().getApi().markPost(SaveSharedPreference.getAPITOKEN(mcontext),post_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(mcontext, "in deletebookmark: this bookmark deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: ",t);
            }
        });

    }



}
