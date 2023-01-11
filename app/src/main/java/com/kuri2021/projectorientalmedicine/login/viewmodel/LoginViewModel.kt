package com.kuri2021.projectorientalmedicine.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kuri2021.projectorientalmedicine.SettingClass
import com.kuri2021.projectorientalmedicine.common.User

class LoginViewModel(application: Application):AndroidViewModel(application) {

    var InputId =MutableLiveData<String>()
    var Inputpw =MutableLiveData<String>()
    var image=MutableLiveData<testdataclass>()

    val TAG="파이어베이스"

    private lateinit var database: DatabaseReference

    init {
        InputId.value=""
        Inputpw.value=""
        image.value?.image=""
    }

    fun writeNewUser(userId: String, name: String, email: String) {
        database = Firebase.database.reference
        val user = User(name, email)

        database.child("users").child(userId).setValue(user)
    }

    fun Login(){
//        val myRef = SettingClass.Firebasedatabase.getReference("aostest1")
//        myRef.setValue("Hello, World!2")

        database = Firebase.database.reference
        val user = User("가로", "세로","잠시 주차 중입니다","https://file-upload.talkplus.io/caa4a745-63fd-4c80-bb48-3d4a05ac0128/userprofiles/2022/12/29/08/1836-12-b589d0235c-22_12_29_17_18_387_aostest1.jpg")
        database.child("aostest2").setValue(user)
    }

    fun KakaoSignup() {
        database.child("aostest2").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

//    fun NaverSignup(){
//
//    }

    fun GoogleSignin(){

    }

    fun MainSignin(){

    }
}