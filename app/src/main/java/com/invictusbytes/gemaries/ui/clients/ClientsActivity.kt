package com.invictusbytes.gemaries.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsFragment
import com.invictusbytes.gemaries.ui.unassigned_clients.UnAssignedClientsFragment
import kotlinx.android.synthetic.main.activity_clients.*
import kotlinx.android.synthetic.main.toolbar.*

class ClientsActivity : BaseActivity() {


    lateinit var viewModel: ClientsViewModel


    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ClientsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)

        viewModel = getViewModel(ClientsViewModel::class.java)

        setupToolbar()
        setupTabLayout()
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Clients"
    }

    private fun setupTabLayout() {
        tabLayoutClients.setupWithViewPager(viewPagerClients)
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment("Assigned", AssignedClientsFragment.newInstance())
        pagerAdapter.addFragment("Unassigned", UnAssignedClientsFragment.newInstance())

        viewPagerClients.adapter = pagerAdapter
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
