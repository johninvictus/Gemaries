package com.invictusbytes.gemaries.ui.unassigned


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.commons.BaseFragment


class UnassignedFragment : BaseFragment() {

    lateinit var viewModel: UnassignedViewModel

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
    }


}
