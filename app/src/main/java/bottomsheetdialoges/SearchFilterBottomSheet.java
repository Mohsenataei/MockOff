package bottomsheetdialoges;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deathstroke.uniqueoff1.R;
import com.example.deathstroke.uniqueoff1.ShowClassification;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchFilterBottomSheet extends BottomSheetDialogFragment {
    private LinearLayout amusmentLayout, servicesLayout, restaurantLayout, clothesLayout, cafeLayout
            , healthLayout, educationLayout, makeupsLayout, culturalLayout;
    private TextView amusmenttextView, servicestextView, restauranttextView, clothestextView, cafetextView
            , healthtextView, educationtextView, makeupstextView, culturaltextView;
    private boolean[] layoutflags = new boolean[9];
    private boolean[] textViewflags = new boolean[9];

    private static final int AMUSMENT = 0;
    private static final int SERVICE = 1;
    private static final int RESTAURANT = 2;
    private static final int CLOTHES = 3;
    private static final int HEALTH = 4;
    private static final int CAFE = 5;
    private static final int EDUCATION = 6;
    private static final int MAKEUP = 7;
    private static final int CULTURAL = 8;

    private BottomSheetListener mListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.search_filter_layout,container,false);

        //initialize layouts
        amusmentLayout = view.findViewById(R.id.amusment_layout);
        servicesLayout = view.findViewById(R.id.services_layout);
        restaurantLayout = view.findViewById(R.id.restaurant_layout);
        clothesLayout = view.findViewById(R.id.tours_layout);
        healthLayout = view.findViewById(R.id.health_layout);
        cafeLayout = view.findViewById(R.id.arts_layout);
        educationLayout = view.findViewById(R.id.education_layout);
        makeupsLayout = view.findViewById(R.id.makeUps_layout);
        culturalLayout = view.findViewById(R.id.tags_layout);

        //initialize text views
        amusmenttextView = view.findViewById(R.id.amusment_textView);
        servicestextView = view.findViewById(R.id.services_textView);
        restauranttextView = view.findViewById(R.id.restaurant_textView);
        clothestextView = view.findViewById(R.id.tours_textView);
        healthtextView = view.findViewById(R.id.health_textView);
        cafetextView = view.findViewById(R.id.arts_textView);
        educationtextView = view.findViewById(R.id.education_textView);
        makeupstextView = view.findViewById(R.id.makeUps_textView);
        culturaltextView = view.findViewById(R.id.tags_textView);




        amusmentLayout.setOnClickListener(v->{
            OnClickAmusmentLayout();
            //StartShowClassificationActivity("تفریحی");
            mListener.onLayoutClicked("3");
            dismiss();
        });

        servicesLayout.setOnClickListener(v->{
            OnClickServicesLayout();
            //StartShowClassificationActivity("خدمات");
            mListener.onLayoutClicked("6");
            dismiss();
        });

        restaurantLayout.setOnClickListener(v->{
            OnClickRestaurantLayout();
            //StartShowClassificationActivity("رستوران");
            mListener.onLayoutClicked("2");
            dismiss();
        });

        clothesLayout.setOnClickListener(v ->{
            OnClickClothesLayout();
            //StartShowClassificationActivity("پوشاک");
            mListener.onLayoutClicked("9");
            dismiss();
        });

        healthLayout.setOnClickListener(v->{
            OnClickHealthtLayout();
            //StartShowClassificationActivity("سلامت");
            mListener.onLayoutClicked("4");
            dismiss();
        });

        cafeLayout.setOnClickListener(v->{
            OnClickCafeLayout();
            //StartShowClassificationActivity("کافی شاپ");
            mListener.onLayoutClicked("8");
            dismiss();
        });

        educationLayout.setOnClickListener(v->{
            OnClickEducationLayout();
            StartShowClassificationActivity("آموزش");
            mListener.onLayoutClicked("7");
            dismiss();
        });

        makeupsLayout.setOnClickListener(v->{
            OnClickMakeUpsLayout();
            StartShowClassificationActivity("آرایشی و بهداشتی");
            mListener.onLayoutClicked("5");
            dismiss();
        });

        culturalLayout.setOnClickListener(v->{
            OnClickCulturalLayout();
            //StartShowClassificationActivity("فرهنگی");
            mListener.onLayoutClicked("1");
            dismiss();
        });

        return view;
    }

    private void StartShowClassificationActivity(String field) {
        Intent intent = new Intent(getContext(),ShowClassification.class);
        intent.putExtra("field",field);
        startActivity(intent);
    }

    private void OnClickAmusmentLayout(){
        if (!layoutflags[AMUSMENT]){
            ChangeLayoutBackgroundToBlue(amusmentLayout);
            ChangeTextViewDrawableTop(amusmenttextView,getResources().getDrawable(R.drawable.ic_swimming_pool_solid_onclick),textViewflags[AMUSMENT]);
            layoutflags[AMUSMENT]=true;
            textViewflags[AMUSMENT]=true;
        }else {
            ChangeLayoutBackgroundToDefault(amusmentLayout);
            ChangeTextViewDrawableTop(amusmenttextView,getResources().getDrawable(R.drawable.ic_swimming_pool_solid),textViewflags[AMUSMENT]);
            layoutflags[AMUSMENT]=false;
            textViewflags[AMUSMENT]=false;
        }

    }
    private void OnClickServicesLayout(){
        if(!layoutflags[SERVICE]){
            ChangeLayoutBackgroundToBlue(servicesLayout);
            ChangeTextViewDrawableTop(servicestextView,getResources().getDrawable(R.drawable.ic_wrench_solid_onclick),textViewflags[SERVICE]);
            layoutflags[SERVICE]=true;
            textViewflags[SERVICE]=true;
        }else{
            ChangeLayoutBackgroundToDefault(servicesLayout);
            ChangeTextViewDrawableTop(servicestextView,getResources().getDrawable(R.drawable.ic_wrench_solid),textViewflags[SERVICE]);
            layoutflags[SERVICE]=false;
            textViewflags[SERVICE]=false;
        }

    }
    private void OnClickRestaurantLayout(){

        if(!layoutflags[RESTAURANT]){
            ChangeLayoutBackgroundToBlue(restaurantLayout);
            ChangeTextViewDrawableTop(restauranttextView,getResources().getDrawable(R.drawable.ic_utensils_onclick),textViewflags[RESTAURANT]);
            layoutflags[RESTAURANT]=true;
            textViewflags[RESTAURANT]=true;
        }else{
            ChangeLayoutBackgroundToDefault(restaurantLayout);
            ChangeTextViewDrawableTop(restauranttextView,getResources().getDrawable(R.drawable.ic_utensils),textViewflags[RESTAURANT]);
            layoutflags[RESTAURANT]=false;
            textViewflags[RESTAURANT]=false;
        }

    }
    private void OnClickClothesLayout(){
        if(!layoutflags[CLOTHES]){
            ChangeLayoutBackgroundToBlue(clothesLayout);
            ChangeTextViewDrawableTop(clothestextView,getResources().getDrawable(R.drawable.ic_plane_departure_onclick),textViewflags[CLOTHES]);
            layoutflags[CLOTHES]=true;
            textViewflags[CLOTHES]=true;
        }else{
            ChangeLayoutBackgroundToDefault(clothesLayout);
            ChangeTextViewDrawableTop(clothestextView,getResources().getDrawable(R.drawable.ic_plane_departure),textViewflags[CLOTHES]);
            layoutflags[CLOTHES]=false;
            textViewflags[CLOTHES]=false;
        }
    }
    private void OnClickCafeLayout(){
        if(!layoutflags[CAFE]){
            ChangeLayoutBackgroundToBlue(cafeLayout);
            ChangeTextViewDrawableTop(cafetextView,getResources().getDrawable(R.drawable.ic_palette_onclick),textViewflags[CAFE]);
            layoutflags[CAFE]=true;
            textViewflags[CAFE]=true;
        }else{
            ChangeLayoutBackgroundToDefault(cafeLayout);
            ChangeTextViewDrawableTop(cafetextView,getResources().getDrawable(R.drawable.ic_palette),textViewflags[CAFE]);
            layoutflags[CAFE]=false;
            textViewflags[CAFE]=false;
        }
    }
    private void OnClickHealthtLayout(){
        if(!layoutflags[HEALTH]){
            ChangeLayoutBackgroundToBlue(healthLayout);
            ChangeTextViewDrawableTop(healthtextView,getResources().getDrawable(R.drawable.ic_heartbeat_onclick),textViewflags[HEALTH]);
            layoutflags[HEALTH]=true;
            textViewflags[HEALTH]=true;
        }else{
            ChangeLayoutBackgroundToDefault(healthLayout);
            ChangeTextViewDrawableTop(healthtextView,getResources().getDrawable(R.drawable.ic_heartbeat),textViewflags[HEALTH]);
            layoutflags[HEALTH]=false;
            textViewflags[HEALTH]=false;
        }
    }
    private void OnClickMakeUpsLayout(){
        if(!layoutflags[MAKEUP]){
            ChangeLayoutBackgroundToBlue(makeupsLayout);
            ChangeTextViewDrawableTop(makeupstextView,getResources().getDrawable(R.drawable.ic_grin_beam_category_onclick),textViewflags[MAKEUP]);
            layoutflags[MAKEUP]=true;
            textViewflags[MAKEUP]=true;
        }else{
            ChangeLayoutBackgroundToDefault(makeupsLayout);
            ChangeTextViewDrawableTop(makeupstextView,getResources().getDrawable(R.drawable.ic_grin_beam_category),textViewflags[MAKEUP]);
            layoutflags[MAKEUP]=false;
            textViewflags[MAKEUP]=false;
        }
    }
    private void OnClickCulturalLayout(){
        if(!layoutflags[CULTURAL]){
            ChangeLayoutBackgroundToBlue(culturalLayout);
            ChangeTextViewDrawableTop(culturaltextView,getResources().getDrawable(R.drawable.ic_tags_onclick),textViewflags[CULTURAL]);
            layoutflags[CULTURAL]=true;
            textViewflags[CULTURAL]=true;
        }else{
            ChangeLayoutBackgroundToDefault(culturalLayout);
            ChangeTextViewDrawableTop(culturaltextView,getResources().getDrawable(R.drawable.ic_tags),textViewflags[CULTURAL]);
            layoutflags[CULTURAL]=false;
            textViewflags[CULTURAL]=false;
        }
    }
    private void OnClickEducationLayout(){
        if(!layoutflags[EDUCATION]){
            ChangeLayoutBackgroundToBlue(educationLayout);
            ChangeTextViewDrawableTop(educationtextView,getResources().getDrawable(R.drawable.ic_graduation_cap_onclick),textViewflags[EDUCATION]);
            layoutflags[EDUCATION]=true;
            textViewflags[EDUCATION]=true;
        }else{
            ChangeLayoutBackgroundToDefault(educationLayout);
            ChangeTextViewDrawableTop(educationtextView,getResources().getDrawable(R.drawable.ic_graduation_cap),textViewflags[EDUCATION]);
            layoutflags[EDUCATION]=false;
            textViewflags[EDUCATION]=false;
        }
    }

    private void ChangeLayoutBackgroundToBlue(LinearLayout layout){
        layout.setBackground(getResources().getDrawable(R.drawable.grid_item_border_onclick));
    }

    private void ChangeLayoutBackgroundToDefault(LinearLayout layout){
        layout.setBackground(getResources().getDrawable(R.drawable.grid_items_border));
    }

    private void ChangeTextViewDrawableTop(TextView tv, Drawable drawableTop, boolean flag){
        //tv.setCompoundDrawables(null,drawableTop,null,null);
        tv.setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null);
        if (flag){
            tv.setTextColor(getResources().getColor(R.color.coop_item_color));

        }else{
            tv.setTextColor(getResources().getColor(R.color.white));
        }

    }

    public interface BottomSheetListener {
        void onLayoutClicked(String cat_id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement BottomSheetListener");
        }
    }
}
