package com.shubham.techugo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import com.shubham.techugo.R
import com.shubham.techugo.adapters.ViewPagerAdapter
import com.shubham.techugo.model.Result
import com.shubham.techugo.model.Status.*
import com.shubham.techugo.network.NetworkModule
import com.shubham.techugo.network.RemoteDataSource
import com.shubham.techugo.viewmodel.MainActivityViewModel
import com.shubham.techugo.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.net.ConnectivityManager
import android.content.Context
import android.view.View

class MainActivity : AppCompatActivity() {
    companion object {
        val tabs = arrayOf("Offers", "Details")
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarColor()
        main_toolbar.text = getString(R.string.fitness_wellness)
        initViewModel()
        observeData()
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }
    }

    private fun observeData() {
        viewModel.getDefaultData().observe(this, Observer {
            when (it.status) {
                SUCCESS -> {
                    progress_bar.hide()
                    setViewPagerAndTabLayout(it.data?.result ?: return@Observer)
                }
                ERROR -> {
                    progress_bar.hide()
                    Toast.makeText(
                        this,
                        getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                LOADING -> {
                    progress_bar.show()
                }
            }
        })
    }

    private fun setViewPagerAndTabLayout(result: Result) {
        val adapter = ViewPagerAdapter(this, tabs.size, result)
        view_pager.adapter = adapter
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RemoteDataSource(NetworkModule.techugoAPI))
        ).get(MainActivityViewModel::class.java)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}
