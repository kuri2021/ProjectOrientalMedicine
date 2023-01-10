package com.kuri2021.projectorientalmedicine.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kuri2021.projectorientalmedicine.SettingClass

class LoginViewModel(application: Application):AndroidViewModel(application) {

    var InputId =MutableLiveData<String>()
    var Inputpw =MutableLiveData<String>()

    val TAG="파이어베이스"

    init {
        InputId.value=""
        Inputpw.value=""
    }


    fun Login(){
        val myRef = SettingClass.Firebasedatabase.getReference("message")

        myRef.setValue("Hello, World!2")
    }

    fun KakaoSignup() {
// Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

// Add a new document with a generated ID
        SettingClass.Firebasestore.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    fun NaverLogin(){
        SettingClass.Firebasestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun GoogleSignin(){

    }

    fun MainSignin(){

    }
}