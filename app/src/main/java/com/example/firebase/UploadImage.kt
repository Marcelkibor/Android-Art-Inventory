package com.example.firebase

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.UploadImageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

private lateinit var auth: FirebaseAuth
private lateinit var imageUri: Uri
private lateinit var binding: UploadImageBinding
@Suppress("UnusedEquals")
class UploadImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = UploadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postBtn = binding.postBtn
        //starting the activity to get the images

        postBtn.setOnClickListener() {
            //call the function for opening and selecting intent
            postContent()
        }
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            binding.SecondImageView
                .setImageURI(it)

        }

        //tap on icon to select the image

        binding.SecondImageView.setOnClickListener() {
            getImage.launch("image/*")
        }

    }
    private fun postContent() {
        val storageRef = FirebaseStorage.getInstance().getReference("images")
        val imageView = binding.SecondImageView
        val progressBar = ProgressDialog(this)
        progressBar.setMessage("Image Uploading...")
        progressBar.setCancelable(true)
        progressBar.show()
        // get current userID And set it as a child for the storage reference
        val currentUser = auth.currentUser?.uid
        if (currentUser!=null){
            // Get the data from an ImageView as bytes
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            // put the uid as a child for the database storage
            storageRef.child(currentUser).putBytes(data).addOnCompleteListener(){
                if (it.isSuccessful){
                    Toast.makeText(this,"Uploaded successfully",Toast.LENGTH_SHORT).show()
                    val intent =Intent(this, myProfile::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
            }
        }
//            }
////structure for adding timestamp to a file as a field
//val formatter = SimpleDateFormat("yyyy_MM_dd_mm_ss", Locale.getDefault())
//val now = Date()
//val fileName = formatter.format(now)
