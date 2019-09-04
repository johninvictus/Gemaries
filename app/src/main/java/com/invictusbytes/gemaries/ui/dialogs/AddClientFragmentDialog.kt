package com.invictusbytes.gemaries.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.invictusbytes.gemaries.R
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_dialog_add_client.*
import org.jetbrains.anko.toast

class AddClientFragmentDialog : DialogFragment() {

    var clientDialogEvent = PublishSubject.create<Pair<String, Int>>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_add_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        operations()
    }

    private fun operations() {
        clientCancel.setOnClickListener {
            dismiss()
        }

        clientSubmit.setOnClickListener {
            validateAndSubmit()
        }
    }


    private fun validateAndSubmit() {
        if (etClientName.text.isNullOrEmpty() || etClientPhone.text.isNullOrEmpty()) {
            activity?.toast("Make sure all field are filled")
            return
        }

        val phone = etClientPhone.text.toString()
        val name = etClientName.text.toString()

        if (!phone.contains("07") || phone.length != 10) {
            activity?.toast("Pleas use a valid kenyan number")
            return
        }

        clientDialogEvent.onNext(Pair(name, phone.toInt()))

        /*
        * clear previous text
        * */
        etClientPhone.text = null
        etClientName.text = null

        dismiss()
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


    companion object {
        @JvmStatic
        fun newInstance(): AddClientFragmentDialog {
            return AddClientFragmentDialog()
        }
    }
}