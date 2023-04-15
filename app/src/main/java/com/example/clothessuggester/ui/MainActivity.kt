package com.example.clothessuggester.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clothessuggester.BuildConfig
import com.example.clothessuggester.data.local.PrefsUtil.SHARED_PREFS_NAME
import com.example.clothessuggester.data.network.TomorrowApiClient
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.projects.whattowear.model.Interval

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()
        initView()
    }

    private fun initView() {
        mainAdapter = MainAdapter(::changeImageClothe)
        binding.recyclerViewIntervals.adapter = mainAdapter
    }

    private fun initPresenter() {
        val sharedPref: SharedPreferences =
            getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)
        presenter = MainPresenter(TomorrowApiClient(BuildConfig.apikey), sharedPref)

        presenter.attachView(this)
        presenter.initScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


    override fun setupIntervals(weatherResponse: List<Interval>) {
        runOnUiThread {
            Log.d("TAG", "setupIntervals: $weatherResponse")
            mainAdapter.submitList(weatherResponse)
            changeImageClothe(weatherResponse.first().clothesImageId)
        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeImageClothe(clothesImageId: Int) {
        binding.imageClothe.setImageResource(clothesImageId)
    }
}