package com.abhiram.thorak.fragments

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.CustomAdapter
import com.abhiram.thorak.helpers.SharedPreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TrayFragment : Fragment(){

    private lateinit var appDb : AppDatabase
    private var appList : MutableList<AppList> = ArrayList()
    private lateinit var allAppList : List<AppList>
    lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDb = AppDatabase.getDatabse(requireContext())
        allAppList = getApps()
    }
//
    override fun onResume() {
        super.onResume()
        show()
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_tray, container, false)
        val pm: PackageManager? = context?.packageManager
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        val fav : TextView = inflate.findViewById(R.id.textViewtray)
        val sharedPreferenceHelper = SharedPreferenceHelper()

        sharedPreferenceHelper.SharedPreferenceHelperInit(requireContext())
        fav.typeface = sharedPreferenceHelper.getFont(requireContext())
        appDb = AppDatabase.getDatabse(requireContext())
        fragmentTransaction = parentFragmentManager.beginTransaction()
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {CustomAdapter(allAppList, pm!!, it,fragmentTransaction) }
        recyclerview.adapter = adapter
        return inflate
    }

    private fun getApps(): List<AppList> {
        GlobalScope.launch{
            appList.clear()
            appList.addAll(appDb.appDao().getAll())
        }
        return appList.toList()
    }
    private fun show(){
        val pm: PackageManager? = context?.packageManager
        allAppList = getApps()
        val recyclerview: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {CustomAdapter(allAppList, pm!!, it,fragmentTransaction) }
        recyclerview.adapter = adapter
    }
}