package com.invictusbytes.gemaries.ui.assigned_clients


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.ClientsAdapter
import com.invictusbytes.gemaries.commons.BaseFragment
import kotlinx.android.synthetic.main.fragment_assigned_clients.*

/**
 * A simple [Fragment] subclass.
 */
class AssignedClientsFragment : BaseFragment() {


    private lateinit var adapter: ClientsAdapter
    private lateinit var viewModel: AssignedClientsViewModel

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
        })
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
