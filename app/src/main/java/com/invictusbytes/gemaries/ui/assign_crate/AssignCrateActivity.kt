package com.invictusbytes.gemaries.ui.assign_crate

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ClientsAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.ui.client_profile.ClientProfileActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_assign_crate.*
import kotlinx.android.synthetic.main.toolbar.*

class AssignCrateActivity : BaseActivity() {

    lateinit var viewModel: AssignCrateViewModel
    private lateinit var adapter: ClientsAdapter
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_crate)

        viewModel = getViewModel(AssignCrateViewModel::class.java)
        setupToolbar()
        operations()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "All clients"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.assign_crate_menu, menu);
        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query.isNullOrEmpty()) {
                    adapter.filterData("")
                } else {
                    adapter.filterData(query)
                }

                if (searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchItem?.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.filterData("")
                } else {
                    adapter.filterData(newText)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun operations() {
        setupAdapter()

        /**
         * get data from viewModel
         * */
        viewModel.getAllClients()
            .observe(this, Observer {
                adapter.setData(ArrayList(it))
                if (it.isEmpty()) {
                    assignCrate.visibility = View.VISIBLE
                } else {
                    assignCrate.visibility = View.GONE
                }
            })
    }

    private fun setupAdapter() {
        adapter = ClientsAdapter("assigned")
        assignCrateRecycler.layoutManager = LinearLayoutManager(this)
        assignCrateRecycler.adapter = adapter

        assignCrateRecycler.itemAnimator = DefaultItemAnimator()
        assignCrateRecycler.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()

        /*
        * adapter clicks
        * */
        adapterClickEvents()
    }

    private fun adapterClickEvents() {
        val singleClick = adapter.clientItemClick.subscribe {
            startProfileActivity(it)
        }
        disposable.add(singleClick)
    }

    private fun startProfileActivity(client: UsersEntity) {
        ClientProfileActivity.startActivity(this, client.id!!, "Assign")
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AssignCrateActivity::class.java))
        }
    }
}
