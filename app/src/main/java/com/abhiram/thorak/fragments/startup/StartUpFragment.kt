package com.abhiram.thorak.fragments.startup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartUpFragment : Fragment() {

    private lateinit var app : AppList
    private lateinit var appDb : AppDatabase

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_start_up, container, false)
        val start : ImageButton = inflate.findViewById(R.id.start)
        appDb = AppDatabase.getDatabse(requireContext())
        val pm: PackageManager? = context?.packageManager
        var pkg : List<PackageInfo> = pm!!.getInstalledPackages(PackageManager.GET_META_DATA)
        val intent : Intent = Intent(Intent.ACTION_MAIN,null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        var test : MutableList<ResolveInfo> = pm!!.queryIntentActivities(intent,0)
        var j : Int =0
        for (i in test){
            j = j+1
            Log.e("App Name",pm.getApplicationLabel(i.activityInfo.applicationInfo) as String )
            app = AppList(pm.getApplicationLabel(i.activityInfo.applicationInfo) as String ,i.activityInfo
                .applicationInfo.packageName,false,false)
            addApp(app)
            hide("Instagram")
        }
        Log.e("App Count",j.toString() )
        start.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view, AddFavFragment()).commit()
        }
        return inflate
    }
    private fun isSystemPackage(resolveInfo: PackageInfo): Boolean {
        resolveInfo.applicationInfo.flags
        return resolveInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    private fun addApp(app : AppList){
        GlobalScope.launch(Dispatchers.IO) {
            appDb.appDao().add(app)
        }
    }
    private fun hide(app : String){
        GlobalScope.launch(Dispatchers.IO) {
            appDb.appDao().hide(app)
        }
    }
}