package com.example.vladislav.nasaapi.mars;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladislav.nasaapi.R;

public class RoversFragment extends Fragment implements View.OnClickListener{

    CardView cvCuriosity;
    CardView cvOpportunity;
    CardView cvSpirit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rovers_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cvCuriosity = (CardView) view.findViewById(R.id.cv_curiosity);
        cvOpportunity = (CardView) view.findViewById(R.id.cv_opportunity);
        cvSpirit = (CardView) view.findViewById(R.id.cv_spirit);

        cvCuriosity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
