package com.example.knowit.ui.Fragment.rvDiffUtilAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.knowit.R
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.databinding.RecyclerviewBinding
import com.example.knowit.util.ImageStorageManager

class DiffGeneralAdapter(
    val context: Context,
    private val onItemClicked: (GeneralNewsTable) -> Unit,
    private val onShareButtonClicked: (String) -> Unit
) : ListAdapter<GeneralNewsTable, DiffGeneralAdapter.GeneralListViewHolder>(DiffUtil()) {


    class GeneralListViewHolder(itemView: RecyclerviewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val imgView = itemView.imageView
        val title = itemView.titleTV
        val desc = itemView.desc
        val share = itemView.newsShare


        fun bind(
            data: GeneralNewsTable,
            context: Context?,
            onItemClicked: (GeneralNewsTable) -> Unit,
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralListViewHolder =
        GeneralListViewHolder(
            RecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GeneralListViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item, context , onItemClicked , onShareButtonClicked)
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<GeneralNewsTable>() {
        override fun areItemsTheSame(
            oldItem: GeneralNewsTable,
            newItem: GeneralNewsTable
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GeneralNewsTable,
            newItem: GeneralNewsTable
        ): Boolean {
            return oldItem.imageName == newItem.imageName
        }
    }


}