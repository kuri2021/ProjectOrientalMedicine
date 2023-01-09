package com.kuri2021.projectorientalmedicine

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SettingClass : Application(), Application.ActivityLifecycleCallbacks {

    companion object{
        lateinit var Firebasedatabase:FirebaseDatabase
        lateinit var Firebasestore:FirebaseFirestore
    }


    override fun onCreate() {
        super.onCreate()
        firebasereset()
    }

    fun firebasereset(){
        Firebasedatabase=Firebase.database
        Firebasestore=Firebase.firestore
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStarted(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        TODO("Not yet implemented")
    }
}