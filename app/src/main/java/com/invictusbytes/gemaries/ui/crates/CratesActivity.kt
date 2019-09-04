package com.invictusbytes.gemaries.ui.crates

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.ui.assigned.AssignedFragment
import com.invictusbytes.gemaries.ui.scanner.ScannerActivity
import com.invictusbytes.gemaries.ui.unassigned.UnassignedFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_crates.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast


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
        operations()
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


    private fun operations() {
        fbAddCrate.setOnClickListener {
            startCameraActivity()
        }
    }

    private fun startCameraActivity() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    ScannerActivity.startActivity(this@CratesActivity, "Crate", 0L)
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    toast("You need to accept the permission")
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                }
            }).check()
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

}
