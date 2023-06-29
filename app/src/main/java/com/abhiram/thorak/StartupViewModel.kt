package com.abhiram.thorak

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.abhiram.thorak.helpers.RoomRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartupViewModel(context: Context) : ViewModel(){
    var roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(context)
    }
    val pm: PackageManager? = context?.packageManager
    fun getApps(){
        val intent : Intent = Intent(Intent.ACTION_MAIN,null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        var test : MutableList<ResolveInfo> = pm!!.queryIntentActivities(intent,0)
        var j : Int =0
        for (i in test){
            j = j+1
            Log.e("App Name",pm.getApplicationLabel(i.activityInfo.applicationInfo) as String )
            var app = AppList(i.activityInfo.applicationInfo.packageName,
                pm.getApplicationLabel(i.activityInfo.applicationInfo) as String,
                false,
                false)
            roomRepository.addApps(app)

            Log.e("AppName",app.appName)
        }
    }
}
