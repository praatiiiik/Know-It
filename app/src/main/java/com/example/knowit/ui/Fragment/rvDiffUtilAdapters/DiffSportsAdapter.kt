package com.example.knowit.ui.Fragment.rvDiffUtilAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.knowit.R
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.databinding.RecyclerviewBinding
import com.example.knowit.util.ImageStorageManager

class DiffSportsAdapter(
    val context: Context,
    private val onItemClicked: (SportsNewsTable) -> Unit,
    private val onShareButtonClicked : (String) -> Unit
) : ListAdapter<SportsNewsTable, DiffSportsAdapter.SportsListViewHolder>(DiffUtil()) {

    class SportsListViewHolder(itemView: RecyclerviewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val imgView = itemView.imageView
        val title = itemView.titleTV
        private val desc = itemView.desc
        private val share = itemView.newsShare

        fun bind(
            data: SportsNewsTable,
            context: Context?,
            onItemClicked: (SportsNewsTable) -> Unit,
            onShareButtonClicked : (String) -> Unit
        ) {
            title.text = data.article?.title
            desc.text = data.article?.description
            if (!data.imageName.isNullOrEmpty()) {
                imgView.load(
                    ImageStorageManager.getImageFromInternalStorage(
                        context!!,
                        data.imageName.toString()
                    )
                )
            } else {
                imgView.load(R.drawable.ic_baseline_refresh_24)
            }
            desc.setOnClickListener {
                onItemClicked(data)
            }
            share.setOnClickListener{
                onShareButtonClicked(data.article?.url!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsListViewHolder =
        SportsListViewHolder(
            RecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: SportsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context ,onItemClicked,onShareButtonClicked)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<SportsNewsTable>() {
        override fun areItemsTheSame(oldItem: SportsNewsTable, newItem: SportsNewsTable): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SportsNewsTable,
            newItem: SportsNewsTable
        ): Boolean {
            return oldItem.imageName == newItem.imageName
        }
    }


}