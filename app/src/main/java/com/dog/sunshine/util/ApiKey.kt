package com.dog.sunshine.util

import android.util.Base64

class ApiKey {
    companion object {
        init {
            System.loadLibrary("keys")
        }

        private external fun getApiKey(): String

        fun key():String{
            return String(Base64.decode(getApiKey(), Base64.DEFAULT))
        }
    }
}
