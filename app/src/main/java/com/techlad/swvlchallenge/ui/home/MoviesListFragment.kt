package com.techlad.swvlchallenge.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.techlad.swvlchallenge.R
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.MoviesResponse.Data.Movie
import com.techlad.swvlchallenge.databinding.FragmentMoviesListBinding
import com.techlad.swvlchallenge.ui.base.BaseFragment
import com.techlad.swvlchallenge.ui.detail.MovieDetailFragment
import com.techlad.swvlchallenge.utils.Constants.PAGE_SIZE
import com.techlad.swvlchallenge.utils.PaginationScrollListener
import com.techlad.swvlchallenge.utils.visibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : BaseFragment<FragmentMoviesListBinding>(FragmentMoviesListBinding::inflate) {

    //This variable tel everyone that someone searching rn
    private var isSearchViewEnable: Boolean = false
    private val viewModel: MoviesViewModel by viewModels()

    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun init() {
        getAll()
        noData(false)
    }

    private fun getAll() {
        viewModel.startDataLoad()
    }

    private fun getAllWithNextPage() {
        if (!isSearchViewEnable)
            viewModel.nextPage()
    }

    override fun listeners() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, observer)
        setupRecyclerView(onClickListener)
        binding.simpleSearchView?.setOnQueryTextListener(onSearchChange)
        binding.simpleSearchView?.setOnQueryTextFocusChangeListener(onSearchFocusChangeChange)
        binding.itemList.addOnScrollListener(recyclerViewLoadingCallBack)
    }

    private fun setupRecyclerView(onClickListener: View.OnClickListener) {
        binding.adapter = MoviesAdapter()
        binding.adapter?.onClickListener = onClickListener
    }

    private val onClickListener = View.OnClickListener { itemView ->
        val item = itemView.tag as Movie
        val bundle = Bundle()
        bundle.putParcelable(
            MovieDetailFragment.ARG_ITEM,
            item
        )
        val itemDetailFragmentContainer: View? = view?.findViewById(R.id.item_detail_nav_container)

        if (itemDetailFragmentContainer != null) {
            itemDetailFragmentContainer.findNavController()
                .navigate(R.id.fragment_movie_detail, bundle)
        } else {
            itemView.findNavController().navigate(R.id.show_item_detail, bundle)
        }
    }

    private val observer = Observer<Resource<List<Movie>>> {
        when (it.status) {
            Resource.Status.LOADING -> {
                loading(true)
            }
            Resource.Status.SUCCESS -> {
                //Hide progress
                it.data?.let { it1 ->
                    //It means user reached last page
                    if (it1.size < PAGE_SIZE) {
                        isLastPage = true
                    }
                    performAsyncOperation(it1)
                }

                noData(!(it.data != null && it.data.isNotEmpty()))
                loading(false)
                isLoading = false
            }
            Resource.Status.ERROR -> {
                //Show error
                isLoading = false
                loading(false)
            }
        }
    }

    private fun performAsyncOperation(list: List<Movie>) {
        if (isSearchViewEnable) {

            /**
             * Always use sequences for larger lists its faster than normal collection
             * https://blog.kotlin-academy.com/effective-kotlin-use-sequence-for-bigger-collections-with-more-than-one-processing-step-649a15bb4bf
             */

            binding.adapter?.submitList(list.asSequence().groupBy { it.year }
                .flatMap { it.value.sortedBy { it1 -> it1.rating }.take(5) })
        } else {
            binding.adapter?.submitList(list)
        }
    }

    private val recyclerViewLoadingCallBack = object : PaginationScrollListener() {
        override fun isLastPage(): Boolean {
            return isLastPage
        }

        override fun isLoading(): Boolean {
            return isLoading
        }

        override fun loadMoreItems() {
            isLoading = true
            getAllWithNextPage()
        }
    }

    private val onSearchChange = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 700)
            return false
        }
    }

    private val onSearchFocusChangeChange = View.OnFocusChangeListener { _, hasFocus ->
        isSearchViewEnable = hasFocus
    }

    private val searchRunnable = Runnable {
        binding.simpleSearchView?.query?.let {
            if (it.isBlank()) {
                viewModel.nextPage()
            } else
                viewModel.search(it.toString())
        }
    }

    private fun loading(start: Boolean) {
        binding.progressBr?.visibility(start)
    }

    private fun noData(available: Boolean) {
        binding.noRecordLl?.root?.visibility(available)
        binding.itemList.visibility(!available)
    }
}