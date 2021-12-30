package com.example.scopedstorageyt

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.scopedstorageyt.databinding.ActivityReadExternalStorageBinding


class ReadExternalStorage : AppCompatActivity() {

    private lateinit var binding : ActivityReadExternalStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadExternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){

        val pickPhoto = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                binding.firebaseimage.setImageURI(it)

            }
        )

        binding.selectImagebtn.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                pickPhoto.launch("image/*")

            }else{

                Toast.makeText(this,"Read Permission is not Granted", Toast.LENGTH_SHORT).show()

            }

        }


    }

}