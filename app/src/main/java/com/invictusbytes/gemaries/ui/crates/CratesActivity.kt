package com.invictusbytes.gemaries.ui.crates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.ui.assigned.AssignedFragment
import com.invictusbytes.gemaries.ui.unassigned.UnassignedFragment
import kotlinx.android.synthetic.main.activity_crates.*
import kotlinx.android.synthetic.main.toolbar.*

class CratesActivity : BaseActivity() {

    lateinit var viewModel: CratesViewModel

    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, CratesActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crates)

        viewModel = getViewModel(CratesViewModel::class.java)

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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

}
