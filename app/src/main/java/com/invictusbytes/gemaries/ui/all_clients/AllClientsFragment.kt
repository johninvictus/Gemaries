package com.invictusbytes.gemaries.ui.all_clients


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.invictusbytes.gemaries.R

/**
 * A simple [Fragment] subclass.
 */
class AllClientsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return AllClientsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_clients, container, false)
    }


}
