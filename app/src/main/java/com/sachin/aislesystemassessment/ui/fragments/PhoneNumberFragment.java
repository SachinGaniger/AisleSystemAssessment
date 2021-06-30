package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sachin.aislesystemassessment.MainActivity;
import com.sachin.aislesystemassessment.R;
import com.sachin.aislesystemassessment.databinding.FragmentPhoneNumberBinding;

@AndroidEntryPoint
public class PhoneNumberFragment extends Fragment {

    FragmentPhoneNumberBinding phoneNumberBinding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        phoneNumberBinding = FragmentPhoneNumberBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);

        phoneNumberBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_phoneNumberFragment_to_otpFragment);
            }
        });

        return phoneNumberBinding.getRoot();
    }
}