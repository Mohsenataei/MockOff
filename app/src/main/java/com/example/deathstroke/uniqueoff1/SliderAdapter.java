package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    int[] imgs;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return imgs.length ;
    }

    public int getRealCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==(LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item_slider, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(imgs[position]);

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
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
