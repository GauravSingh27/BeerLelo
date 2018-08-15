package com.gaurav.beerlelo.ui.activity.cart

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.repository.BeerRepository
import com.gaurav.beerlelo.ui.bottomsheet.sort.NAME
import com.gaurav.beerlelo.ui.model.Beer
import com.gaurav.beerlelo.ui.model.BeerStyle
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    private val beerCartListAdapter: BeerCartListAdapter by lazy { BeerCartListAdapter() }
    private val beerRepository: BeerRepository by lazy { BeerRepository() }
    private var beerStyles: List<BeerStyle>? = null
    private var beerStyleArray: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setSupportActionBar(tb_cart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_cart_beer_list.layoutManager = LinearLayoutManager(this)
        rv_cart_beer_list.adapter = beerCartListAdapter

        beerRepository.fetchBeerStyles()
                .observe(this,
                        Observer {
                            beerStyles = it
                            beerStyleArray = createBeerStyleList(beerStyles, false)
                            displayBeerList(beerStyles = beerStyleArray)
                        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> onBackPressed()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayBeerList(search: String = "", sortBy: Int = NAME, beerStyles: List<String>?) {
        beerRepository
                .searchBeer(search, sortBy, beerStyles)
                .observe(this, beerObserver)
    }

    private val beerObserver = Observer<List<Beer>> { beerList ->
        beerCartListAdapter.beerList = beerList
        beerCartListAdapter.notifyDataSetChanged()
    }

    private fun createBeerStyleList(beerStyles: List<BeerStyle>?, selectedState: Boolean) =
            beerStyles?.filter { it.isSelected == selectedState }?.map { it.style }

}
