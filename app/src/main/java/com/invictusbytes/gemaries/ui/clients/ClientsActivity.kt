package com.invictusbytes.gemaries.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.ui.all_clients.AllClientsFragment
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_clients.*
import kotlinx.android.synthetic.main.toolbar.*

class ClientsActivity : DaggerAppCompatActivity() {


    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ClientsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)

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
        pagerAdapter.addFragment("All Clients", AllClientsFragment.newInstance())

        viewPagerClients.adapter = pagerAdapter
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
