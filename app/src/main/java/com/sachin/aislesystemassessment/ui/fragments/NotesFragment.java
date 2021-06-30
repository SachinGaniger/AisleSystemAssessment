package com.sachin.aislesystemassessment.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sachin.aislesystemassessment.R;

import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }
}