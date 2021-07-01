package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sachin.aislesystemassessment.R;
import com.sachin.aislesystemassessment.databinding.FragmentPhoneNumberBinding;
import com.sachin.aislesystemassessment.models.LoginResponse;
import com.sachin.aislesystemassessment.models.User;
import com.sachin.aislesystemassessment.ui.viewmodels.UserViewModel;
import com.sachin.aislesystemassessment.utils.CommonUtil;

@AndroidEntryPoint
public class PhoneNumberFragment extends Fragment {

    FragmentPhoneNumberBinding phoneNumberBinding;
    NavController navController;
    UserViewModel userViewModel;
    String phoneNumber;
    private static final String TAG = "PhoneNumberFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        phoneNumberBinding = FragmentPhoneNumberBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

//        phoneNumberBinding.etPhoneNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                phoneNumberBinding.etPhoneNumber.getText().toString();
//            }
//        });

        phoneNumber = phoneNumberBinding.etCountryCode.getText().toString() + phoneNumberBinding.etPhoneNumber.getText().toString();

        Log.i(TAG, "onCreateView: "+ phoneNumber);

        userViewModel.getNumberStatusFromRepo(new User(phoneNumber));



        phoneNumberBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "navigateToNext: Clicked" );
                observeData();

            }
        });

        return phoneNumberBinding.getRoot();
    }

    private void observeData() {

        userViewModel.getNumberStatus().observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse response) {

                if (response.getStatus()){
                    navigateToNext();
                } else {
                    Log.i(TAG, "status is false");
                }
            }
        });

        userViewModel.getProgressStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (getActivity() != null) {
                    if (aBoolean != null && aBoolean) {
                        CommonUtil.showProgressBar(requireActivity());

                    } else {
                        CommonUtil.dismissProgressDialog();

                    }
                }
            }
        });

    }

    private void navigateToNext() {
        Log.i(TAG, "navigateToNext: Clicked.." );
        navController.navigate(R.id.action_phoneNumberFragment_to_otpFragment);
    }
}