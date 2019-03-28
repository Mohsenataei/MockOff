package Service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

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
}
