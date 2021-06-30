package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sachin.aislesystemassessment.R;
import com.sachin.aislesystemassessment.databinding.FragmentOtpBinding;
import com.sachin.aislesystemassessment.databinding.FragmentPhoneNumberBinding;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtpFragment extends Fragment {

    FragmentOtpBinding otpBinding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        otpBinding = FragmentOtpBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);

        otpBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_otpFragment_to_notesFragment);
            }
        });

        return otpBinding.getRoot();
    }
}