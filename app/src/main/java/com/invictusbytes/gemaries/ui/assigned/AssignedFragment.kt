package com.invictusbytes.gemaries.ui.assigned


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.commons.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class AssignedFragment : BaseFragment() {

    lateinit var viewModel: AssignedViewModel

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
    }


}
