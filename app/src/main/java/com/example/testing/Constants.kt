package com.example.testing

import com.example.testing.model.FastModel
import com.example.testing.model.LifeModel
import com.example.testing.model.LocalModel

object Constants {

    val fastList = arrayListOf(
        FastModel("QR Payment", R.drawable.ic_baseline_qr_code_24),
        FastModel("Humo Pay", R.drawable.ic_baseline_screen_humo),
        FastModel("History", R.drawable.ic_baseline_history_24),
        FastModel("My home", R.drawable.ic_outline_home_24),
        FastModel("Exchange Rate", R.drawable.ic_outline_monetization_on_24),
        FastModel("Exchange", R.drawable.ic_baseline_currency_exchange_24),
        FastModel("Internatioinal Pay", R.drawable.ic_baseline_land),
        FastModel("Wallets", R.drawable.ic_outline_account_balance_wallet_24),
        FastModel("Loans", R.drawable.ic_baseline_payment_24)
    )

    val localList = arrayListOf(
        LocalModel("Oxymed Beruniy 93/1", R.drawable.ic_baseline_qr_code_24, "0.45 km"),
        LocalModel("Caffelito", R.drawable.ic_baseline_screen_humo, "0.52 km"),
        LocalModel("999 apteka N14 Beruniy", R.drawable.ic_baseline_history_24, "0.59 km"),
        LocalModel("Baggi Men", R.drawable.ic_outline_home_24, "0.62 km"),
        LocalModel("Street 77 Beruniy", R.drawable.ic_outline_monetization_on_24, "0.77 km"),
        LocalModel("WOK Beruniy", R.drawable.ic_baseline_currency_exchange_24, "0.91 km"),
        LocalModel("Mars Yunusobod", R.drawable.ic_baseline_land, "1.22 km"),
        LocalModel("Mars Tinchlik", R.drawable.ic_outline_account_balance_wallet_24, "1.28 km"),
        LocalModel("Safia Tinchlik", R.drawable.ic_baseline_payment_24, "1.36 km"),
        LocalModel("Libos Gallery Tinchlik", R.drawable.ic_baseline_screen_humo, "1.45 km"),
        LocalModel("Openlab Almazar", R.drawable.ic_outline_home_24, "1.69 km")
    )

    val lifeList = arrayListOf(
        LifeModel(R.color.blue,R.drawable.ic_baseline_home_24,"My Home"),
        LifeModel(R.color.green,R.drawable.ic_spy_fill,"Beware scammers"),
        LifeModel(R.color.orange,R.drawable.ic_visa_fill,"Direct shipping"),
        LifeModel(R.color.blue,R.drawable.ic_baseline_payment_24,"Ordering Cards"),
        LifeModel(R.color.green,R.drawable.ic_outline_monetization_on_24,"Exchange currencies"),
        LifeModel(R.color.orange,R.drawable.ic_rocket_2_fill,"Rocket translations"),
        LifeModel(R.color.blue,R.drawable.ic_percent_line,"For transfers"),
    )
}