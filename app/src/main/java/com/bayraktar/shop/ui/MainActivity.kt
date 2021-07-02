package com.bayraktar.shop.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.HomeSliderAdapter
import com.bayraktar.shop.interfaces.ISliderOnClickListener
import com.bayraktar.shop.model.MobileResponse
import com.bayraktar.shop.model.SliderItem
import com.bayraktar.shop.network.ServiceBuilder
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), ISliderOnClickListener {
    lateinit var searchView: SearchView
    var homeSliderAdapter: HomeSliderAdapter = HomeSliderAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

//todo add title and subtitle
        imageSlider.setIndicatorEnabled(true)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!

        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
//        imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        imageSlider.indicatorSelectedColor = R.attr.colorPrimary

        imageSlider.indicatorUnselectedColor = R.attr.colorPrimaryDark
        imageSlider.scrollTimeInSec = 4 //set scroll delay in seconds :

        imageSlider.startAutoCycle()

        imageSlider.setSliderAdapter(homeSliderAdapter)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getDiscover()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    init {
        homeSliderAdapter.setOnClickListener(this@MainActivity)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (Intent.ACTION_SEARCH == intent!!.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchView.setQuery(query.toString(), false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_toolbar, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView
        searchView.isQueryRefinementEnabled = true
        searchView.setIconifiedByDefault(true)


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: List<MobileResponse>) {
        Log.d("TAG", "onResponse: ${response.size}")

        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        var sliderItems =
            response.find { _response -> _response.featured.isNotEmpty() }?.featured?.map { _item ->
                SliderItem(
                    _item.id,
                    _item.title,
                    _item.cover.url
                )
            }?.toMutableList()
        if (sliderItems == null)
            sliderItems = ArrayList()
        homeSliderAdapter.renewItems(sliderItems)

    }

    override fun onClick(id: String) {
        Log.d("TAG", "OnClick: $id")
    }

}