package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sachin.aislesystemassessment.R;
import com.sachin.aislesystemassessment.databinding.FragmentOtpBinding;
import com.sachin.aislesystemassessment.databinding.FragmentPhoneNumberBinding;
import com.sachin.aislesystemassessment.models.OtpResponse;
import com.sachin.aislesystemassessment.models.OtpVerify;
import com.sachin.aislesystemassessment.ui.viewmodels.UserViewModel;
import com.sachin.aislesystemassessment.utils.CommonUtil;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtpFragment extends Fragment {

    FragmentOtpBinding otpBinding;
    NavController navController;
    UserViewModel userViewModel;
    Bundle bundle;
    private static final String TAG = "OtpFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        otpBinding = FragmentOtpBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        bundle = new Bundle();

        Log.i(TAG, "onCreateView: " + OtpFragmentArgs.fromBundle(getArguments()).getPhoneNumber());

        otpBinding.tvNumber.setText(OtpFragmentArgs.fromBundle(getArguments()).getPhoneNumber());

        String number = OtpFragmentArgs.fromBundle(getArguments()).getNumber();


        otpBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!otpBinding.etOtp.getText().toString().isEmpty() && otpBinding.etOtp.getText().toString() != null){
                    String otp = otpBinding.etOtp.getText().toString();

                    userViewModel.getTokenFromRepo(new OtpVerify(otp, number));

                    observeData();
                }

            }
        });

        return otpBinding.getRoot();
    }

    private void observeData() {

        userViewModel.getToken().observe(getViewLifecycleOwner(), new Observer<OtpResponse>() {
            @Override
            public void onChanged(OtpResponse otpResponse) {
                if (otpResponse.getToken() != null){
                    bundle.putString("token", otpResponse.getToken());
                    navigateNext(bundle);
                } else {
                    Toast.makeText(getActivity(), "Wrong OTP", Toast.LENGTH_SHORT).show();
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

    private void navigateNext(Bundle bundle) {

        navController.navigate(R.id.action_otpFragment_to_notesFragment, bundle);

    }
}