package com.invictusbytes.gemaries.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.ui.assign_crate.AssignCrateActivity
import com.invictusbytes.gemaries.ui.clients.ClientsActivity
import com.invictusbytes.gemaries.ui.crates.CratesActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setBg()
        operations()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setBg() {
        Glide.with(this)
            .load(R.drawable.bakery)
            .into(bgBaker)
    }

    private fun operations() {
        btnCrates.setOnClickListener {
            CratesActivity.startActivity(this)
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }

        btnClients.setOnClickListener {
            ClientsActivity.startActivity(this)
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }

        btnAssignCrate.setOnClickListener {
            AssignCrateActivity.startActivity(this)
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }

    }
}
