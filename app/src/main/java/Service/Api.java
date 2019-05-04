package Service;

import java.util.List;

import entities.Code;
import entities.NearestShops;
import entities.Post;
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
    @POST("signup/client")
    Call<String> userSignUp(
            //@HeaderMap Map<String, String> headers,
            @Field("name") String name,
            @Field("cell_phone") String cell_phone,
            @Field("password") String password,
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("login/client")
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

    @GET("search/{keyword}")
    Call<List<Post>> searchbar(@Query("keyword") String keyword);

    @GET
    Call<ResponseBody> getImage(@Url String url);



    @FormUrlEncoded
    @POST("my_codes")
    Call<List<Code>> getmycodes (@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("/subscribed_shop")
    Call<ResponseBody> getSubscribe_shops(@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("")
    Call<ResponseBody> subscribeToShop(
            @Field("api_token") String api_token,
            @Field("shop_id") Integer shop_id
    );



    @FormUrlEncoded
    @POST("/discount_around_me")
    Call<List<NearestShops>> getNearestDiscounts(
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );


}
