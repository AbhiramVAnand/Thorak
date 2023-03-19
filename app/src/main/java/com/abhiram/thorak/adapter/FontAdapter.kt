package com.abhiram.thorak.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.helpers.SharedPreferenceHelper


class FontAdapter(
    private val fonts: ArrayList<String>,
    private val context: Context,
    val fragT: FragmentManager,

    ) : RecyclerView.Adapter<FontAdapter.ViewHolder>() {
    private val sharedPreferenceHelper = SharedPreferenceHelper()
    private lateinit var font : String
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fontlist,parent,false)
        sharedPreferenceHelper.SharedPreferenceHelperInit(context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        font = fonts[position]
        holder.fontName.text = font
        holder.fontName.typeface = Typeface.createFromAsset(context.assets, "Fonts/$font.ttf")
        holder.fontName.textSize = sharedPreferenceHelper.getFontSize()
        holder.fontNameCard.setOnClickListener{
            sharedPreferenceHelper.setAppFont(fonts[position])
            Toast.makeText(context,fonts[position]+" set as app font", Toast.LENGTH_SHORT).show()
            fragT.popBackStack()
        }
    }

    override fun getItemCount(): Int {
        return fonts.size
    }

    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val fontNameCard : LinearLayout = itemview.findViewById(R.id.fontNameCard)
        val fontName : TextView = itemView.findViewById(R.id.listitemF)
    }
}
