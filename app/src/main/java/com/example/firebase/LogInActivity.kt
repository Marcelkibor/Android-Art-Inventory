package com.example.firebase

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth
private lateinit var binding: LoginBinding
class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get the views
        binding.SignUp.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.signIN.setOnClickListener() {
            authenticate()
        }
    }

    private fun authenticate() {
        val progressBar = ProgressDialog(this)
        progressBar.setMessage("HOLD ON A LITTLE...")
        progressBar.setCancelable(true)
        progressBar.show()
        auth.signInWithEmailAndPassword(binding.tvemail.text.toString(), binding.tvpassword.text.toString())
        .addOnCompleteListener(){task->
            if(task.isSuccessful){
                val intent = Intent(this,HomePageActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Successfully Logged in",Toast.LENGTH_SHORT).show()
                finish()

            }
            else{
                Toast.makeText(this,task.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}