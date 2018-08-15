package com.gaurav.beerlelo.ui.activity.home

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.repository.BeerRepository
import com.gaurav.beerlelo.ui.activity.cart.CartActivity
import com.gaurav.beerlelo.ui.bottomsheet.filter.FilterByBottomSheet
import com.gaurav.beerlelo.ui.bottomsheet.quantity.QuantitySheet
import com.gaurav.beerlelo.ui.bottomsheet.sort.ALCOHOL_CONTENT_ASC
import com.gaurav.beerlelo.ui.bottomsheet.sort.ALCOHOL_CONTENT_DESC
import com.gaurav.beerlelo.ui.bottomsheet.sort.NAME
import com.gaurav.beerlelo.ui.bottomsheet.sort.SortByBottomSheet
import com.gaurav.beerlelo.ui.model.Beer
import com.gaurav.beerlelo.ui.model.BeerStyle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity :
        AppCompatActivity(),
        SortByBottomSheet.SortListener,
        FilterByBottomSheet.FilterListener,
        BeerListAdapter.BeerListAdapterEventListener, QuantitySheet.QuantitySheetListener {

    private val beerListAdapter: BeerListAdapter by lazy { BeerListAdapter(this) }
    private val beerRepository: BeerRepository by lazy { BeerRepository() }
    private var sortOrder = NAME
    private var searchString: String = ""
    private var beerStyles: List<BeerStyle>? = null
    private var beerStyleArray: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(tb_home)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_toolbar_beer)

        rv_beer_list.layoutManager = LinearLayoutManager(this)
        rv_beer_list.adapter = beerListAdapter

        tv_home_sort.setOnClickListener { showSortByBottomSheet() }
        tv_home_filter.setOnClickListener { showFilterBottomSheet() }

        beerRepository.fetchBeerStyles()
                .observe(this,
                        Observer {
                            beerStyles = it
                            beerStyleArray = createBeerStyleList(beerStyles, false)
                            displayBeerList(beerStyles = beerStyleArray)
                        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.search_view_hint)

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(searchQueryListener)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_cart -> startActivity(Intent(this, CartActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }

    override fun sortBy(sortBy: Int) {
        sortOrder = sortBy
        displayBeerList(searchString, sortOrder, beerStyleArray)

        setSortIndication(sortBy)
    }

    override fun addToCart(beerId: Int?, quantity: Int) {
        val quantitySheet = QuantitySheet()
        val bundle = Bundle()
        bundle.putInt("beerId", beerId!!)
        bundle.putInt("quantity", quantity)
        quantitySheet.arguments = bundle
        quantitySheet.show(supportFragmentManager, "quantitySheet")
    }

    private fun setSortIndication(sortBy: Int) {
        when (sortBy) {
            NAME -> {
                tv_home_sort.setText(R.string.sort)
                tv_home_sort.setTextColor(colorForState(false))
            }
            ALCOHOL_CONTENT_ASC -> {
                tv_home_sort.setText(R.string.arrow_down)
                tv_home_sort.setTextColor(colorForState(true))
            }
            ALCOHOL_CONTENT_DESC -> {
                tv_home_sort.setText(R.string.arrow_up)
                tv_home_sort.setTextColor(colorForState(true))
            }

        }
    }

    override fun filterBy(beerStyles: List<BeerStyle>?, selectedState: Boolean) {

        tv_home_filter.setTextColor(colorForState(selectedState))

        beerStyleArray = createBeerStyleList(beerStyles, selectedState)

        displayBeerList(searchString, sortOrder, beerStyleArray)
    }

    override fun quantity(beerId: Int?, quantity: Int) {
        beerRepository.updateCount(beerId, quantity)
    }

    private fun displayBeerList(search: String = "", sortBy: Int = NAME, beerStyles: List<String>?) {
        beerRepository
                .searchBeer(search, sortBy, beerStyles)
                .observe(this, beerObserver)
    }

    private fun colorForState(selectedState: Boolean): Int {

        val colorResource: Int = if (selectedState) R.color.colorHighlight else R.color.colorPrimary
        return ContextCompat.getColor(this, colorResource)
    }


    private fun showSortByBottomSheet() {

        val sortBottomSheet = SortByBottomSheet()
        val bundle = Bundle()
        bundle.putInt("selectedSortOrder", sortOrder)
        sortBottomSheet.arguments = bundle
        sortBottomSheet.show(supportFragmentManager, "sortBottomSheet")
    }

    private fun showFilterBottomSheet() {

        val filterByBottomSheet = FilterByBottomSheet()
        filterByBottomSheet.beerStyles = beerStyles
        filterByBottomSheet.show(supportFragmentManager, "filterByBottomSheet")
    }


    private val searchQueryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {

            return false
        }

        override fun onQueryTextChange(query: String): Boolean {

            searchString = query
            displayBeerList(query, sortOrder, beerStyleArray)
            return true
        }

    }

    private val beerObserver = Observer<List<Beer>> { beerList ->
        beerListAdapter.beerList = beerList
        beerListAdapter.notifyDataSetChanged()
    }

    private fun createBeerStyleList(beerStyles: List<BeerStyle>?, selectedState: Boolean) =
            beerStyles?.filter { it.isSelected == selectedState }?.map { it.style }

}
