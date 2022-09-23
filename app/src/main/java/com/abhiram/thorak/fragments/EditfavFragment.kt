package com.abhiram.thorak.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.FavAdapter
import com.abhiram.thorak.adapter.StartUpAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditfavFragment : Fragment() {

    private lateinit var appDb : AppDatabase
    private var appList : MutableList<AppList> = ArrayList()
    private lateinit var allAppList : List<AppList>
    private lateinit var inflate : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_editfav, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        val pm: PackageManager? = context?.packageManager
        appDb = AppDatabase.getDatabse(requireContext())
        allAppList = getApps()
        for ( i in allAppList){
            Log.e("AppName", "${i.appName}")
        }
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerviewfav)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { FavAdapter(allAppList, pm!!, it, appDb) }
        recyclerview.adapter = adapter
        return inflate
    }
    override fun onResume() {
        super.onResume()
        allAppList = getApps()
        for ( i in allAppList){
            Log.e("AppName", "${i.appName}")
        }
        show()
    }
    private fun getApps(): List<AppList> {
        GlobalScope.launch{
            appList.addAll(appDb.appDao().getAll())
        }
        return appList.toList()
    }
    private fun show(){
        val pm: PackageManager? = context?.packageManager
        allAppList = getApps()
        for ( i in allAppList){
            Log.e("AppName", "${i.appName}")
        }
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerviewfav)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { StartUpAdapter(allAppList, pm!!, it,appDb) }
        recyclerview.adapter = adapter
    }
}