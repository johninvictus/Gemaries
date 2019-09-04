package com.invictusbytes.gemaries.ui.assign_crate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ClientsAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import kotlinx.android.synthetic.main.activity_assign_crate.*
import kotlinx.android.synthetic.main.toolbar.*

class AssignCrateActivity : BaseActivity() {

    lateinit var viewModel: AssignCrateViewModel
    private lateinit var adapter: ClientsAdapter

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

    private fun operations() {
        setupAdapter()

        /**
         * get data from viewModel
         * */
        viewModel.getAllClients()
            .observe(this, Observer {
                adapter.setData(ArrayList(it))
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
