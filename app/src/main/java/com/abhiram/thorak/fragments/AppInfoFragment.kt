package com.abhiram.thorak.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.R
import com.abhiram.thorak.helpers.SharedPreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppInfoFragment : Fragment() ,View.OnClickListener{
    private var pkgName: String? = null
    private lateinit var appDb : AppDatabase
    private lateinit var inflate: View
    private var isfav : Int?= null
    private lateinit var favicon : ImageView
    private lateinit var nameApp : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pkgName = it.getString("pkg")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_app_info, container, false)
        val pm : PackageManager = requireContext().packageManager
        appDb = AppDatabase.getDatabse(requireContext())
        val icon : ImageView = inflate.findViewById(R.id.appicon)
        val name : TextView = inflate.findViewById(R.id.appName)
        val fav : LinearLayout = inflate.findViewById(R.id.favo)
        favicon = inflate.findViewById(R.id.fav)
        val appInfo : ImageView = inflate.findViewById(R.id.appinfo)
        val uninstall : ImageView = inflate.findViewById(R.id.uninstall)
        val appName : String = pkgName.toString()
        val sharedPreferenceHelper = SharedPreferenceHelper()

        sharedPreferenceHelper.SharedPreferenceHelperInit(requireContext())
        name.typeface = sharedPreferenceHelper.getFont(requireContext())
        name.textSize = sharedPreferenceHelper.getFontSize()
        nameApp = pm.getApplicationLabel(pm.getApplicationInfo(appName,0)).toString()
        icon.setImageDrawable(pm.getApplicationIcon(appName))
        name.text = nameApp
        Log.e("Val",isfav.toString())
        isfav(nameApp)
        favicon.setOnClickListener(this)
        appInfo.setOnClickListener{
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", pkgName, null)
            intent.data = uri
            requireContext().startActivity(intent)
        }
        uninstall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DELETE)
            val uri = Uri.fromParts("package", pkgName, null)
            intent.data = uri
            requireContext().startActivity(intent)
            parentFragmentManager.beginTransaction().replace(R.id.frag_view,HomeFragment()).commit()
        }
        return inflate
    }

    private fun isfav(name : String) {
        GlobalScope.launch(Dispatchers.IO){
            isfav = appDb.appDao().isfav(name)
            if (isfav==1){
                favicon.setImageResource(R.drawable.ic_heartfilled)
            }else{
                favicon.setImageResource(R.drawable.ic_heartempty)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            AppInfoFragment().apply {
                arguments = Bundle().apply {
                    putString("pkg", param1)
                }
            }
    }

    override fun onClick(p0: View?) {
        GlobalScope.launch(Dispatchers.IO) {
            isfav = appDb.appDao().isfav(nameApp)
            if (isfav==1){
                appDb.appDao().removeFav(nameApp)
                favicon.setImageResource(R.drawable.ic_heartempty)
            }else{
                appDb.appDao().setFav(nameApp)
                favicon.setImageResource(R.drawable.ic_heartfilled)
            }
        }
    }
}