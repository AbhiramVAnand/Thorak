package com.abhiram.thorak

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PackageChangeReceiver : BroadcastReceiver() {
    private lateinit var pm : PackageManager
    private lateinit var appDb : AppDatabase
    private lateinit var app : AppList

    override fun onReceive(context: Context?, intent: Intent?) {
        // fetching package names from extras
        val packageName = intent?.data?.encodedSchemeSpecificPart
        pm = context!!.packageManager
        appDb = AppDatabase.getDatabse(context)
        when (intent?.action) {
            Intent.ACTION_PACKAGE_FULLY_REMOVED -> isUninstalled(packageName.toString())
            Intent.ACTION_PACKAGE_CHANGED -> installed(packageName.toString())
            Intent.ACTION_PACKAGE_INSTALL -> installed(packageName.toString())
        }
    }
    private fun isUninstalled(pkgName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.appDao().uninstalled(pkgName)
            appDb.appDao().getAll()
        }
    }
    private fun installed(pkg : String){
        GlobalScope.launch(Dispatchers.IO) {
            val appName :String = pm.getApplicationLabel(pm.getApplicationInfo(pkg,0)) as String
            app = AppList(appName,pkg,false)
            appDb.appDao().add(app)
            appDb.appDao().getAll()
        }
    }
}