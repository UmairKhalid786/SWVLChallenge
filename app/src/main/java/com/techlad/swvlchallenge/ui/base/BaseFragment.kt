package com.techlad.swvlchallenge.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Umair on 18,July,2021
 */

abstract class BaseFragment<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) :
    Fragment() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listeners()
    }

    private fun showDialog(
        title: String,
        message: String,
        onOkPress: ((dailog: DialogInterface) -> Unit)?
    ) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton("OK", { dialog, which ->
            onOkPress?.invoke(dialog)
        })
        builder.show()
    }


    abstract fun init()
    abstract fun listeners()

    fun displayMessage(message: String, onRetry: (() -> Unit)?) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setAction(if (onRetry == null) "OK" else "Retry") {
                onRetry?.invoke()
            }.show()
    }
}