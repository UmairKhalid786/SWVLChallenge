package com.techlad.swvlchallenge.ui.detail

import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import com.techlad.swvlchallenge.data.responses.FlickerResponse.Photos.Photo
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import com.techlad.swvlchallenge.databinding.FragmentMovieDetailBinding
import com.techlad.swvlchallenge.ui.base.BaseFragment
import com.techlad.swvlchallenge.utils.showMessage
import com.techlad.swvlchallenge.utils.visibility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    val viewModel: PhotosViewModel by viewModels()

    lateinit var itemDetailTextView: TextView
    var item: MoviesResponse.Data.Movie? = null


    companion object {
        const val ARG_ITEM = "item"
    }

    override fun init() {
        getArgumentsStuff()
        setMovieDetails()
        setupAdapters()
        setUpViewModel()
        nodata(false)
    }


    private fun setupAdapters() {
        binding.adapter = PhotosAdapter()
        binding.searchResultRv?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun setMovieDetails() {
        binding.detailHeader.propsLl.movie = item
    }

    private fun getArgumentsStuff() {
        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                item = arguments?.getParcelable(ARG_ITEM)
                item?.let {
                    binding.movie = item
                }
            }
        }
    }

    override fun listeners() {
        binding.adapter?.onClickListener = onClickListener
    }

    private fun setUpViewModel() {
        item?.title?.let { viewModel.getPhotos(it) }
        viewModel.content.observe(viewLifecycleOwner, onDataReceiveListener)
    }

    private val onDataReceiveListener: Observer<Resource<FlickerResponse>> = Observer { it ->

        binding.searchProgBar?.visibility(false)

        when (it?.status) {
            Resource.Status.SUCCESS -> {
                nodata(true)
                it.data?.photos?.photo?.let {
                    binding.adapter?.submitList(it)
                    nodata(it.isEmpty())
                }
            }

            Resource.Status.ERROR -> {
                it.message?.let { it1 -> activity?.showMessage(it1) }
                nodata(true)
            }

            Resource.Status.LOADING -> {
                binding.searchProgBar?.visibility(true)
            }
        }
    }

    private fun nodata(nodata: Boolean) {
        binding.noRecordLl?.root?.visibility(nodata)
        binding.searchResultRv?.visibility(!nodata)
    }

    private val onClickListener = View.OnClickListener {
        it?.tag?.let {
            val item = it as Photo
            displayMessage("This photo is owned by :" + item.owner, null)
        }
    }
}