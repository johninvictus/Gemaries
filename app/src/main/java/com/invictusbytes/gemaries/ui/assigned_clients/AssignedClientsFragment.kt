package com.invictusbytes.gemaries.ui.assigned_clients


import android.opengl.Visibility
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
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_assigned_clients.*
import kotlinx.android.synthetic.main.fragment_unassigned_clients.*

/**
 * A simple [Fragment] subclass.
 */
class AssignedClientsFragment : BaseFragment() {


    private lateinit var adapter: ClientsAdapter
    private lateinit var viewModel: AssignedClientsViewModel
    val composable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assigned_clients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(AssignedClientsViewModel::class.java)
        operations()
    }

    private fun operations() {
        setupAdapter()

        /**
         * get data from viewModel
         * */
        viewModel.getAssignedClients().observe(this, Observer {
            adapter.setData(ArrayList(it))

            if(it.isEmpty()){
                tvAssignedClients.visibility = View.VISIBLE
            }else {
                tvAssignedClients.visibility = View.GONE
            }
        })

        /*
        * search
        * */
        assignedClientSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onResume() {
        super.onResume()
        adapterClicks()
    }

    override fun onPause() {
        super.onPause()
        composable.clear()
    }

    private fun adapterClicks() {
        /*
       * listen to adapter clicks
       * */
        val lc =
            adapter.clientItemLongClick.subscribe {
                Snackbar.make(
                    parentAssignedClients,
                    "Are you sure you want to delete?",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("DELETE") { v ->
                        viewModel.deleteClient(it)
                    }.show()
            }

        composable.add(lc)
    }

    private fun setupAdapter() {
        adapter = ClientsAdapter("assigned")
        assignedClientRecycler.layoutManager = LinearLayoutManager(activity!!)
        assignedClientRecycler.adapter = adapter

        assignedClientRecycler.itemAnimator = DefaultItemAnimator()
        assignedClientRecycler.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return AssignedClientsFragment()
        }
    }
}
