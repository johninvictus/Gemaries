package com.invictusbytes.gemaries.ui.unassigned


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
import kotlinx.android.synthetic.main.fragment_unassigned.*


class UnassignedFragment : BaseFragment() {

    lateinit var viewModel: UnassignedViewModel
    private lateinit var adapter: CratesAdapter

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return UnassignedFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unassigned, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(UnassignedViewModel::class.java)

        operations()
    }


    private fun operations() {
        setupAdapter()

        /*
        * set data from the db
        * */
        viewModel.unAssignedCrates().observe(this, Observer {
            adapter.setData(ArrayList(it))
        })
    }

    private fun setupAdapter() {
        adapter = CratesAdapter("unassigned")
        unAssignedRecycler.layoutManager = LinearLayoutManager(activity!!)
        unAssignedRecycler.adapter = adapter

        unAssignedRecycler.itemAnimator = DefaultItemAnimator()
        unAssignedRecycler.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }
}
