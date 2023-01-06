package com.kuri2021.projectorientalmedicine.login.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    MutableLiveData<String> InputId = new MutableLiveData<>();
    MutableLiveData<String> InputPw = new MutableLiveData<>();


}
