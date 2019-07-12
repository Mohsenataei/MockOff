package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.os.Bundle;

import Service.RetrofitClient;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import entities.Detail;
import entities.ShopShits;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link shop_info_fragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link shop_info_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class shop_info_fragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public shop_info_fragment() {
//        // Required empty public constructor
//    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment shop_info_fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static shop_info_fragment newInstance(String param1, String param2) {
//        shop_info_fragment fragment = new shop_info_fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    private TextView shop_address, shop_phone_number, shop_work_time, shop_work_days;

    private Detail model;

    public Detail getModel() {
        return model;
    }

    public void setModel(Detail model) {
        this.model = model;
    }



    private int shopid;
    private String shopname;

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_info_fragment, container, false);
        shop_address = view.findViewById(R.id.shop_address);
        shop_phone_number = view.findViewById(R.id.shop_phone_number);
        shop_work_days = view.findViewById(R.id.shop_work_days);
        shop_work_time = view.findViewById(R.id.shop_work_time);
        Log.d("fragment", "onCreateView: is it working ? ");
        getShopDetails();
////        setTextViews();
//        justfottest();
//        shop_address.setText(model.getAddress());
//        shop_work_time.setText(model.getWork_time());
//        shop_work_days.setText(model.getWork_date());
//        shop_phone_number.setText(model.getHome_phone());
        return view;
    }

    private void setTextViews(){

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("fragment", "onAttach: testing on attath");
        justfottest();
        getShopDetails();
//        setTextViews();
      //  justfottest();
//        shop_address.setText(model.getAddress());
//        shop_work_time.setText(model.getWork_time());
//        shop_work_days.setText(model.getWork_date());
//        shop_phone_number.setText(model.getHome_phone());

    }

    private void getShopDetails(){
        Call<ShopShits> call = RetrofitClient.getmInstance().getApi().getShopDetails(String.valueOf(getShopid()));
        call.enqueue(new Callback<ShopShits>() {
            @Override
            public void onResponse(Call<ShopShits> call, Response<ShopShits> response) {
                Log.d("fragment", "onResponse: get shop Details really ?");
                if (response.isSuccessful() && response.body() != null){
                    Log.d("fragment", "onResponse: get shop Details response not null");

                    model = response.body().getDetail();
                }else{
                    Log.d("fragment", "onResponse: get shop Details response failed");
                }
            }

            @Override
            public void onFailure(Call<ShopShits> call, Throwable t) {
                Log.d("fragment", "onFailure: get shop Details failed");

            }
        });
    }
    private void justfottest(){
        retrofit2.Call<ShopShits> call = RetrofitClient.getmInstance().getApi().getShopDetails("23");
        call.enqueue(new Callback<ShopShits>() {
            @Override
            public void onResponse(retrofit2.Call<ShopShits> call, Response<ShopShits> response) {
                Log.d("infragment", "onResponse: connected");
                if (response.isSuccessful() && response.body() != null){
                    Log.d("infragment", "onResponse: so far so good ");

                    Detail detail = response.body().getDetail();
                    setmTextviews(detail);
                    if (detail == null){
                        Log.d("infragment", "onResponse: detail is empty ");
                    }else {
                        Log.d("infragment", "onResponse: sample info : " + detail.getAddress());
                    }
                }else {
                    Log.d("infragment", "onResponse: this is not working at all ");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ShopShits> call, Throwable t) {
                Log.d("infragment", "onFailure: really ? what is the fucking problem ?");
            }
        });

    }

    private void setmTextviews(Detail mdetail){
        shop_address.setText(mdetail.getAddress());
        shop_work_time.setText(mdetail.getWork_time());
        shop_work_days.setText(mdetail.getWork_date());
        shop_phone_number.setText(mdetail.getHome_phone());
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onُShopInfoFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onُShopInfoFragmentInteraction(Uri uri);
//    }
}
