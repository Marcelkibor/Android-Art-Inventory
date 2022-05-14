package com.example.firebase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.single_profile.view.*

class galleryAdapter(private val post:ArrayList<Model>):RecyclerView.Adapter<galleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(itemView:View, listener :onItemClickListener): RecyclerView.ViewHolder(itemView){

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_profile,parent,false)
        return GalleryViewHolder(itemView,mylistener)
    }
    override fun onBindViewHolder(holder: GalleryViewHolder,position: Int) {
        val currentPost = post[position]
        holder.itemView.apply {
        galTitle.text = currentPost.imageCapt
       Glide.with(context).load(currentPost.imageUri).into(galImage)

   }
}

    override fun getItemCount(): Int {
        return post.size
    }

}
