package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entities.Code;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CustomViewHolder> {

    private List<Code> codeList;
    private Context context;

    public CodeAdapter (Context context, List<Code> codeList){
        this.context = context;
        this.codeList = codeList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }


}
