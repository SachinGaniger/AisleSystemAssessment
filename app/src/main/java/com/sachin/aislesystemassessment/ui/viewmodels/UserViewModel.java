package com.sachin.aislesystemassessment.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sachin.aislesystemassessment.models.LoginResponse;
import com.sachin.aislesystemassessment.models.OtpResponse;
import com.sachin.aislesystemassessment.models.OtpVerify;
import com.sachin.aislesystemassessment.models.ProfileResponse;
import com.sachin.aislesystemassessment.models.User;
import com.sachin.aislesystemassessment.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class UserViewModel extends ViewModel {

    public final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    private MutableLiveData<LoginResponse> numberStatus = new MutableLiveData<>();
    private MutableLiveData<OtpResponse> token = new MutableLiveData<>();
    private MutableLiveData<ProfileResponse> profile = new MutableLiveData<>();
    private static final String TAG = "UserViewModel";
    MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    boolean action = false;

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    @Inject
    public UserViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<LoginResponse> getNumberStatus(){
        return numberStatus;
    }

    public LiveData<OtpResponse> getToken(){
        return token;
    }

    public LiveData<ProfileResponse> getProfile(){
        return profile;
    }

    public void getNumberStatusFromRepo(User user){

        showProgress.postValue(true);

        disposable.add(repository.login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(status ->{
                numberStatus.postValue(status);
                showProgress.postValue(false);
            }, error ->{
                Log.i(TAG, "getNumberStatusFromRepo: " + error.getMessage());
                showProgress.postValue(false);
            })
        );

    }

    public LiveData<Boolean> getProgressStatus(){
        return showProgress;
    }

    public void getTokenFromRepo(OtpVerify otpVerify){

        showProgress.postValue(true);

        disposable.add(repository.verifyOtpFromApi(otpVerify)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(otpResponse -> {
                    showProgress.postValue(false);
                    token.postValue(otpResponse);
                }, error ->{
                    Log.i(TAG, "getTokenFromRepo: " + error.getMessage());
                    showProgress.postValue(false);
                })
        );

    }

    public void getProfileFromRepository(String token){

        showProgress.postValue(true);

        disposable.add(repository.getProfileFromApi(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profileResponse -> {
                    showProgress.postValue(false);
                    profile.postValue(profileResponse);
                }, error ->{
                    Log.i(TAG, "getTokenFromRepo: " + error.getMessage());
                    showProgress.postValue(false);
                })
        );

    }
}
