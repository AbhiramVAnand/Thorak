package com.abhiram.thorak.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.CustomAdapter
import com.abhiram.thorak.adapter.ItemsViewModel


class TrayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_tray, container, false)
        val pm: PackageManager? = context?.packageManager
        var app : ArrayList<ItemsViewModel>? = ArrayList()
        var pkg : List<PackageInfo> = pm!!.getInstalledPackages(PackageManager.GET_META_DATA)
        for(i in pkg){
            if(!isSystemPackage(i)) {
                app!!.add(ItemsViewModel(pm.getApplicationIcon(i.packageName),pm.getApplicationLabel(i.applicationInfo) as String,i.packageName))
            }
        }
        app!!.sortBy { it.appName }
        if (app != null) {
            for(i in app){
                Log.e("App Name","${i.appName}\n")
                Log.e("App Icon","${i.icon}\n")
            }
        }
        Log.e("Size","${app.size}")
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {CustomAdapter(app, pm, it) }
        recyclerview.adapter = adapter
        return inflate
    }
    fun isSystemPackage(resolveInfo:PackageInfo): Boolean {
        return resolveInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

}