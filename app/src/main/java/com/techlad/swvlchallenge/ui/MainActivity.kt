package com.techlad.swvlchallenge.ui

import com.techlad.swvlchallenge.R
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.techlad.swvlchallenge.databinding.ActivityMainBinding
import com.techlad.swvlchallenge.ui.base.BaseActivity
import com.techlad.swvlchallenge.utils.visibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onPostInit() {
        setListeners()
    }

    private fun setListeners() {
        handleNavigationStuff()
        setOnToolbarAction()
    }

    private fun setOnToolbarAction() = binding.toolbarLl.backBtn.setOnClickListener { controller().popBackStack() }

//    private fun controller() = (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment)

    private fun controller() = findNavController(R.id.nav_host)

    private fun handleNavigationStuff() = controller().addOnDestinationChangedListener(navigationHandler)

    private fun toolbar(isVisible: Boolean) = binding.toolbarLl.root.visibility(isVisible)

    private val navigationHandler = NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_movie_detail -> {
                    toolbar(true)
                }
                else -> {
                    toolbar(false)
                }
            }
        }

}