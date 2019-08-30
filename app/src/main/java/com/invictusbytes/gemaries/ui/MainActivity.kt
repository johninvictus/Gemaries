package com.invictusbytes.gemaries.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.invictusbytes.gemaries.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setBg()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setBg() {
        Glide.with(this)
            .load(R.drawable.bakery)
            .into(bgBaker)
    }
}
