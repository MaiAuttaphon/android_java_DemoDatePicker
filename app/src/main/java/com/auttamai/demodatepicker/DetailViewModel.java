package com.auttamai.demodatepicker;

import android.app.Activity;
import android.text.format.DateFormat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.Date;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<Date> selectedDate = new MutableLiveData<>(Calendar.getInstance().getTime());

    public void setDate(Activity activity, Date date) {
        selectedDate.setValue(date);
    }

    public String setDateTH(Date date) {
        return "วันที่ "+
                (DateFormat.format("dd", date).toString()) + " "+
                intToMonth(Integer.parseInt(DateFormat.format("MM", date).toString())) + " " +
                (Integer.parseInt(DateFormat.format("yyyy", date).toString())+543)
                ;
    }

    private String intToMonth(int month) {
        switch(month) {
            case 1 :return "มกราคม";
            case 2 :return "กุมภาพันธ์";
            case 3 :return "มีนาคม";
            case 4 :return "เมษายน";
            case 5 :return "พฤษภาคม";
            case 6 :return "มิถุนายน";
            case 7 :return "กรกฎาคม";
            case 8 :return "สิงหาคม";
            case 9 :return "กันยายน";
            case 10 :return "ตุลาคม";
            case 11:return "พฤศจิกายน";
            case 12 :return "ธันวาคม";
            default : return "etc";
        }
    }

}