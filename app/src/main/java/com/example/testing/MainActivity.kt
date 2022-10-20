package com.example.testing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing.adapter.FastAdapter
import com.example.testing.adapter.LifeHackAdapter
import com.example.testing.adapter.LocalAdapter
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private lateinit var fastAdapter: FastAdapter
    private lateinit var localAdapter: LocalAdapter
    private lateinit var lifeAdapter: LifeHackAdapter
    private var MY_SCAN_REQUEST_CODE = 111
    private var isShowBalance = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fastAdapter = FastAdapter(Constants.fastList)
        localAdapter = LocalAdapter(Constants.localList)
        lifeAdapter = LifeHackAdapter(Constants.lifeList)
        fastRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        fastRecycler.setHasFixedSize(true)
        fastRecycler.adapter = fastAdapter

        localRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        localRecycler.setHasFixedSize(true)
        localRecycler.adapter = localAdapter

        lifeHackRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        lifeHackRecycler.setHasFixedSize(true)
        lifeHackRecycler.adapter = lifeAdapter

        showHideBtn.setOnClickListener {
            manageShowBalance()
        }

        scanCardLayout.setOnClickListener {
            scanCardClickListener()
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedTime + 3000 > System.currentTimeMillis()) {
                    finish()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Press back again to leave the app.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                backPressedTime = System.currentTimeMillis()
            }

        })
    }

    private fun manageShowBalance() {
        if (isShowBalance) {
            balans.text = "*** ***"
            showHideBtn.setImageResource(R.drawable.ic_eye_off_line)
        } else {

            balans.text = "120 000"
            showHideBtn.setImageResource(R.drawable.ic_eye_line)
        }
        isShowBalance = !isShowBalance
    }


    private fun scanCardClickListener() {
        val scanIntent = Intent(this, CardIOActivity::class.java)

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true) // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false) // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false) // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true)

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_SCAN_REQUEST_CODE) {
            var resultDisplayStr: String
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                val scanResult: CreditCard? =
                    data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = """
                Card Number: ${scanResult?.redactedCardNumber}
                
                """.trimIndent()

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );
                if (scanResult?.isExpiryValid == true) {
                    resultDisplayStr += """
                    Expiration Date: ${scanResult.expiryMonth}/${scanResult.expiryYear}
                    
                    """.trimIndent()
                }
                if (scanResult?.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += """CVV has ${scanResult.cvv.length} digits."""
                }
                if (scanResult?.postalCode != null) {
                    resultDisplayStr += """
                    Postal Code: ${scanResult.postalCode}
                    
                    """.trimIndent()
                }
            } else {
                resultDisplayStr = "Scan was canceled."
            }
            // do something with resultDisplayStr, maybe display it in a textView
            cardTv.text = resultDisplayStr
        }
        // else handle other activity results
    }

}