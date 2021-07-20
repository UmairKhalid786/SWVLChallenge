package com.techlad.swvlchallenge.ui.detail

import androidx.databinding.ViewDataBinding
import com.techlad.swvlchallenge.R
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import com.techlad.swvlchallenge.databinding.ItemListPhotoBinding
import com.techlad.swvlchallenge.ui.base.BaseAdapter


/**
 * Created by Umair on 19,July,2021
 */
class PhotosAdapter : BaseAdapter<FlickerResponse.Photos.Photo>() {

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_list_photo

    override fun areTheseItemsSame(
        oldItem: FlickerResponse.Photos.Photo,
        newItem: FlickerResponse.Photos.Photo
    ) = oldItem.id == newItem.id

    override fun onBind(
        binding: ViewDataBinding,
        item: FlickerResponse.Photos.Photo,
        adapterPosition: Int
    ) {
        if (binding is ItemListPhotoBinding) {
            binding.photo = item
        }
    }
}