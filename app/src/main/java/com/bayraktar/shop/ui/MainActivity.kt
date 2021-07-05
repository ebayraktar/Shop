package com.bayraktar.shop.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bayraktar.shop.App
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.HomeSliderAdapter
import com.bayraktar.shop.enums.ModelType
import com.bayraktar.shop.enums.ShopType
import com.bayraktar.shop.model.MobileResponse
import com.bayraktar.shop.model.base.BaseList
import com.bayraktar.shop.model.base.BaseType
import com.bayraktar.shop.network.ServiceBuilder
import com.bayraktar.shop.ui.fragment.CategoryFragment
import com.bayraktar.shop.ui.fragment.CollectionFragment
import com.bayraktar.shop.ui.fragment.ProductFragment
import com.bayraktar.shop.ui.fragment.ShopFragment
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var homeSliderAdapter: HomeSliderAdapter
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        homeSliderAdapter = HomeSliderAdapter(App.SCREEN_SIZE)

        //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.setSliderAdapter(homeSliderAdapter)

        srLayout.setOnRefreshListener {
            srLayout.isRefreshing = false
            discover()
        }

        discover()
    }

    private fun discover() {
        emptyState(true)
        compositeDisposable.add(
            ServiceBuilder.buildService().getDiscover()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    private fun emptyState(isVisible: Boolean) {
        val visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }

        progress_bar.visibility = visibility

//        srLayout.isRefreshing = isVisible

        //slider Shimmer
        shimmerSlider.visibility = visibility

        //Empty product
        shimmerProducts.visibility = visibility

        //Empty category
        shimmerCategories.visibility = visibility

        hideFragments(!isVisible)
    }

    private fun hideFragments(isVisible: Boolean) {
        val visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }

        imageSlider.visibility = visibility
        flProducts.visibility = visibility
        flCategories.visibility = visibility
        flCollections.visibility = visibility
        flEditorShops.visibility = visibility
        flNewShops.visibility = visibility
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
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
        emptyState(false)
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: List<MobileResponse>) {
        emptyState(false)

        val featured = getItems(response, ModelType.FEATURED)
        val products = getItems(response, ModelType.NEW_PRODUCTS)
        val categories = getItems(response, ModelType.CATEGORIES)
        val collections = getItems(response, ModelType.COLLECTIONS)
        val editorShops = getItems(response, ModelType.EDITOR_SHOPS)
        val newShops = getItems(response, ModelType.NEW_SHOPS)


        homeSliderAdapter.setItems(featured.list as List<BaseList>)


        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.flProducts,
            ProductFragment.newInstance(products.list, products.title)
        )
        fragmentTransaction.replace(
            R.id.flCategories,
            CategoryFragment.newInstance(categories.list, categories.title)
        )
        fragmentTransaction.replace(
            R.id.flCollections,
            CollectionFragment.newInstance(collections.list, collections.title)
        )
        fragmentTransaction.replace(
            R.id.flEditorShops,
            ShopFragment.newInstance(editorShops.list, editorShops.title, ShopType.EDITOR_SHOP)
        )
        fragmentTransaction.replace(
            R.id.flNewShops,
            ShopFragment.newInstance(newShops.list, newShops.title, ShopType.NEW_SHOP)
        )

        fragmentTransaction.commit()
    }

    private fun getItems(
        response: List<MobileResponse>,
        modelType: ModelType
    ): BaseType {

        val result = response.find {
            it.type != null && it.type == modelType.toString().toLowerCase(Locale.getDefault())
        }
        return when (modelType) {
            ModelType.FEATURED -> {
                BaseType(
                    list = result?.featured ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
            ModelType.NEW_PRODUCTS -> {
                BaseType(
                    list = result?.products ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
            ModelType.EDITOR_SHOPS -> {
                BaseType(
                    list = result?.shops ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
            ModelType.NEW_SHOPS -> {
                BaseType(
                    list = result?.shops ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
            ModelType.COLLECTIONS -> {
                BaseType(
                    list = result?.collections ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
            ModelType.CATEGORIES -> {
                BaseType(
                    list = result?.categories ?: ArrayList(),
                    title = result?.title ?: "UNKNOWN TITLE"
                )
            }
        }
    }
}