package com.abhiram.thorak.helpers

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.util.Log


// To those who are reading this
// I would like to thank Vishnu Sanal (https://github.com/VishnuSanal/) for this idea of "sharedpreferencehelper"


class SharedPreferenceHelper() {
    private lateinit var sharedPreferences: SharedPreferences
    private val ICONHEIGHT : String = "iconheight"
    private val ICONWIDTH : String = "iconwidth"
    private val FONTSIZE : String = "fontsize"
    private val CLOCKSIZE : String = "clocksize"
    private val  DATESIZE : String = "datesize"
    private val FONT : String = "fontname"
    private val CLOCKFONT : String = "clockfont"

    fun SharedPreferenceHelperInit(context: Context) {
        this.sharedPreferences =
            context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
    fun setIconHeight(height : Int){
        sharedPreferences.edit()!!.putInt(ICONHEIGHT,height).apply()
    }

    fun setIconWidth(width:Int){
        sharedPreferences.edit()!!.putInt(ICONWIDTH,width).apply()
    }

    fun setFontSize(size : Float){
        sharedPreferences.edit()!!.putFloat(FONTSIZE,size).apply()
    }

    fun setClockSize(size:Float){
        var datesize : Float = size/4
        sharedPreferences.edit()!!.putFloat(CLOCKSIZE,size).apply()
        sharedPreferences.edit()!!.putFloat(DATESIZE,datesize).apply()
    }

    fun setAppFont(font : String){
        sharedPreferences.edit()!!.putString(FONT,font).apply()
    }

    fun setClockFont(font: String){
        sharedPreferences.edit()!!.putString(CLOCKFONT,font).apply()
    }

    fun getIconHeight():Int {
        return sharedPreferences.getInt(ICONHEIGHT,100)!!
    }

    fun getIconWidth() : Int{
        return sharedPreferences.getInt(ICONWIDTH,100)!!
    }

    fun getFontSize() : Float{
        return sharedPreferences.getFloat(FONTSIZE,16F)!!
    }
    fun getClockSize() : Float {
        return sharedPreferences.getFloat(CLOCKSIZE,42F)
    }

    fun getDateSize() : Float {
        return sharedPreferences.getFloat(DATESIZE,14F)
    }

    fun getFont(context: Context) : Typeface{
        val font = sharedPreferences.getString(FONT,"NotoSans-Regular")
        return Typeface.createFromAsset(context?.assets, "Fonts/$font.ttf")
    }

    fun getClockFont(context: Context) : Typeface{
        val font = sharedPreferences.getString(CLOCKFONT,"NotoSans-Regular")
        return Typeface.createFromAsset(context?.assets, "Fonts/$font.ttf")
    }
}