package com.techlad.swvlchallenge.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Umair on 18,July,2021
 */
open class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(data: T) {
        binding.root.tag = data
    }
}