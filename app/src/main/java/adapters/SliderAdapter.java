package adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.deathstroke.uniqueoff1.R;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private String[] imgurls;
    int[] imgs;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context, String[] imgs) {
        this.context = context;
        this.imgurls = imgs;
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
                .centerCrop()
                .into(imageView);

        Log.d("aghamohsen", "instantiateItem: " + imgurls[position]);
        container.addView(imageView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
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
