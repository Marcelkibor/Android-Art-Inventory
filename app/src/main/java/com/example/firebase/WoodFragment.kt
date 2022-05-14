package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.FragmentWoodBinding
import com.google.firebase.database.*
import java.util.*


private lateinit var binding: FragmentWoodBinding
private lateinit var dbRef:DatabaseReference
private lateinit var recView: RecyclerView
private lateinit var postList: ArrayList<Model>
class WoodFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View?{
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wood, container, false)

    }
}


