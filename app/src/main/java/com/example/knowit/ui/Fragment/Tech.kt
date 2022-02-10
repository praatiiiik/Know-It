package com.example.knowit.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowit.R
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable
import com.example.knowit.data.viewmodels.NewsViewModel
import com.example.knowit.databinding.FragmentGeneralBinding
import com.example.knowit.databinding.FragmentTechBinding
import com.example.knowit.ui.Activity.WebViewActivity
import com.example.knowit.ui.Fragment.rvAdapters.GeneralNewsAdapter
import com.example.knowit.ui.Fragment.rvAdapters.TechNewsAdapter
import com.example.knowit.ui.Fragment.rvDiffUtilAdapters.DiffTechAdapter
import com.example.knowit.util.Resource


class Tech : Fragment() {

    private val sharedViewModel: NewsViewModel by activityViewModels()
    private var _mBinding : FragmentTechBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var adapter : DiffTechAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentTechBinding.inflate(inflater, container, false)
        initViews()
        adapter = DiffTechAdapter(requireActivity() , this::onItemClicked , this::shareButtonClicked)
        return mBinding.root
    }

    private fun initViews() {
        mBinding.techRecyclerView.apply {
            Log.d("rv", "initView: RV initialized properly")
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = sharedViewModel.techNewsData
        mBinding.techRecyclerView.adapter = adapter
        data.observe(requireActivity(),{
            adapter.submitList(it)
        })

        mBinding.techRefreshButton.setOnClickListener {
            sharedViewModel.getTechNews()
        }
    }

    private fun onItemClicked(data: TechNewsTable?) {
        val intent = WebViewActivity.getStartIntent(requireActivity(), data?.article?.url.toString())
        startActivity(intent)
        //   Toast.makeText(requireContext(),data?.id.toString(),Toast.LENGTH_SHORT).show()
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