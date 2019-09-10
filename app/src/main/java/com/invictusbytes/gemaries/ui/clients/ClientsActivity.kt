package com.invictusbytes.gemaries.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ViewPagerAdapter
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsFragment
import com.invictusbytes.gemaries.ui.dialogs.AddClientFragmentDialog
import com.invictusbytes.gemaries.ui.unassigned_clients.UnAssignedClientsFragment
import com.invictusbytes.gemaries.utils.AppExecutors
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_clients.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject

class ClientsActivity : BaseActivity() {


    lateinit var viewModel: ClientsViewModel
    private lateinit var dialog: AddClientFragmentDialog
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var appExecutors: AppExecutors

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
        operations()
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

        pagerAdapter.addFragment("Unassigned", UnAssignedClientsFragment.newInstance())
        pagerAdapter.addFragment("Assigned", AssignedClientsFragment.newInstance())

        viewPagerClients.adapter = pagerAdapter
    }

    private fun operations() {
        fbAddClient.setOnClickListener {
            dialog.show(supportFragmentManager, "Client")
        }
        dialog = AddClientFragmentDialog.newInstance()
    }

    private fun listenDialog() {
        val event =
            dialog.clientDialogEvent.subscribe {

                appExecutors.diskIO().execute {
                    val u = viewModel.getUserByPhone(it.second)

                    if (u == null) {
                        viewModel.addClient(
                            UsersEntity(
                                name = it.first,
                                phone = it.second,
                                created = Date()
                            )
                        )

                        appExecutors.mainThread().execute {
                            toast("client added successfully")
                        }

                    } else {
                        appExecutors.mainThread().execute {
                            toast("Sorry, the client is using an existing phone number.")
                        }
                    }

                }
            }

        compositeDisposable.add(event)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    override fun onResume() {
        super.onResume()
        listenDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
