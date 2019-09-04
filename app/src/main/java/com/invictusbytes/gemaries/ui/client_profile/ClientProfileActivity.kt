package com.invictusbytes.gemaries.ui.client_profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.CratesAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.ui.scanner.ScannerActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_client_profile.*
import kotlinx.android.synthetic.main.fragment_unassigned_clients.*
import kotlinx.android.synthetic.main.toolbar.*

class ClientProfileActivity : BaseActivity() {

    private var userId: Long? = null
    private var operation: String? = null

    private lateinit var adapter: CratesAdapter
    private lateinit var viewModel: ClientProfileViewModel
    val composable = CompositeDisposable()

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

    override fun onResume() {
        super.onResume()
        adapterClicks()
    }

    override fun onPause() {
        super.onPause()
        composable.clear()
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
            startScanner("Assign")
        }

        fbUnAssignCrates.setOnClickListener {
            startScanner("UnAssign")
        }
    }

    private fun adapterClicks() {
        /*
       * listen to adapter clicks
       * */
        val lc =
            adapter.crateItemLongClick.subscribe {
                Snackbar.make(
                    parentProfile,
                    "Are you sure you want to delete?",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("DELETE") { v ->
                        viewModel.deleteCrate(it)
                    }.show()
            }

        composable.add(lc)
    }

    private fun startScanner(state: String) {
        ScannerActivity.startActivity(this, state, userId!!)
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
    }

    private fun populateProfileCard(client: UsersEntity) {
        tvUserName?.text = client.name
        tvUserPhone?.text = client.phone.toString()
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
