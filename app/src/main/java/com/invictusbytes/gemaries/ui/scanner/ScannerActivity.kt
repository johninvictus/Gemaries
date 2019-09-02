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
import kotlinx.android.synthetic.main.activity_scanner.*
import kotlinx.android.synthetic.main.toolbar.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.longToast

class ScannerActivity : BaseActivity(), ZXingScannerView.ResultHandler {


    lateinit var viewModel: ScannerViewModel
    private var scannerView: ZXingScannerView? = null
    private var flash: Boolean = false
    private var previousScann: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        viewModel = getViewModel(ScannerViewModel::class.java)

        setupToolbar()
        setupScanner()
        operations()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Scan Crate"

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
    }


    private fun showResult(result: String) {
        if (previousScann == result) {
            longToast("This crate is already scanned and added")
            return
        }
        previousScann = result


        /**
         * show the result view
         * */
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


    override fun handleResult(rawResult: Result) {
        showResult(rawResult.text)
        playBeep()
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
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ScannerActivity::class.java))
        }
    }
}
