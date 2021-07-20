package com.techlad.swvlchallenge.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Umair on 19,July,2021
 */

/* This class is copied from
* https://stackoverflow.com/a/51433794/4732930
* */
/**
 * Supporting only LinearLayoutManager for now.
 */
abstract class PaginationScrollListener
    : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val manager = recyclerView.layoutManager as LinearLayoutManager
            manager.let {
                val visibleItemCount = it.childCount
                val totalItemCount = it.itemCount
                val firstVisibleItemPosition = it.findFirstVisibleItemPosition()

                if (!isLoading() && !isLastPage()) {
                    if (visibleItemCount + firstVisibleItemPosition >=
                        totalItemCount && firstVisibleItemPosition >= 0
                    ) {

                        loadMoreItems()
                    }
                }
            }
        }
    }

    abstract fun loadMoreItems()
}