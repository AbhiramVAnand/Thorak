package com.abhiram.thorak.fragments.startup

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.AppListView
import com.abhiram.thorak.adapter.FavAdapter
import com.abhiram.thorak.adapter.StartUpAdapter
import com.abhiram.thorak.fragments.HomeFragment
import java.sql.RowId

class AddFavFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_add_fav, container, false)
        val done : Button = inflate.findViewById(R.id.done)
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
        val adapter = context?.let { StartUpAdapter(app, pm, it) }
        recyclerview.adapter = adapter
        done.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frag_view, FinishStartupFragment()).commit()
        }
        return inflate
    }
    fun isSystemPackage(resolveInfo:PackageInfo): Boolean {
        return resolveInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
}