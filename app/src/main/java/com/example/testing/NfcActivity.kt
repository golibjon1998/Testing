package com.example.testing

import android.content.ActivityNotFoundException
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nfc.*
import uz.fozilbekimomov.nfcreader.SimpleCardReader
import uz.fozilbekimomov.nfcreader.model.EmvCard


class NfcActivity : AppCompatActivity(), SimpleCardReader.SimpleCardReaderCallback,
    NfcAdapter.ReaderCallback {

    private var nfcAdapter: NfcAdapter? = null
//    val mLottieAnimationView: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

//        lottie.imageAssetsFolder = "images/"
//        lottie.loop(false)
//        lottie.playAnimation()
        initNFC()

//        start_nfc.setOnClickListener {
        if (requestNFC()) {
            startNFC()
        }
//        }
    }

    private fun startNFC() {
        nfcAdapter?.enableReaderMode(
            this, this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_NFC_B or
                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    private fun initNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
    }

    override fun cardIsReadyToRead(card: EmvCard) {
        Log.d("LLLL", "cardIsReadyToRead: $card")
        sendData("${card.cardNumber}", "${card.expireDateMonth}/${card.expireDateYear}")
    }

    private fun sendData(number: String, date: String) {

        cardNumber.text = "$number\n$date"

    }

    override fun cardMovedTooFastOrLockedNfc() {
        Toast.makeText(this, "Tap again", Toast.LENGTH_LONG).show()
    }

    override fun errorReadingOrUnsupportedCard() {
        Toast.makeText(this, "Error / Unsupported", Toast.LENGTH_LONG).show()
    }

    override fun onTagDiscovered(tag: Tag?) {
        SimpleCardReader.readCard(tag, this)
    }

    private fun requestNFC(): Boolean {
        if (nfcAdapter == null) {
            Toast.makeText(this, "No NFC on this device", Toast.LENGTH_LONG).show()
            return false
        } else if (nfcAdapter?.isEnabled == false) {

            // NFC is available for device but not enabled
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    startActivityForResult(Intent(Settings.Panel.ACTION_NFC), 2265)
                } catch (ignored: ActivityNotFoundException) {
                }

            } else {
                try {
                    startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
                } catch (ignored: ActivityNotFoundException) {
                }
            }
            return false
        }
        return true
    }
}