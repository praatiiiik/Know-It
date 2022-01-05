package com.example.knowit.ui.Fragment.rvAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.databinding.RecyclerviewBinding
import com.example.knowit.util.ImageStorageManager

class BusinessNewsAdapter(val context: Context?) : RecyclerView.Adapter<BusinessNewsAdapter.NewsViewHolder>() {

    private var newsList: ArrayList<BusinessNewsTable> = ArrayList()

    inner class NewsViewHolder(itemView: RecyclerviewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val imgView = itemView.imageView
        val title = itemView.titleTV
        val desc = itemView.desc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(
        RecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = newsList[position]
        holder.title.text = currentNews.article?.title
        holder.desc.text = currentNews.article?.description
        if(!currentNews.imageName.isNullOrEmpty()){
            holder.imgView.load(ImageStorageManager.getImageFromInternalStorage(context!!,currentNews.imageName.toString()))
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<BusinessNewsTable>){
        newsList.clear()
        newsList.addAll(newList)
        notifyDataSetChanged()
    }

}