package com.kuri2021.projectorientalmedicine.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.kuri2021.projectorientalmedicine.R
import com.kuri2021.projectorientalmedicine.SettingClass
import com.kuri2021.projectorientalmedicine.databinding.ActivityLoginBinding
import com.kuri2021.projectorientalmedicine.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {


    private lateinit var mBinding:ActivityLoginBinding
    private val model:LoginViewModel by viewModels()

    val TAG="파이어베이스"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)

        mBinding.lifecycleOwner=this
        mBinding.loginViewModel=model

        update()

    }



    fun update(){
//        val myRef = SettingClass.Firebasedatabase.getReference("message")
//
//        myRef.setValue("Hello, World!")
//

//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue<String>()
//                Log.d(TAG, "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
    }
}