package com.kuri2021.projectorientalmedicine.login

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kuri2021.projectorientalmedicine.R
import com.kuri2021.projectorientalmedicine.common.User
import com.kuri2021.projectorientalmedicine.common.User2
import com.kuri2021.projectorientalmedicine.databinding.ActivityLoginBinding
import com.kuri2021.projectorientalmedicine.login.viewmodel.LoginViewModel
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityLoginBinding
    private val model:LoginViewModel by viewModels()
    private lateinit var database: DatabaseReference
    private val GET_GALLERY_IMAGE = 200

    private val BUF_SIZE = 4096

    val TAG="파이어베이스"

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)

        mBinding.lifecycleOwner=this
        mBinding.loginViewModel=model

        update()

        findViewById<ImageView>(R.id.NaverSignin).setOnClickListener {
            database = Firebase.database.reference
            database.child("aostest3").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.child("carImage").value}")
                Log.i("firebase", "Got value ${it.child("length").value}")
                Log.i("firebase", "Got value ${it.child("parkingmessage").value}")
                Log.i("firebase", "Got value ${it.child("width").value}")
                findViewById<TextView>(R.id.testtext).text=it.value.toString()
//                val byteArrray: ByteArray = getBytes(it.child("carImage").value)
                Glide.with(this).load(it.child("carImage").value).into(findViewById<ImageView>(R.id.GoogleSignin))
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
        }

        findViewById<TextView>(R.id.FindId).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }

//        try {
//            val information =
//                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
//            val signatures = information.signingInfo.apkContentsSigners
//            val md = MessageDigest.getInstance("SHA")
//            for (signature in signatures) {
//                val md: MessageDigest
//                md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                var hashcode = String(Base64.encode(md.digest(), 0))
//                Log.d("hashcode", "" + hashcode)
//            }
//        } catch (e: Exception) {
//            Log.d("hashcode", "에러::" + e.toString())
//
//        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri: Uri? = data.data
//            Glide.with(this).load(selectedImageUri).into(findViewById<ImageView>(R.id.GoogleSignin))
            var data=getContentData(selectedImageUri.toString())
            Glide.with(this).load(data).into(findViewById<ImageView>(R.id.GoogleSignin))
            database = Firebase.database.reference
            val user = User2("가로", "세로","잠시 주차 중입니다",selectedImageUri.toString())
            database.child("aostest3").setValue(user)
        }
    }
    fun getContentData(uri: String?): ByteArray? {
        if (uri == null) {
            return null
        }
        val out = ByteArrayOutputStream()
        var pt: InputStream? = null
        val buf = ByteArray(BUF_SIZE)
        var len: Int
        return try {
            val r: ContentResolver = getContentResolver()
            pt = r.openInputStream(Uri.parse(uri))
            if (pt != null) {
                while (pt.read(buf).also { len = it } > 0) {
                    out.write(buf, 0, len)
                }
            }
            out.toByteArray()
        } catch (e: IOException) {
            null
        } finally {
            if (pt != null) {
                try {
                    pt.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    // 바이너리 바이트 배열을 스트링으로
//    fun byteArrayToBinaryString(b: ByteArray): String? {
//        val sb = StringBuilder()
//        for (i in b.indices) {
//            sb.append(byteToBinaryString(b[i]))
//        }
//        return sb.toString()
//    }
//
//    // 바이너리 바이트를 스트링으로
//    fun byteToBinaryString(n: Byte): String? {
//        val sb = StringBuilder("00000000")
//        for (bit in 0..7) {
//            if (n shr bit and 1 > 0) {
//                sb.setCharAt(7 - bit, '1')
//            }
//        }
//        return sb.toString()
//    }

//    fun updateFirebase(){
//        var drawable=resources.getDrawable(R.drawable.main_logo)
//        var simage=""
//        val bitmap = (image as BitmapDrawable).bitmap
//    }

    fun update(){
//        val myRef = Firebasedatabase.getReference("한글")
//
//        myRef.setValue("한글 테스트")


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