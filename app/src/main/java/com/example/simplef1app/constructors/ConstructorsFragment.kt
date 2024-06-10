package com.example.simplef1app.constructors

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
import com.example.simplef1app.databinding.FragmentConstructorsBinding

class ConstructorsFragment : Fragment() {
    private var _binding: FragmentConstructorsBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ConstructorsViewModelFactory
    private lateinit var viewModel: ConstructorsViewModel
    private lateinit var adapter: ConstructorsAdapter

    private var isScrolling = true
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var pastVisiblesItems = 0
    private var isLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = F1Api()
        val repository = Repository(api)
        factory = ConstructorsViewModelFactory(repository, requireContext())
        viewModel = ViewModelProvider(this, factory).get(ConstructorsViewModel::class.java)
        viewModel.getConstructors(API_LIMIT)
        adapter = ConstructorsAdapter(findNavController())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstructorsBinding.inflate(inflater, container, false).also {
            it.recyclerViewConstructors.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerViewConstructors.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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
                            viewModel.getConstructorsWithOffset(API_LIMIT, totalItemCount)
                        }
                    }
                }
            })
            it.recyclerViewConstructors.adapter = adapter

            it.constructorsFilter.addTextChangedListener {constructorsNameFilterText ->
                viewModel.getConstructorsByName(constructorsNameFilterText.toString())
            }
        }

        viewModel.constructors.observe(viewLifecycleOwner) { constructors ->
            adapter.submitList(constructors)
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