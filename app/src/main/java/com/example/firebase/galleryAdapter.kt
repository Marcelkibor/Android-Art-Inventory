package com.example.firebase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.single_profile.view.*

class galleryAdapter(private val post: ArrayList<PostedCont>):RecyclerView.Adapter<galleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
           return GalleryViewHolder(
               LayoutInflater.from(parent.context).inflate(
                   R.layout.single_profile,
                   parent,
                   false
               )
           )
    }
    override fun onBindViewHolder(holder: GalleryViewHolder,position: Int) {
        val currentPost = post[position]
        holder.itemView.apply {
        galTitle.text = currentPost.imageCapt
       Glide.with(context).load(currentPost.imageUri).into(galImageView)

   }
}

    override fun getItemCount(): Int {
        return post.size
    }

}
