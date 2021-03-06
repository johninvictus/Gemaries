package com.invictusbytes.gemaries.ui.scanner

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import com.google.zxing.Result
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.commons.BaseActivity
import com.invictusbytes.gemaries.data.db.entities.Assigned
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.utils.AppExecutors
import kotlinx.android.synthetic.main.activity_scanner.*
import kotlinx.android.synthetic.main.toolbar.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject

class ScannerActivity : BaseActivity(), ZXingScannerView.ResultHandler {


    lateinit var viewModel: ScannerViewModel
    private var scannerView: ZXingScannerView? = null
    private var flash: Boolean = false

    private var state: String? = null
    private var userId: Long? = null

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        viewModel = getViewModel(ScannerViewModel::class.java)


        state = intent.getStringExtra(STATE)
        userId = intent.getLongExtra(USER_ID, 0)

        setupToolbar()
        setupScanner()
        operations()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Scanner"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupScanner() {
        scannerView = ZXingScannerView(this)
        flScanner.addView(scannerView)
    }

    private fun operations() {
        ivFlash.setOnClickListener {
            toggleFlash()
        }



        when (state) {
            "Crate" -> {
                supportActionBar?.title = "Scan Crate"
            }

            "Assign" -> {
                supportActionBar?.title = "Assign Crate"
            }

            "UnAssign" -> {
                supportActionBar?.title = "UnAssign Crate"
            }

        }

    }


    private fun showResult(result: String) {
        tvScannerCode.text = result
        scanResult?.visibility = View.VISIBLE

        // hide after 2 seconds
        Handler().postDelayed({
            scanResult?.visibility = View.GONE
        }, 4000)

    }

    private fun toggleFlash() {
        flash = !flash
        scannerView?.flash = flash
    }

    private fun playBeep() {
        val beep = MediaPlayer.create(this, R.raw.scanner_bip)
        beep.start()
    }

    private fun addCrate(code: String) {
        appExecutors.diskIO().execute {
            val c = viewModel.getCrate(code)
            if (c != null) {
                appExecutors.mainThread().execute {
                    longToast("This crate is already added")
                }
            } else {
                val crate = CratesEntity(code = code, created = Date())
                viewModel.addCrate(crate)

                appExecutors.mainThread().execute {
                    toast("You added this crate")
                }
            }
        }
    }

    private fun assignCrateToUser(code: String) {
        /*
        check if this crate is in the system
        * check if this crate is not assigned
        * then assign assign
        * */
        appExecutors.diskIO().execute {
            val c = viewModel.getCrate(code)

            // is available or not
            if (c != null) {
                // check it if it assigned to anyone
                val assignedCrate = viewModel.getCrateIfAssigned(code)


                if (assignedCrate == null) {
                    // assign to a user
                    val assign = Assigned(
                        null, c.id!!,
                        userId!!, true,
                        Date(), Date()
                    )


                    viewModel.addAssigned(assign)
                    appExecutors.mainThread().execute {
                        longToast("This user as been assigned this crate")
                    }

                } else {
                    appExecutors.mainThread().execute {
                        longToast("This crate is already assigned to someone")
                    }

                }

            } else {
                appExecutors.mainThread().execute {
                    longToast("This crate is not added to the app")
                }
            }
        }

    }

    private fun unAssignCrateToUser(code: String) {
        /*
        * #steps
        * - check if crate is added
        * - check if crate is assigned to this guy
        * - unassign crate
        * */
        appExecutors.diskIO().execute {
            val c = viewModel.getCrate(code)

            // is available or not
            if (c != null) {
                val crateAvailable = viewModel.getCrateIfAssignedToUser(code, userId!!)
                if (crateAvailable != null) {
                    val assigned = viewModel.getUserUnAssignedEntry(userId!!, c.id!!)

                    assigned?.let {
                        val modAssigned = Assigned(
                            id = assigned.id,
                            crateId = assigned.crateId,
                            active = false,
                            added = assigned.added,
                            returned = Date(),
                            userId = assigned.userId
                        )

                        viewModel.updateAssigned(modAssigned)

                        appExecutors.mainThread().execute {
                            longToast("You unassigned this crate")
                        }
                    }


                } else {
                    appExecutors.mainThread().execute {
                        longToast("This crate is not assigned to this user")
                    }
                }
            } else {
                appExecutors.mainThread().execute {
                    longToast("This crate is not added to the app")
                }
            }
        }

    }


    override fun handleResult(rawResult: Result) {
        showResult(rawResult.text)
        playBeep()

        when (state) {
            "Crate" -> {
                supportActionBar?.title = "Scan Crate"
                addCrate(rawResult.text)
            }

            "Assign" -> {
                supportActionBar?.title = "Assign Crate"
                assignCrateToUser(rawResult.text)
            }

            "UnAssign" -> {
                supportActionBar?.title = "UnAssign Crate"
                unAssignCrateToUser(rawResult.text)
            }

        }

        Handler().postDelayed({
            scannerView?.resumeCameraPreview(this)
        }, 2000)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        scannerView?.setResultHandler(this)
        scannerView?.setAspectTolerance(0.2f)
        scannerView?.startCamera()
        scannerView?.flash = flash
    }


    override fun onPause() {
        super.onPause()
        scannerView?.stopCamera()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }


    companion object {
        const val STATE = "STATE"
        const val USER_ID = "USER_ID"

        fun startActivity(context: Context, state: String, user_id: Long) {
            val intent = Intent(context, ScannerActivity::class.java)
            intent.putExtra(STATE, state)
            intent.putExtra(USER_ID, user_id)
            context.startActivity(intent)
        }
    }
}
