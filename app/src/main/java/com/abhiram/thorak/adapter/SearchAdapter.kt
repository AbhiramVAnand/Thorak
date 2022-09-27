package com.abhiram.thorak.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import com.abhiram.thorak.fragments.HomeFragment

class SearchAdapter(private val mContext: Context,
                       private val viewResourceId: Int,
                       private val items: ArrayList<AppList>,
                       private val frag : FragmentTransaction) : ArrayAdapter<AppList?>(mContext, viewResourceId, items.toList()){

    private val itemsAll = items.clone() as ArrayList<AppList>
    private var suggestions = ArrayList<AppList>()
    private lateinit var pm : PackageManager
    private lateinit var fragT : FragmentTransaction

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            val vi = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(viewResourceId, null)
        }
        v?.setBackgroundColor(R.color.offwhite)
        pm = context.packageManager
        fragT = frag
        try {
            val app: AppList? = suggestions[position]
            if (app != null) {
                val appName = v?.findViewById(R.id.textApp) as TextView?
                val appIcon : ImageView? = v?.findViewById(R.id.testicon)
                appIcon?.setImageDrawable(pm.getApplicationIcon(app.pkgName))
                appName?.text = app.appName
            }
            v?.setOnClickListener {
                val launchIntent : Intent = pm.getLaunchIntentForPackage(app!!.pkgName)!!
                context.startActivity(launchIntent)
                fragT.replace(R.id.frag_view,HomeFragment()).commit()
            }
        }catch (e : Exception){ }
        return v!!
    }

    override fun getFilter(): Filter {
        return nameFilter
    }

    private var nameFilter: Filter = object : Filter() {
        override fun convertResultToString(resultValue: Any): String {
            return (resultValue as AppList).appName
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if (constraint != null) {
                suggestions.clear()
                for (app in itemsAll) {
                    if (app.appName.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(app)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                filterResults
            } else {
                FilterResults()
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val filteredList =  results?.values as ArrayList<AppList>?

            if (results != null && results.count > 0) {
                clear()
                for (c: AppList in filteredList ?: listOf<AppList>()) {
                    add(c)
                }
                notifyDataSetChanged()
            }
        }
    }

}
