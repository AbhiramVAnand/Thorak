package com.abhiram.thorak.fragments.startup

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
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
        appDb = AppDatabase.getDatabse(requireContext())
        val pm: PackageManager? = context?.packageManager
        var pkg : List<PackageInfo> = pm!!.getInstalledPackages(PackageManager.GET_META_DATA)
        for(i in pkg){
            if(!isSystemPackage(i)) {
                app = AppList(pm.getApplicationLabel(i.applicationInfo) as String ,i.packageName,false)
                addApp(app)
            }
        }
        start.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frag_view, AddFavFragment()).commit()
        }
        return inflate
    }


    private fun isSystemPackage(resolveInfo: PackageInfo): Boolean {
        return resolveInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    private fun addApp(app : AppList){
        GlobalScope.launch(Dispatchers.IO) {
            appDb.appDao().add(app)
        }
    }
}