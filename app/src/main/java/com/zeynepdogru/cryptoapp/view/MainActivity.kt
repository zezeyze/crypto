package com.zeynepdogru.cryptoapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.zeynepdogru.cryptoapp.R

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1001
    private val tag="HUAWEI_PUSH"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
        }
        getToken()

    }
    private fun getToken() {
        object : Thread() {
            override fun run() {
                try {
                    val appId = "111127709"
                    val tokenScope = "HCM"
                    val token = HmsInstanceId.getInstance(this@MainActivity).getToken(appId, tokenScope)
                    Log.i(tag, "get token:$token")

                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token)
                    }
                } catch (e: ApiException) {
                    Log.e(tag, "get token failed, $e")
                }
            }
        }.start()
    }

    private fun sendRegTokenToServer(token: String) {
        Log.i(tag, "sending token to server. token:$token")
    }

}