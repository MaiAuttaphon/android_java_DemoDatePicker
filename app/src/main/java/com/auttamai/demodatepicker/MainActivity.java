package com.auttamai.demodatepicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.LinearLayout;

import com.auttamai.demodatepicker.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomSheetBehavior<LinearLayout> barsheetaccount;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        binding.buttonTH.setOnClickListener(view1 -> {
            openCalenda();
        });

        binding.buttonEN.setOnClickListener(view1 -> {

        });

        barsheetaccount = BottomSheetBehavior.from(binding.bs);
        barsheetaccount.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    binding.bottomSheetContainer.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                    setSelectedItemPosition();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        barsheetaccount.setHideable(true);
        barsheetaccount.setState(BottomSheetBehavior.STATE_HIDDEN);
        binding.bottomSheetContainer.setOnClickListener(v -> barsheetaccount.setState(BottomSheetBehavior.STATE_HIDDEN));
        binding.setDatePicker.setOnClickListener(v -> {
            Date date = binding.picker.getCurrentDate();
            viewModel.setDate(this,date);
            binding.textDate.setText(date.toString());
            barsheetaccount.setState(BottomSheetBehavior.STATE_HIDDEN);
        });

        setDefaultDate();
    }

    private void setDefaultDate() {
        binding.picker.setYearEnd(Calendar.getInstance().get(Calendar.YEAR));
        Date date = Calendar.getInstance().getTime();
        binding.picker.setSelectedDay(Integer.parseInt(DateFormat.format("dd", date).toString()));
        binding.picker.setSelectedMonth(Integer.parseInt(DateFormat.format("MM", date).toString()));
        binding.picker.setSelectedYear(Integer.parseInt(DateFormat.format("yyyy", date).toString()));
        viewModel.setDate(this,date);
    }

    public void openCalenda() {
        binding.bottomSheetContainer.setVisibility(View.VISIBLE);
        barsheetaccount.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Date date = viewModel.selectedDate.getValue();
        binding.picker.setSelectedDay(Integer.parseInt(DateFormat.format("dd", date).toString()));
        binding.picker.setSelectedMonth(Integer.parseInt(DateFormat.format("MM", date).toString()));
        binding.picker.setSelectedYear(Integer.parseInt(DateFormat.format("yyyy", date).toString()));
    }
}