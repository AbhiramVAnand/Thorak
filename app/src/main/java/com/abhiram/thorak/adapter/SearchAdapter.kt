//package com.abhiram.thorak.adapter
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.Typeface
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.inputmethod.EditorInfo
//import android.widget.*
//import androidx.fragment.app.FragmentTransaction
//import com.abhiram.thorak.AppList
//import com.abhiram.thorak.R
//import com.abhiram.thorak.helpers.SharedPreferenceHelper
//
//class SearchAdapter(
//    private val mContext: Context,
//    private val viewResourceId: Int,
//    private val items: ArrayList<AppList>,
//    private val frag: FragmentTransaction,
//    private val autoCompleteTextView: AutoCompleteTextView
//) : ArrayAdapter<AppList?>(mContext, viewResourceId, items.toList()){
//
//    private val itemsAll = items.clone() as ArrayList<AppList>
//    private var suggestions = ArrayList<AppList>()
//    private lateinit var pm : PackageManager
//    private lateinit var fragT : FragmentTransaction
//    private var sharedPreferenceHelper = SharedPreferenceHelper()
//
//    @SuppressLint("ResourceAsColor")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        sharedPreferenceHelper.SharedPreferenceHelperInit(context)
//        val font : Typeface = sharedPreferenceHelper.getFont(context)
//        val height = sharedPreferenceHelper.getIconHeight()
//        val width = sharedPreferenceHelper.getIconWidth()
//        var v: View? = convertView
//        if (v == null) {
//            val vi = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            v = vi.inflate(viewResourceId, null)
//        }
//        v?.setBackgroundColor(R.color.darkDim)
//        v?.background = context.getDrawable(R.drawable.searchitembg)
//        pm = context.packageManager
//        fragT = frag
//        try {
//            val app: AppList? = suggestions[position]
//            if (app != null) {
//                val appName = v?.findViewById(R.id.textApp) as TextView?
//                val appIcon : ImageView? = v?.findViewById(R.id.testicon)
//                appIcon?.setImageDrawable(pm.getApplicationIcon(app.pkgName))
//                appName?.text = app.appName
//                appName?.setTextSize(sharedPreferenceHelper.getFontSize())
//                appName?.typeface = font
//                appIcon?.layoutParams!!.height =height
//                appIcon?.layoutParams!!.width = width
//
//            }
//            v?.setOnClickListener {
//                val launchIntent : Intent = pm.getLaunchIntentForPackage(app!!.pkgName)!!
//                context.startActivity(launchIntent)
//            }
//            autoCompleteTextView.setOnEditorActionListener { textView, action, event ->
//                var handled = false
//                if (action == EditorInfo.IME_ACTION_DONE) {
//                    if(suggestions.size!=0){
//                        val launchIntent : Intent = pm.getLaunchIntentForPackage(suggestions[0].pkgName)!!
//                        context.startActivity(launchIntent)
//                        handled = true
//                    }
//                }
//                handled
//            }
//        }catch (e : Exception){ }
//        return v!!
//    }
//
//    override fun getFilter(): Filter {
//        return nameFilter
//    }
//
//    private var nameFilter: Filter = object : Filter() {
//        override fun convertResultToString(resultValue: Any): String {
//            return (resultValue as AppList).appName
//        }
//
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            return if (constraint != null) {
//                suggestions.clear()
//                for (app in itemsAll) {
//                    if (app.appName.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
//                        suggestions.add(app)
//                    }
//                }
//                val filterResults = FilterResults()
//                filterResults.values = suggestions
//                filterResults.count = suggestions.size
//                filterResults
//            } else {
//                FilterResults()
//            }
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            val filteredList =  results?.values as ArrayList<AppList>?
//
//            if (results != null && results.count > 0) {
//                clear()
//                for (c: AppList in filteredList ?: listOf<AppList>()) {
//                    add(c)
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//}
