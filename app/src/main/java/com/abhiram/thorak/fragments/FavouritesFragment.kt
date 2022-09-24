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
import com.abhiram.thorak.adapter.CustomAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment() {

    private lateinit var appDb : AppDatabase
    private var appList : MutableList<AppList> = ArrayList()
    private lateinit var allAppList : List<AppList>
    private lateinit var inflate : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_favourites, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        Log.e("Created","true")
        val pm: PackageManager? = context?.packageManager
        appDb = AppDatabase.getDatabse(requireContext())
        allAppList = getFavs()
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {CustomAdapter(allAppList, pm!!, it) }
        recyclerview.adapter = adapter
        return inflate
    }
    override fun onResume() {
        super.onResume()
        show()
    }

    private fun getFavs(): List<AppList> {
        GlobalScope.launch{
            appList.addAll(appDb.appDao().getFav())
        }
        return appList.toList()
    }

    fun show(){
        Log.e("CreatedShow","true")
        val pm: PackageManager? = context?.packageManager
        appDb = AppDatabase.getDatabse(requireContext())
        allAppList = getFavs()
        Log.e("AppList","$allAppList")
        val recyclerview: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {CustomAdapter(allAppList, pm!!, it) }
        recyclerview.adapter = adapter
    }

}