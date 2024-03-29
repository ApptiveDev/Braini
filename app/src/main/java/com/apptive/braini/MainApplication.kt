package com.apptive.braini

import android.app.Application
import android.content.Context
import com.apptive.braini.R
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    init { instance = this }

    override fun onCreate() {
        super.onCreate()

        val appKey = getString(R.string.kakao_native_app_key)
        KakaoSdk.init(this, appKey)
    }

    companion object {
        lateinit var instance: Application
        fun context() = instance as Context
    }
}