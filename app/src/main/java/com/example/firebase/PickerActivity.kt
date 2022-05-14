package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.HomePageBinding

private lateinit var binding: HomePageBinding
private lateinit var user: customUser
class PickerActivity : AppCompatActivity() {
    private var textview: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.stoneCard.setOnClickListener(){
            val intent = Intent(this,postImageActivity::class.java)
            startActivity(intent)
        }
        binding.painCard.setOnClickListener(){
            val intent = Intent(this,postImageActivity::class.java)
            startActivity(intent)
        }
        binding.woodCard.setOnClickListener(){
            val intent = Intent(this,postImageActivity::class.java)
            startActivity(intent)
        }
        binding.steelCard.setOnClickListener(){
            val intent = Intent(this,postImageActivity::class.java)
            startActivity(intent)
        }
    }
}