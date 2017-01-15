package com.example.vladislav.nasaapi.mars;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladislav.nasaapi.ChangeFragmentListener;
import com.example.vladislav.nasaapi.R;

public class RoversFragment extends Fragment implements View.OnClickListener{

    CardView cvCuriosity;
    CardView cvOpportunity;
    CardView cvSpirit;

    ChangeFragmentListener changeFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachListener(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachListener(activity);
    }

    private void attachListener(@NonNull Context context){
        if (context instanceof ChangeFragmentListener) {
            changeFragmentListener = (ChangeFragmentListener) context;
        }else {
            throw new IllegalStateException("MainActivity must implements ChangeFragmentListener");
        }
    }

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
        cvOpportunity.setOnClickListener(this);
        cvSpirit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Class fragmentClass = DateChooseFragment.class;
        Bundle args = new Bundle();

        int viewId = v.getId();

        switch (viewId){
            case R.id.cv_curiosity:
                args.putString("rover", "curiosity");
                break;
            case R.id.cv_opportunity:
                args.putString("rover", "opportunity");
                break;
            case R.id.cv_spirit:
                args.putString("rover", "spirit");
                break;
        }

        changeFragmentListener.changeFragment(fragmentClass, args);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        changeFragmentListener = null;
    }
}
