package adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import entities.HeaderPics;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.deathstroke.uniqueoff1.PostPage;
import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private String[] imgurls;
    private List<HeaderPics> headerPics;
    int[] imgs;
    LayoutInflater layoutInflater;


    public SliderAdapter(Context context, String[] imgs, List<HeaderPics> headerPics) {
        this.context = context;
        this.imgurls = imgs;
        this.headerPics = headerPics;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public SliderAdapter(Context context, String[] imgs) {
        this.context = context;
        this.imgurls = imgs;
       // this.headerPics = headerPics;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return imgurls.length ;
    }

    public int getRealCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item_slider, container, false);

        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load(imgurls[position])
                .fit()
                .into(imageView);


        Log.d("shopViewPager", "instantiateItem: " + imgurls[position]);
        container.addView(imageView);

        if(headerPics != null){
        if(!headerPics.isEmpty()){
            //listening to image click
            imageView.setOnClickListener(view-> {
                if(headerPics.get(position).getUrl() != null)
                    context.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(headerPics.get(position).getUrl())));
                else if(headerPics.get(position).getPost_id() != null) {
                    Intent intent = new Intent(context, PostPage.class);
                    intent.putExtra("post_id",headerPics.get(position).getPost_id());
                    context.startActivity(intent);
                } else if(headerPics.get(position).getShop_id() != null){
                    Intent intent = new Intent(context, PostPage.class);
                    intent.putExtra("shop_id",headerPics.get(position).getShop_id());
                    context.startActivity(intent);
                }
            });

        }
        }


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    private int mapPagerPositionToModelPosition(int pagerPosition) {
        // Put last page model to the first position.
        if (pagerPosition == 0) {
            return getRealCount() - 1;
        }
        // Put first page model to the last position.
        if (pagerPosition == getRealCount() + 1) {
            return 0;
        }
        return pagerPosition - 1;
    }


}
