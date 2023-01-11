package com.kuri2021.projectorientalmedicine.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.kuri2021.projectorientalmedicine.R
import com.kuri2021.projectorientalmedicine.databinding.ActivityMainBinding
import com.kuri2021.projectorientalmedicine.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.lifecycleOwner=this
        mBinding.mainViewModel=model
    }
}