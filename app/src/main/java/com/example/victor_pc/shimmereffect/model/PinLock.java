package com.example.victor_pc.shimmereffect.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.victor_pc.shimmereffect.BR;
import com.example.victor_pc.shimmereffect.R;

public class PinLock extends BaseObservable {

    public static String lockNumber;
    public static Boolean isValid = false;
    public static Boolean isRegistered = false;

    @Bindable
    public String getLockNumber() {
        return lockNumber;
    }

    public PinLock setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
        notifyPropertyChanged(BR.lockNumber);
        return this;
    }

    @Bindable
    public Boolean getValid() {
        return isValid;
    }

    public PinLock setValid(Boolean valid) {
        isValid = valid;
        notifyPropertyChanged(BR.valid);
        return this;
    }

    @Bindable
    public Boolean getRegistered() {
        return isRegistered;
    }

    public PinLock setRegistered(Boolean registered) {
        isRegistered = registered;
        notifyPropertyChanged(BR.registered);
        return this;
    }
}
