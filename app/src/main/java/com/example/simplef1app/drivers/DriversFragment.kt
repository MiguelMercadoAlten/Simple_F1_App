package com.example.simplef1app.drivers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplef1app.api.F1Api
import com.example.simplef1app.api.Repository
import com.example.simplef1app.databinding.FragmentDriversBinding


class DriversFragment : Fragment() {
    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: DriversViewModelFactory
    private lateinit var viewModel: DriversViewModel
    private lateinit var adapter: DriversAdapter

    private var isScrolling = true
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var pastVisiblesItems = 0
    private var isLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = F1Api()
        val repository = Repository(api)
        factory = DriversViewModelFactory(repository, requireContext())
        viewModel = ViewModelProvider(this, factory).get(DriversViewModel::class.java)
        viewModel.getDrivers(API_LIMIT)
        adapter = DriversAdapter(findNavController())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDriversBinding.inflate(inflater, container, false).also {
            it.recyclerViewDrivers.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerViewDrivers.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        visibleItemCount = recyclerView.layoutManager!!.childCount
                        totalItemCount = recyclerView.layoutManager!!.itemCount
                        pastVisiblesItems =
                            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (isScrolling && visibleItemCount + pastVisiblesItems >= totalItemCount && isLoadMore) {
                            isScrolling = false
                            viewModel.getDriversWithOffset(API_LIMIT, totalItemCount)
                        }
                    }
                }
            })
            it.recyclerViewDrivers.adapter = adapter

            it.driversFilter.addTextChangedListener {driversNameFilterText ->
                viewModel.getDriversByName(driversNameFilterText.toString())
            }

        }

        viewModel.drivers.observe(viewLifecycleOwner) { drivers ->
            adapter.submitList(drivers)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val API_LIMIT = 50
    }
}