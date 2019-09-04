package com.invictusbytes.gemaries.ui.assign_crate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.commons.BaseActivity

class AssignCrateActivity : BaseActivity() {

    lateinit var viewModel: AssignCrateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_crate)

        viewModel = getViewModel(AssignCrateViewModel::class.java)
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }


    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AssignCrateActivity::class.java))
        }
    }
}
