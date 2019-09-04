package com.invictusbytes.gemaries.ui.client_profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.CratesAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import kotlinx.android.synthetic.main.activity_client_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class ClientProfileActivity : BaseActivity() {

    private var userId: Long? = null
    private var operation: String? = null

    private lateinit var adapter: CratesAdapter
    private lateinit var viewModel: ClientProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_profile)

        viewModel = getViewModel(ClientProfileViewModel::class.java)

        if (intent.hasExtra(USER_ID) && intent.hasExtra(OPERATION)) {
            userId = intent.getLongExtra(USER_ID, 0)
            operation = intent.getStringExtra(OPERATION)

            /*
            * init user get method
            * */
            viewModel.setUserId(userId!!)
        }

        setupToolbar()
        operations()
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = operation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun operations() {

        /**
         * show appropriate floating action
         * */

        if (operation.equals("Assign")) {
            fbAssignCrates.show()
        } else {
            fbUnAssignCrates.show()
        }

        /*
        * get user
        * */
        viewModel.getUser().observe(this, Observer {
            populateProfileCard(it)
        })

        setupRecyclerView()

        /**
         * data from db
         * */
        viewModel.getCrates().observe(this, Observer {
            adapter.setData(ArrayList(it))

            numberAssignedCrates?.text = String.format("%d crates", it.size)
        })

        /**
         * click fab
         * */
        fbAssignCrates.setOnClickListener {

        }

        fbUnAssignCrates.setOnClickListener {

        }
    }

    private fun populateProfileCard(client: UsersEntity) {
        tvUserName?.text = client.name
        tvUserPhone?.text = client.phone.toString()
        numberAssignedCrates?.text = String.format("%d crates", 0)
    }

    private fun setupRecyclerView() {
        adapter = CratesAdapter("assigned")
        profileRecycler.layoutManager = LinearLayoutManager(this)
        profileRecycler.adapter = adapter

        profileRecycler.itemAnimator = DefaultItemAnimator()
        profileRecycler.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
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
