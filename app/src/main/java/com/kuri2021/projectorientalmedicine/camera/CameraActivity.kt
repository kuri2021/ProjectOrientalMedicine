package com.kuri2021.projectorientalmedicine.camera

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.kuri2021.projectorientalmedicine.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    private var photoURI : Uri? = null
    private val REQUEST_CREATE_EX = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }

    fun createImageUri(filename:String, mimeType:String): Uri?{
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }



    private fun dispatchTakePictureIntentEx()
    {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val takePictureIntent : Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val uri : Uri? =   createImageUri("JPEG_${timeStamp}_", "image/jpg")
        photoURI = uri
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        startActivityForResult(takePictureIntent, REQUEST_CREATE_EX)
    }

    fun loadBitmapFromMediaStoreBy(photoUri: Uri) : Bitmap?{
        var image: Bitmap? = null
        try{
            image = if(Build.VERSION.SDK_INT > 27){
                val source: ImageDecoder.Source =
                    ImageDecoder.createSource(this.contentResolver, photoUri)
                ImageDecoder.decodeBitmap(source)

            }else{
                MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri)
            }
        }catch(e: IOException){
            e.printStackTrace()
        }
        return image
    }


}