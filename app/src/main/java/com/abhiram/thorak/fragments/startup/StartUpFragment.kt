package com.abhiram.thorak.fragments.startup

import android.annotation.SuppressLint
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
        val start : Button = inflate.findViewById(R.id.start)
// Textview value using variable
        val testing : TextView =inflate.findViewById(R.id.textView2)
        testing.setTextSize(58F)
//
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
                .applicationInfo.packageName,false)
            addApp(app)
        }
        Log.e("App Count",j.toString() )
//        for(i in pkg){
//            if(!isSystemPackage(i)) {
//                app = AppList(pm.getApplicationLabel(i.applicationInfo) as String ,i.packageName,false)
//                addApp(app)
//            }
//        }
        start.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view, AddFavFragment()).commit()
        }
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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
}