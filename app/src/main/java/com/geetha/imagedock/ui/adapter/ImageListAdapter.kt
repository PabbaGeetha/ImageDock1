package com.geetha.imagedock.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geetha.imagedock.data.model.Hits
import com.geetha.imagedock.databinding.ImageItemBinding
import com.geetha.imagedock.ui.ImageClickListener

class ImageListAdapter(
    private val mContext: Context,
    private val clickListener: ImageClickListener
) :
    RecyclerView.Adapter<ImageListAdapter.MyViewHolder>() {

    private var mImagesList = ArrayList<Hits>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(itemView, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindImage(mImagesList[position])

    }

    override fun getItemCount(): Int {
        return mImagesList.size
    }

    inner class MyViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindImage(hit: Hits) {
            Glide.with(mContext).load(hit.previewURL)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgView)
            binding.tvUserName.text = hit.user
            binding.tvTags.text = hit.tags
            binding.cardView.setOnClickListener {
                clickListener.onClick(hit)
            }
        }
    }

    /**
     * updating the adapter
     */
    fun updateUserList(imagesList: List<Hits>?) {
                this.mImagesList = (imagesList as ArrayList<Hits>?)!!
                notifyDataSetChanged()
    }
}