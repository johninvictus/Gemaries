package com.invictusbytes.gemaries.ui.unassigned


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.invictusbytes.gemaries.R

/**
 * A simple [Fragment] subclass.
 */
class UnassignedFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unassigned, container, false)
    }


}
