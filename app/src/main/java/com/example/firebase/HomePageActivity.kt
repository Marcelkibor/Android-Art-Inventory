package com.example.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.HomePageSecondBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

private lateinit var binding: HomePageSecondBinding
private lateinit var dbRef: DatabaseReference
private lateinit var auth:FirebaseAuth
lateinit var dialog: Dialog
private lateinit var user: customUser
class HomePageActivity : AppCompatActivity() {
    private var textview: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btGal.setOnClickListener(){
            val intent = Intent(this,ScrollersActivity::class.java)
            startActivity(intent)
        }
        binding.btHomeCreate.setOnClickListener(){
            val intent = Intent(this,PickerActivity::class.java)
            startActivity(intent)
        }

    }
}