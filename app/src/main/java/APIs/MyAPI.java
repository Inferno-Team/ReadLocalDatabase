package APIs;

import results.LogInResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyAPI {
    String BASE_URL="http://10.0.3.2/wasim_folder/";
    @FormUrlEncoded
    @POST("log_in.php")
    Call<LogInResult>logIn(
            @Field(encoded = true,value = "username")String username,
            @Field(encoded = true,value = "password")String password
    );
}
