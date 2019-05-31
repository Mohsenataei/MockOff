package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.deathstroke.uniqueoff1.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import entities.Post;
import interfaces.LoadMore;

public class LazyLoadPost extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<Post> postList = new ArrayList<>();

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;

    private LoadMore mLoadMore;


    public LazyLoadPost (List<Post> posts, RecyclerView recyclerView){
        this.postList = posts;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

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

    private class NewPostViewHolder extends RecyclerView.ViewHolder {
        public NewPostViewHolder(View v) {
            super(v);
        }
    }

    private class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        public ProgressBarViewHolder(View v) {
            super(v);
        }
    }
}
