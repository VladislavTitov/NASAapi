package com.example.vladislav.nasaapi.mars;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.vladislav.nasaapi.R;

import java.util.Calendar;

public class DateChooseFragment extends Fragment {

    private String rover;

    TextView tvDateShow;
    TextView tvDateStartMission;
    TextView tvDateFinishMission;

    Button btnDateChoose;

    private int year, month, day;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    private void callDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-" + dayOfMonth;
                tvDateShow.setText(date);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

}
