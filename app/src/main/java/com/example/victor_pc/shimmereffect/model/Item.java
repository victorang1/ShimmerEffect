package com.example.victor_pc.shimmereffect.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.drawable.GradientDrawable;

import com.example.victor_pc.shimmereffect.BR;

import java.io.Serializable;

public class Item extends BaseObservable implements Serializable {

    private GradientDrawable color;
    private String name;
    private String pekerjaan;
    private String gender;
    private Integer pageNumber;
    public ObservableField<Integer> fieldError = new ObservableField<>();
    public ObservableField<Integer> namaError = new ObservableField<>();

    public Item() {
    }

    public Item(String name, String pekerjaan, String gender, GradientDrawable gradientDrawable) {
        this.name = name;
        this.pekerjaan = pekerjaan;
        this.gender = gender;
        this.color = gradientDrawable;
    }

    public Item(String name, String pekerjaan, String gender, GradientDrawable gradientDrawable, Integer pageNumber) {
        this.name = name;
        this.pekerjaan = pekerjaan;
        this.gender = gender;
        this.color = gradientDrawable;
        this.pageNumber = pageNumber;
    }

    @Bindable
    public GradientDrawable getColor() {
        return color;
    }

    public Item setColor(GradientDrawable color) {
        this.color = color;
        notifyPropertyChanged(BR.color);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public String getPekerjaan() {
        return pekerjaan;
    }

    public Item setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
        notifyPropertyChanged(BR.pekerjaan);
        return this;
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public Item setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
        return this;
    }

    @Bindable
    public Integer getPageNumber() {
        return pageNumber;
    }

    public Item setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        notifyPropertyChanged(BR.pageNumber);
        return this;
    }
}
