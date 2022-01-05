package com.example.knowit.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.viewmodels.NewsViewModel
import com.example.knowit.databinding.FragmentBusinessBinding
import com.example.knowit.ui.Activity.WebViewActivity
import com.example.knowit.ui.Fragment.rvDiffUtilAdapters.DiffBusinessAdapter


class Business : Fragment() {

    private val sharedViewModel: NewsViewModel by activityViewModels()
    private var _mBinding : FragmentBusinessBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var adapter : DiffBusinessAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentBusinessBinding.inflate(inflater, container, false)
        initViews()
        adapter = DiffBusinessAdapter(requireActivity(),this::onItemClicked)
        return mBinding.root
    }

    private fun initViews() {
        mBinding.businessRecyclerView.apply {
            Log.d("rv", "initView: RV initialized properly")
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = sharedViewModel.businessData
        mBinding.businessRecyclerView.adapter = adapter
        data.observe(requireActivity(),{
            adapter.submitList(it)
        })

        mBinding.businesRefreshButton.setOnClickListener {
            sharedViewModel.getBusinessNews()
        }
    }

    private fun onItemClicked(data: BusinessNewsTable?) {
        val intent = WebViewActivity.getStartIntent(requireActivity(), data?.article?.url.toString())
        startActivity(intent)
        //   Toast.makeText(requireContext(),data?.id.toString(),Toast.LENGTH_SHORT).show()
    }
}