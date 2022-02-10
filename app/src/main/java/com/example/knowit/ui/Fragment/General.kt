package com.example.knowit.ui.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.viewmodels.NewsViewModel
import com.example.knowit.databinding.FragmentGeneralBinding
import com.example.knowit.ui.Activity.WebViewActivity
import com.example.knowit.ui.Fragment.rvAdapters.GeneralNewsAdapter
import com.example.knowit.ui.Fragment.rvDiffUtilAdapters.DiffGeneralAdapter

class General() : Fragment() {


    private val sharedViewModel: NewsViewModel by activityViewModels()
    private var _mBinding : FragmentGeneralBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var adapter : DiffGeneralAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentGeneralBinding.inflate(inflater, container, false)
        initViews()
        adapter = DiffGeneralAdapter(requireActivity(),this::onItemClicked,this::shareButtonClicked)
        return mBinding.root
    }

    private fun initViews() {
        mBinding.generalRecyclerView.apply {
            Log.d("rv", "initView: RV initialized properly")
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = sharedViewModel.generalNewsData
        mBinding.generalRecyclerView.adapter = adapter
        data.observe(requireActivity(),{
            adapter.submitList(it)
        })

        mBinding.generalRefreshButton.setOnClickListener {
            sharedViewModel.getGeneralNews()
        }
    }

    private fun onItemClicked(data: GeneralNewsTable?) {
        val intent = WebViewActivity.getStartIntent(requireActivity(), data?.article?.url.toString())
        startActivity(intent)
    }

    private fun shareButtonClicked(data : String){

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, data)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}