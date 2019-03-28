package Service;

import Model.ApiToken;
import Model.LoginUser;
import Model.RegUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {
    @POST("signup")
    Call<String> createAccount(@Body RegUser regUser);

    @POST("login")
    Call<String> loginUser(@Body LoginUser loginUser);

}
