package com.invictusbytes.gemaries.ui.assigned


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
import com.invictusbytes.gemaries.adapters.CratesAdapter
import com.invictusbytes.gemaries.commons.BaseFragment
import kotlinx.android.synthetic.main.fragment_assigned.*

/**
 * A simple [Fragment] subclass.
 */
class AssignedFragment : BaseFragment() {

    lateinit var viewModel: AssignedViewModel
    private lateinit var adapter: CratesAdapter

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return AssignedFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assigned, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(AssignedViewModel::class.java)
        operations()
    }

    private fun operations() {
        setupAdapter()

        viewModel.assignedCrates().observe(this, Observer {
            adapter.setData(ArrayList(it))
        })
    }

    private fun setupAdapter() {
        adapter = CratesAdapter("assigned")
        assignedRecycler.layoutManager = LinearLayoutManager(activity!!)
        assignedRecycler.adapter = adapter

        assignedRecycler.itemAnimator = DefaultItemAnimator()
        assignedRecycler.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }

}
