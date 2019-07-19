package Service;

import java.util.List;

import entities.BankResponse;
import entities.Code;
import entities.Detail;
import entities.HeaderPics;
import entities.NearestShops;
import entities.Order;
import entities.Post;
import entities.ShopShits;
import entities.SubShop;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    @FormUrlEncoded
    @POST("signup")
    Call<String> userSignUp(
            //@HeaderMap Map<String, String> headers,
            @Field("name") String name,
            @Field("cell_phone") String cell_phone,
            @Field("password") String password,
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("google_signin")
    Call<String> googleSingIn(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("login")
    Call<String> userLogin(
            //@HeaderMap Map<String, String> headers,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("cooperation")
    Call<ResponseBody> cooperationRequest (
            @Field("field1") String F1,
            @Field("field2") String F2,
            @Field("field3") String F3,
            @Field("field4") String F4,
            @Field("field5") String F5,
            @Field("field6") String F6
    );

    @POST("search")
    Call<List<Post>> getsearchedposts(
            @Field("search_id") String search_id,
            @Field("city") String city,
            @Field("word") String word
    );

    @GET
    Call<ResponseBody> getImage(@Url String url);



    @FormUrlEncoded
    @POST("my_codes")
    Call<List<Code>> getmycodes (@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("subscribed_shop")
    Call<List<SubShop>> getSubscribe_shops(@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("subscribe_shop")
    Call<ResponseBody> subscribeToShop(
            @Field("api_token") String api_token,
            @Field("shop_id") Integer shop_id
    );



    @FormUrlEncoded
    @POST("discount_around_me")
    Call<List<NearestShops>> getNearestDiscounts(
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );

    @FormUrlEncoded
    @POST("mark_post")
    Call<ResponseBody> markPost(
            @Field("api_token") String api_token,
            @Field("shop_id") int shop_id
    );

    @FormUrlEncoded
    @POST("delete_marked_post")
    Call<ResponseBody> deleteMarkedPost(
            @Field("api_token") String api_token,
            @Field("shop_id") int shop_id
    );

    @FormUrlEncoded
    @POST("marked_post")
    Call<List<Post>> getMarkedPosts(@Field("api_token") String api_token);


    @FormUrlEncoded
    @POST("home_page_post")
    Call<List<Post>> getHomeRegPosts(
            @Field("city") String city,
            @Field("skip") int skip
    );

    @FormUrlEncoded
    @POST("home_page_hot_tag")
    Call<List<Post>> getHomeHotPosts(
            @Field("city") String city
    );

    @FormUrlEncoded
    @POST("home_page_ad")
    Call<List<HeaderPics>> getHomeHeaderPics(@Field("city") String city);

    @FormUrlEncoded
    @POST("shop_details")
    Call<ShopShits> getShopDetails(@Field("shop_id") String shop_id);

    @FormUrlEncoded
    @POST("shop_posts")
    Call<List<Post>> getShopPosts(@Field("shop_id") String shop_id);

    @FormUrlEncoded
    @POST("category")
    Call<List<Post>> getcategoryPosts(
            @Field("cat_id") String cat_id,
            @Field("city") String city
            );

    @FormUrlEncoded
    @POST("create_order")
    Call<BankResponse> create_order (
            @Field("orders") List<Order> orders
    );

}
