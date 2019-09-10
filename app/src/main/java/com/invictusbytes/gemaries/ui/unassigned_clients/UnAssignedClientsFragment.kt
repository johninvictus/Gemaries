package com.invictusbytes.gemaries.ui.unassigned_clients


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ClientsAdapter
import com.invictusbytes.gemaries.commons.BaseFragment
import com.invictusbytes.gemaries.utils.AppExecutors
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_unassigned_clients.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UnAssignedClientsFragment : BaseFragment() {

    private lateinit var adapter: ClientsAdapter
    private lateinit var viewModel: UnAssignedClientsViewModel
    val composable = CompositeDisposable()

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unassigned_clients, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(UnAssignedClientsViewModel::class.java)

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

    private fun operations() {
        setupAdapter()

        /**
         * get data from viewModel
         * */
        viewModel.getUnAssignedClients().observe(this, Observer {
            adapter.setData(ArrayList(it))

            if (it.isEmpty()) {
                tvUnAssignedClients.visibility = View.VISIBLE
            } else {
                tvUnAssignedClients.visibility = View.GONE
            }
        })


        /*
        * search
        * */
        unAssignedClientSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    adapter.filterData("")
                } else {
                    adapter.filterData(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.filterData("")
                } else {
                    adapter.filterData(newText)
                }
                return false
            }
        })

    }

    private fun setupAdapter() {
        adapter = ClientsAdapter("assigned")
        unAssignedClientRecycler.layoutManager = LinearLayoutManager(activity!!)
        unAssignedClientRecycler.adapter = adapter

        unAssignedClientRecycler.itemAnimator = DefaultItemAnimator()
        unAssignedClientRecycler.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun adapterClicks() {
        /*
       * listen to adapter clicks
       * */
        val lc =
            adapter.clientItemLongClick.subscribe { user ->
                Snackbar.make(
                    parentUnAssignedClients,
                    "Are you sure you want to delete?",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("DELETE") {
                        appExecutors.diskIO().execute {
                            viewModel.deleteClient(user)
                        }
                    }.show()
            }

        composable.add(lc)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return UnAssignedClientsFragment()
        }
    }
}
