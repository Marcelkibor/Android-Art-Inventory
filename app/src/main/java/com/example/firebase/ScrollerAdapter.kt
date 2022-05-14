package com.example.firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebase.ScrollerAdapter.ScrollerViewHolder
import kotlinx.android.synthetic.main.single_profile.view.*

class ScrollerAdapter(private val post: ArrayList<Model>):RecyclerView.Adapter<ScrollerViewHolder>() {
    class ScrollerViewHolder(itemView: View, listener: onItemClickListener, ):RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
    private lateinit var mylistener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mylistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrollerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_profile,parent,false)
        return ScrollerViewHolder(itemView,mylistener)
    }

    override fun onBindViewHolder(holder: ScrollerViewHolder, position: Int) {
        val currItem = post[position]
        holder.itemView.apply {
            Glide.with(context).load(currItem.imageUri).into(galImage)
            galTitle.text = currItem.imageCapt

        }
    }

    override fun getItemCount(): Int {
        return post.size
    }
}