package org.sopt.and

import android.app.Application
import org.sopt.and.ui.theme.InfoDataStore

class GlobalApplication: Application() {
    private lateinit var dataStore: InfoDataStore

    companion object {
        lateinit var globalApplication: GlobalApplication
        fun getInstance() = globalApplication
    }

    override fun onCreate() {
        super.onCreate()

        globalApplication = this
        dataStore = InfoDataStore(this)
    }

    fun getDataStore(): InfoDataStore = dataStore

}