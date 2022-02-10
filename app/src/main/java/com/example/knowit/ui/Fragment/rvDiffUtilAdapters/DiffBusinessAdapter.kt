package com.example.knowit.ui.Fragment.rvDiffUtilAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.knowit.R
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.databinding.RecyclerviewBinding
import com.example.knowit.util.ImageStorageManager

class DiffBusinessAdapter(
    val context: Context,
    private val onItemClicked: (BusinessNewsTable) -> Unit,
    private val onShareButtonClicked: (String) -> Unit
) :
    ListAdapter<BusinessNewsTable, DiffBusinessAdapter.BusinessListViewHolder>(DiffUtil()) {

    class BusinessListViewHolder(itemView: RecyclerviewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val imgView = itemView.imageView
        val title = itemView.titleTV
        val desc = itemView.desc
        val share = itemView.newsShare

        fun bind(
            data: BusinessNewsTable,
            context: Context?,
            onItemClicked: (BusinessNewsTable) -> Unit,
            onShareButtonClicked: (String) -> Unit
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessListViewHolder =
        BusinessListViewHolder(
            RecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BusinessListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context , onItemClicked,onShareButtonClicked)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<BusinessNewsTable>() {
        override fun areItemsTheSame(
            oldItem: BusinessNewsTable,
            newItem: BusinessNewsTable
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BusinessNewsTable,
            newItem: BusinessNewsTable
        ): Boolean {
            return oldItem.imageName == newItem.imageName
        }
    }

}