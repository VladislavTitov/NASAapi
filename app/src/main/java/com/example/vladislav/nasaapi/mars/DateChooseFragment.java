package com.example.vladislav.nasaapi.mars;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.vladislav.nasaapi.ChangeFragmentListener;
import com.example.vladislav.nasaapi.R;

import java.util.Calendar;

public class DateChooseFragment extends Fragment {

    private String rover;

    TextView tvDateShow;
    TextView tvDateStartMission;
    TextView tvDateFinishMission;

    Button btnDateChoose;
    Button btnShowPhotos;

    private int year, month, day;

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
        if (getArguments() != null && getArguments().getString("rover") != null){
            rover = getArguments().getString("rover");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_choose_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDateShow = (TextView) view.findViewById(R.id.tv_date_show);
        tvDateStartMission = (TextView) view.findViewById(R.id.tv_date_start_mission);
        tvDateFinishMission = (TextView) view.findViewById(R.id.tv_date_finish_mission);

        btnDateChoose = (Button) view.findViewById(R.id.btn_date_choose);
        btnDateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDatePicker();
            }
        });

        btnShowPhotos = (Button) view.findViewById(R.id.btn_show_photos);
        btnShowPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class fragmentClass = PhotosShowFragment.class;
                Bundle args = new Bundle();
                args.putString("rover", rover);
                args.putString("date", tvDateShow.getText().toString());
                changeFragmentListener.changeFragment(fragmentClass, args);
            }
        });

    }

    private void callDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1)+ "-" + dayOfMonth;
                tvDateShow.setText(date);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        changeFragmentListener = null;
    }

}
