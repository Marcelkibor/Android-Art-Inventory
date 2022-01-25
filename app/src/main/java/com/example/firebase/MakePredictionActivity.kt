package com.example.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.databinding.GalleryProfileBinding
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.*

private lateinit var dbRef:DatabaseReference
private lateinit var binding: GalleryProfileBinding
private lateinit var postArrayList: ArrayList<PostedCont>
class MakePredictionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = MakePredictionActivity.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        val galaRecycler = binding.galleryRecycler
        galaRecycler.apply {
            layoutManager = LinearLayoutManager(this@GalleryActivity)
            galaRecycler.hasFixedSize()
            val spacer = SpacingDecorator(10)
            galaRecycler.addItemDecoration(spacer)

        }
        postArrayList = arrayListOf<PostedCont>()
        fetchRecords()

    }

    private fun fetchRecords() {
        dbRef  = FirebaseDatabase.getInstance().getReference("post")
        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (postSnap in snapshot.children){
                      val post = postSnap.getValue(PostedCont::class.java)
                    postArrayList.add(post!!)
                    }
                    binding.galleryRecycler.adapter = galleryAdapter(postArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })

}}