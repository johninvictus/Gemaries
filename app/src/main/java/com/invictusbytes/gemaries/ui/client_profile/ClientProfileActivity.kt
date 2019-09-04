package com.invictusbytes.gemaries.ui.client_profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.commons.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class ClientProfileActivity : BaseActivity() {

    private var userId: Long? = null
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_profile)

        if (intent.hasExtra(USER_ID) && intent.hasExtra(OPERATION)) {
            userId = intent.getLongExtra(USER_ID, 0)
            operation = intent.getStringExtra(OPERATION)
        }

        setupToolbar()
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = operation
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

    companion object {
        const val USER_ID = "USER_ID"
        const val OPERATION = "OPERATION"

        fun startActivity(context: Context, userId: Long, operation: String) {
            val intent = Intent(context, ClientProfileActivity::class.java)
            intent.putExtra(USER_ID, userId)
            intent.putExtra(OPERATION, operation)

            context.startActivity(intent)
        }
    }
}
