package com.invictusbytes.gemaries.ui.crates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.ui.assigned.AssignedFragment
import com.invictusbytes.gemaries.ui.unassigned.UnassignedFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_crates.*
import kotlinx.android.synthetic.main.toolbar.*

class CratesActivity : DaggerAppCompatActivity() {

    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, CratesActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crates)

        setupToolbar()
        setupTabLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Crates"
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment("Assigned", AssignedFragment.newInstance())
        pagerAdapter.addFragment("Unassigned", UnassignedFragment.newInstance())

        viewPager.adapter = pagerAdapter
    }
}
