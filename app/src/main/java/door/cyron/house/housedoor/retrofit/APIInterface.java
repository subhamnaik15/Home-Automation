package door.cyron.house.housedoor.retrofit;


import door.cyron.house.housedoor.acount.SignInResponseModel;
import door.cyron.house.housedoor.acount.SigninModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {


//    @FormUrlEncoded
//    @POST("login")
//    Call<SignInResponseModel> signIn(@Field("email") String email, @Field("type") String type, @Field("pass") String pass);

    @POST("login")
    Call<SignInResponseModel> signIn(@Body SigninModel signinModel);


    interface Header {
        String AUTHORIZATION = "Authorization";
        String TIMEZONE = "Timezone";

    }

//    @Multipart
//    @POST("patients/register")
//    Call<Response> register(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);


//    @GET("configurations")
//    Call<Configuration> downloadConfiguration();

//    @FormUrlEncoded
//    @POST("patients/get-otp")
//    Call<Response> requestOTP(@Field("uid") String mobileNo);

//    @POST
//    Call<Response> saveMedicineTrackerInfo(@Url String url, @Body SaveMedicineReadingRequestModel medicine);

}
