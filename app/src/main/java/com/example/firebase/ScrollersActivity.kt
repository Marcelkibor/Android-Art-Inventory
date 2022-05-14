package com.example.firebase

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ScrollersActivityBinding
import com.google.firebase.database.*
import java.util.*

private lateinit var dbRef:DatabaseReference
private lateinit var binding: ScrollersActivityBinding
private lateinit var recView: RecyclerView
private lateinit var postList: ArrayList<Model>
class ScrollersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScrollersActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recView = binding.galleryRecycler
        recView.layoutManager = LinearLayoutManager(this@ScrollersActivity)
        recView.setHasFixedSize(true)
        postList = arrayListOf<Model>()
        setTabs()
        postItem()
    }

    private fun setTabs() {
       val adapter = FragmentsAdapter(supportFragmentManager)
        adapter.addFrag(PaintFragment(),"PAINT")
        adapter.addFrag(StoneFragment(),"STONE")
        adapter.addFrag(WoodFragment(),"WOOD")
        adapter.addFrag(SteelFragment(),"STEEL")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        //set icons
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.paint)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.d4)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.d2)
        binding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.d11)
    }

    private fun postItem() {
        dbRef = FirebaseDatabase.getInstance().getReference("post")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (snap in snapshot.children){
                        val item = snap.getValue(Model::class.java)
                        postList.add(item!!)
                    }
                    var adapter = ScrollerAdapter(postList)
                    recView.adapter = adapter
                    adapter.setOnItemClickListener(object : ScrollerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val image = postList[position].imageUri
                            val name = postList[position].imageCapt
                            val ftrVal = postList[position].futureValue
                            val currVal = postList[position].itemPrice
                            val margin = postList[position].margin
                            val rate = postList[position].itemRate
                            val intent = Intent(this@ScrollersActivity,GalaDisplay::class.java)
                            intent.putExtra("image",image)
                            intent.putExtra("name",name)
                            intent.putExtra("ftrVal",ftrVal)
                            intent.putExtra("currVal",currVal)
                            intent.putExtra("margin",margin)
                            intent.putExtra("rate",rate)
                            startActivity(intent)
                        }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}

