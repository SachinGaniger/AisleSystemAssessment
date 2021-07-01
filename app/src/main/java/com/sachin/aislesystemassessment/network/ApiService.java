package com.sachin.aislesystemassessment.network;

import com.sachin.aislesystemassessment.models.LoginResponse;
import com.sachin.aislesystemassessment.models.OtpResponse;
import com.sachin.aislesystemassessment.models.OtpVerify;
import com.sachin.aislesystemassessment.models.ProfileResponse;
import com.sachin.aislesystemassessment.models.User;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type:application/json")
    @POST("phone_number_login")
    Observable<LoginResponse> login(@Body User user,
                                    @Header("Cookie") String cookie);

    @Headers("Content-Type:application/json")
    @POST("verify_otp")
    Observable<OtpResponse> verifyOtp(@Body OtpVerify otpVerify,
                                      @Header("Cookie") String cookie);

    @GET("test_profile_list")
    Observable<ProfileResponse> getProfileList(@Header("Authorization") String token,
                                               @Header("Cookie") String cookie);


}
