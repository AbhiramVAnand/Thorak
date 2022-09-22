package com.abhiram.thorak.fragments

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.AppListView
import com.abhiram.thorak.adapter.FavAdapter

class EditfavFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_editfav, container, false)
        val pm: PackageManager? = context?.packageManager
        var app : ArrayList<AppListView>? = ArrayList()
        var pkg : List<PackageInfo> = pm!!.getInstalledPackages(PackageManager.GET_META_DATA)
        for(i in pkg){
            if(!isSystemPackage(i)) {
                app!!.add(AppListView(pm.getApplicationLabel(i.applicationInfo) as String,i.packageName, false))
            }
        }
        app!!.sortBy { it.appName }
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerviewfav)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { FavAdapter(app, pm, it) }
        recyclerview.adapter = adapter
        return inflate
    }
    fun isSystemPackage(resolveInfo:PackageInfo): Boolean {
        return resolveInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
}