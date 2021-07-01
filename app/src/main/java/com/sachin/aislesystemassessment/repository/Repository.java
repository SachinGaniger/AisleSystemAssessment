package com.sachin.aislesystemassessment.repository;

import com.sachin.aislesystemassessment.models.LoginResponse;
import com.sachin.aislesystemassessment.models.OtpResponse;
import com.sachin.aislesystemassessment.models.OtpVerify;
import com.sachin.aislesystemassessment.models.ProfileResponse;
import com.sachin.aislesystemassessment.models.User;
import com.sachin.aislesystemassessment.network.ApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

import static com.sachin.aislesystemassessment.utils.Constants.COOKIE;

public class Repository {

    private ApiService apiService;

    @Inject
    public Repository(ApiService _apiService){
        this.apiService = _apiService;
    }

    public Observable<LoginResponse> login(User user){
        return apiService.login(user, COOKIE);
    }


    public Observable<OtpResponse> verifyOtpFromApi(OtpVerify otpVerify){
        return apiService.verifyOtp(otpVerify, COOKIE);
    }

    public Observable<ProfileResponse> getProfileFromApi(String token){
        return apiService.getProfileList(token, COOKIE);
    }

}
