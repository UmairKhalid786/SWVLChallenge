package com.techlad.swvlchallenge.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Umair on 18,July,2021
 */
abstract class BaseAdapter<T>(var onClickListener: View.OnClickListener? = null) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val diffCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return areTheseItemsSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<T>) = differ.submitList(list)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T> {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater, viewType, parent, false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int
    ) {

        val obj = differ.currentList[position]
        holder.bind(obj)
        holder.binding.root.setOnClickListener(onClickListener)
        onBind(holder.binding, obj, adapterPosition = position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getItemByIndex(index: Int): T? {
        return differ.currentList.getOrNull(index)
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int
    protected abstract fun areTheseItemsSame(oldItem: T, newItem: T): Boolean
    protected abstract fun onBind(binding: ViewDataBinding, item: T, adapterPosition: Int)

}