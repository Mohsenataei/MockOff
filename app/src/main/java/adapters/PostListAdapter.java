package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;

import java.util.List;

import entities.Post;

public class PostListAdapter extends ArrayAdapter<Post> {

    private Context context;
    private List<Post> Posts;

    public PostListAdapter(Context context, List<Post> posts ){
        super(context, R.layout.post_list_layout,posts);
        this.context = context;
        this.Posts = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.post_list_layout,parent,false);
        Post post = Posts.get(position);
        TextView shopname, postname,offpercentage,oldprice,newprice,shoplocation;
        String imageurl;
        ImageView shop_image;

        shopname = convertView.findViewById(R.id.shopname);
        postname = convertView.findViewById(R.id.postname);
        offpercentage = convertView.findViewById(R.id.off_percentage);
        oldprice = convertView.findViewById(R.id.old_price);
        newprice = convertView.findViewById(R.id.new_price);
        shoplocation = convertView.findViewById(R.id.shop_location);

        shop_image = convertView.findViewById(R.id.shop_image_view);

        shopname.setText(String.valueOf(post.getShop_name()));
        postname.setText(String.valueOf(post.getPost_name()));
        offpercentage.setText(String.valueOf(post.getOff_percentage()));
        oldprice.setText(String.valueOf(post.getOriginal_price()));
        newprice.setText(String.valueOf(post.getOff_price()));
        shoplocation.setText(String.valueOf(post.getShop_location()));

        imageurl = post.getImage_url();



        return convertView;

    }
}
