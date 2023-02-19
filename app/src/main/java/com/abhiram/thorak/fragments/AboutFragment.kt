package com.abhiram.thorak.fragments

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.abhiram.thorak.R
import com.abhiram.thorak.helpers.SharedPreferenceHelper


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_about, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkBl)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkBl)
        val setdef : TextView = inflate.findViewById(R.id.setdef)
        val tele : ImageView = inflate.findViewById(R.id.tele)
        val gihtub : ImageView = inflate.findViewById(R.id.github)
        val playstore : ImageView = inflate.findViewById(R.id.plyst)
        val sharedPreferenceHelper = SharedPreferenceHelper()

        sharedPreferenceHelper.SharedPreferenceHelperInit(requireContext())
        setdef.typeface = sharedPreferenceHelper.getFont(requireContext())
        setdef.textSize = sharedPreferenceHelper.getFontSize()
        if(isMyLauncherDefault()){
            setdef.text = "Thorak is your Default Launcher"
        }else{
            setdef.text = "Set Thorak as your Default Launcher."
        }
        setdef.setOnClickListener{
            if(!isMyLauncherDefault()) {
                val callHomeSettingIntent = Intent(Settings.ACTION_HOME_SETTINGS)
                startActivity(callHomeSettingIntent)
            }
        }
        tele.setOnClickListener{
            val uri: Uri = Uri.parse("https://t.me/+Db0a9U6XWLExYzFl") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        gihtub.setOnClickListener{
            val uri: Uri = Uri.parse("https://github.com/AbhiramVAnand/Thorak") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        playstore.setOnClickListener{
//            val uri: Uri = Uri.parse("http://www.google.com") // missing 'http://' will cause crashed
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
            Toast.makeText(context,"Link Not Available",Toast.LENGTH_SHORT).show()
        }
//        share.setOnClickListener{
//            val uri: Uri = Uri.parse("http://www.google.com") // missing 'http://' will cause crashed
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }

        return inflate
    }
    fun isMyLauncherDefault(): Boolean {
        val filter = IntentFilter(Intent.ACTION_MAIN)
        filter.addCategory(Intent.CATEGORY_HOME)
        val filters: MutableList<IntentFilter> = ArrayList()
        filters.add(filter)
        val activities: List<ComponentName> = ArrayList()
        val packageManager = requireContext().packageManager
        val myPackageName: String = "com.abhiram.thorak"
        packageManager.getPreferredActivities(filters, activities, null)
        for (activity in activities) {
            if (myPackageName == activity.packageName) {
                return true
            }
        }
        return false
    }
}