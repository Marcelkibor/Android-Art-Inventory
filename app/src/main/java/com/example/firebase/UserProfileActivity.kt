package com.example.firebase
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.UserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

private lateinit var auth: FirebaseAuth
private lateinit var binding: UserProfileBinding
class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = UserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get the views
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            binding.profileImageView.setImageURI(it)
        }
        binding.profileImageView.setOnClickListener() {
            getImage.launch("image/*")
        }
        binding.profilePost
            .setOnClickListener() {
                progressShow()
                    binding.tvimageUrl.text
            postData()
        }
    }

    private fun progressShow() {
        val progressShow = ProgressDialog(this)
        progressShow.setMessage("GETTING IMAGE URI...")
        progressShow.setCancelable(true)
        progressShow.show()
    }
    private fun progressHide() {
        val progressShow = ProgressDialog(this)
        progressShow.setMessage("GETTING IMAGE URI...")
        progressShow.setCancelable(true)
        progressShow.hide()
    }


    private fun postData() {
        val currentUser = auth.currentUser?.uid
        val imageView = binding.profileImageView
        if(currentUser!=null) {
            val profileStore = FirebaseStorage.getInstance().getReference("Profile")
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            //push the file to the database location
            profileStore.child(currentUser).putBytes(data)
                .continueWith (){task->
                            if(task.isSuccessful){
//                                profileStore.downloadUrl.addOnCompleteListener() {job->
//                                 = task.toString()
                                    Toast.makeText(this, "UPLOAD ACCESSIBLE", Toast.LENGTH_SHORT)
                                        .show()
                                progressHide()
                                }

                            else{
                                Toast.makeText(this, "DOWNLOAD URI FETCHING ERROR!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
            }
   }


