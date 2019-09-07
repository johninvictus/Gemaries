package com.invictusbytes.gemaries.ui.unassigned


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
import com.invictusbytes.gemaries.adapters.CratesAdapter
import com.invictusbytes.gemaries.commons.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_unassigned.*


class UnassignedFragment : BaseFragment() {

    lateinit var viewModel: UnassignedViewModel
    private lateinit var adapter: CratesAdapter
    val composable = CompositeDisposable()

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
            adapter.crateItemLongClick.subscribe {
                Snackbar.make(
                    parentUnAssignedCrates,
                    "Are you sure you want to delete?",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("DELETE") { v ->
                        viewModel.deleteCrate(it)
                    }.show()
            }

        composable.add(lc)
    }

    private fun operations() {
        setupAdapter()

        /*
        * set data from the db
        * */
        viewModel.unAssignedCrates().observe(this, Observer {
            adapter.setData(ArrayList(it))
        })

        /*
        * search
        * */
        unAssignedSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
