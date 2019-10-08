package com.verve.demoapplication

import android.app.Application
import com.verve.api.Verve

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Verve.initialize(this, "e094fe72fcdd44619042824996f21869", "6c8bcc4e765e46b1bf7743a00a364921")
    }
}