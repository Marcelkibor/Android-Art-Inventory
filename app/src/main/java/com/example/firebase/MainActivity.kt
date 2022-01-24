package com.example.firebase
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityMainBinding
private lateinit var auth:FirebaseAuth
//this is used as the registration activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SignIn.setOnClickListener() {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        val submit = binding.signup
        //define action for onSubmit
        submit.setOnClickListener() {
            progShow()
            checkCredentials()
        }
    }

    fun progShow() {
        val progressShow = ProgressDialog(this)
        progressShow.setMessage("Creating an account for you...")
        progressShow.setCancelable(true)
        progressShow.show()
        return
    }

    fun hideProgShow() {
        val progressShow = ProgressDialog(this)
        progressShow.hide()
    }

    //verify the credentials
    fun checkCredentials() {
        val username = binding.tvUserName
        val email = binding.tvemail
        val password = binding.tvpassword
        if (email.text.toString().isEmpty()) {
            email.error = "please enter email"
            email.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Password cannot be empty"
            password.requestFocus()
            return
        }
        if (password.length() < 5) {
            password.error = "password too short!"
            password.requestFocus()
            return
        }
        if (username.text.toString().isEmpty()) {
            username.error = "Username cannot be empty"
        } else {
            //initialize the storage reference
            auth.createUserWithEmailAndPassword(
                binding.tvemail.text.toString(),
                binding.tvpassword.text.toString()
            )
                .addOnCompleteListener() { task->
                    if (task.isSuccessful){
                        val userName = binding.tvUserName.text.toString()
                        val eMail = binding.tvemail.text.toString()
                        val dbRef = FirebaseDatabase.getInstance().getReference("users")
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            val myCustomUser = customUser(userId, userName, eMail)
                            dbRef.child(userId).setValue(myCustomUser).addOnSuccessListener {
                                val intent = Intent(this, LogInActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this, "Custom user registered", Toast.LENGTH_SHORT)
                                    .show()
                                hideProgShow()
                                finish()
                            }
                        }
                        else{
                            Toast.makeText(this, "Custom reg error!", Toast.LENGTH_SHORT).show()
                            hideProgShow()
                        }
                    }
                    else{
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                        hideProgShow()
                            }
                }
                        }
                    }
}