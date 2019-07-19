package bottomsheetdialoges;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;
import com.example.deathstroke.uniqueoff1.Shop;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Service.RetrofitClient;
import adapters.RegPostAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import entities.Post;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPageBottomSheet extends BottomSheetDialogFragment {
    private RecyclerView mrecyclerView;
    private RegPostAdapter regPostAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView shopnametextview;
    private Button shopButton, allrightButton;
    private List<Post> posts = new ArrayList<>();
    private String shopname;
    private int shopid;
    private String lat;
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    private static final String TAG = "MapPageBottomSheet";

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_bottom_sheet,container,false);

        mrecyclerView = v.findViewById(R.id.map_bottom_sheet_recycler_view);

        shopButton = v.findViewById(R.id.map_goto_shop);
        allrightButton = v.findViewById(R.id.bottom_sheet_alright);
        shopnametextview = v.findViewById(R.id.map_shop_name);
        shopnametextview.setText(shopname);
        shopButton.setOnClickListener(view->{
            Intent intent = new Intent(getContext(),Shop.class);
            intent.putExtra("shopname",shopname);
            intent.putExtra("shopid",String.valueOf(shopid));
            intent.putExtra("latitude",getLat());
            intent.putExtra("longitude",getLon());
            startActivity(intent);
        });

        allrightButton.setOnClickListener(view ->{
            dismiss();
        });

        getshopPosts();
        return v;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }


    private void getshopPosts(){
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getShopPosts(String.valueOf(getShopid()));

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: response is successful");

                    if(posts.isEmpty())
                        posts.clear();

                    posts = response.body();

                    regPostAdapter = new RegPostAdapter(posts,getActivity());
                    layoutManager = new LinearLayoutManager(getActivity());
                    mrecyclerView.setLayoutManager(layoutManager);
                    mrecyclerView.setAdapter(regPostAdapter);
                    regPostAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }


}
