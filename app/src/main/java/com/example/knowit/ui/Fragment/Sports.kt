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
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.viewmodels.NewsViewModel
import com.example.knowit.databinding.FragmentSportsBinding
import com.example.knowit.ui.Activity.WebViewActivity
import com.example.knowit.ui.Fragment.rvDiffUtilAdapters.DiffSportsAdapter


class
Sports : Fragment() {

    private val sharedViewModel : NewsViewModel by activityViewModels()
    private var _mBinding : FragmentSportsBinding? = null
    private val mBinding get()  = _mBinding!!
    private lateinit var adapter : DiffSportsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSportsBinding.inflate(inflater, container, false)
        initViews()
        adapter = DiffSportsAdapter(requireActivity(),this::onItemClicked ,this::shareButtonClicked)
        return mBinding.root
    }

    private fun initViews() {
        mBinding.sportsRecyclerView.apply {
            Log.d("rv", "initView: RV initialized properly")
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = sharedViewModel.sportsData
        mBinding.sportsRecyclerView.adapter = adapter
        data.observe(requireActivity(),{
            adapter.submitList(it)
        })

        mBinding.sportsRefreshButton.setOnClickListener {
            sharedViewModel.getSportsNews()
        }
    }

    private fun onItemClicked(data: SportsNewsTable?) {
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