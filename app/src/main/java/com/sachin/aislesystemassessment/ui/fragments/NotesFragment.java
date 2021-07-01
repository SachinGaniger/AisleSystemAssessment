package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sachin.aislesystemassessment.R;
import com.sachin.aislesystemassessment.databinding.FragmentNotesBinding;
import com.sachin.aislesystemassessment.databinding.FragmentOtpBinding;
import com.sachin.aislesystemassessment.models.Photo;
import com.sachin.aislesystemassessment.models.ProfileResponse;
import com.sachin.aislesystemassessment.ui.adapters.LikesAdapter;
import com.sachin.aislesystemassessment.ui.viewmodels.UserViewModel;
import com.sachin.aislesystemassessment.utils.CommonUtil;
import com.sachin.aislesystemassessment.utils.GridSpacingItemDecoration;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotesFragment extends Fragment {

    FragmentNotesBinding notesBinding;
    NavController navController;
    UserViewModel userViewModel;

    private static final String TAG = "NotesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        notesBinding = FragmentNotesBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);

        Log.i(TAG, "onCreateView: " + NotesFragmentArgs.fromBundle(getArguments()).getToken());

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getProfileFromRepository(NotesFragmentArgs.fromBundle(getArguments()).getToken());

        observeData();

        return notesBinding.getRoot();
    }

    private void observeData() {

        userViewModel.getProfile().observe(getViewLifecycleOwner(), new Observer<ProfileResponse>() {
            @Override
            public void onChanged(ProfileResponse profileResponse) {

                updateUi(profileResponse);

            }
        });

        userViewModel.getProgressStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (getActivity() != null) {
                    if (aBoolean != null && aBoolean) {
                        CommonUtil.showProgressBar(requireActivity());
                        notesBinding.nsvNotes.setVisibility(View.INVISIBLE);
                    } else {
                        CommonUtil.dismissProgressDialog();
                        notesBinding.nsvNotes.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    private void updateUi(ProfileResponse profileResponse) {

        String nameAge = profileResponse.getInvites().getProfiles().get(0).getGeneralInformation().getFirstName() + ", " + profileResponse.getInvites().getProfiles().get(0).getGeneralInformation().getAge();
        Log.i(TAG, "onChanged: nameAge "+ nameAge);

        notesBinding.tvNameAge.setText(nameAge);

        List<Photo> photos = profileResponse.getInvites().getProfiles().get(0).getPhotos();

        for (int i=0; i<photos.size(); i++){

            if (photos.get(i).isSelected() && photos.get(i).getStatus().equals("avatar")){
                Glide.with(getActivity())
                        .load(photos.get(i).getPhoto())
                        .centerCrop()
//                .fitCenter()
                        .into(notesBinding.ivProfile);
            }

        }

        int spanCount = 2;
        int spacing = 22;
        notesBinding.rvLikesProfile.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        notesBinding.rvLikesProfile.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));

        LikesAdapter likesAdapter = new LikesAdapter(profileResponse.getLikes().getProfiles(), profileResponse.getLikes().getCanSeeProfile(), getActivity());
        notesBinding.rvLikesProfile.setAdapter(likesAdapter);

    }
}