package com.example.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.HomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: HomePageBinding
private lateinit var dbRef: DatabaseReference
private lateinit var auth:FirebaseAuth
lateinit var dialog: Dialog
private lateinit var user: customUser
class HomePageActivity : AppCompatActivity() {
    private var textview: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        binding.btAddContent.setOnClickListener {
            val intent = Intent(this,postImageActivity::class.java)
            startActivity(intent)
        }
        binding.btGallery.setOnClickListener {
            val intent = Intent (this,GalleryActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        if (uid.isNotEmpty()){
            setProfile()
        }
        else{
            Toast.makeText(this,"USER NOT FOUND!!",Toast.LENGTH_SHORT).show()
        }

    }
    private fun setProfile() {

        val reference = FirebaseDatabase.getInstance().getReference("users")
            .child(auth.currentUser?.uid.toString())
//        dbRef.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                user = snapshot.getValue(customUser::class.java)!!
//                binding.namePlace.text = user.userName
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//         }
    }

    }