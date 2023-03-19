package com.abhiram.thorak.helpers

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.util.Log
import java.lang.reflect.Type


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
    private val ICONRADIUS : String = "iconradius"
    private val FONTWEIGHT : String = "fontweight"
    private val FSEEK : String = "fseekvalue"
    private val ISEEK :String = "iseekvalue"

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

    fun setIconRadius(size: Float){
        sharedPreferences.edit()!!.putFloat(ICONRADIUS,size).apply()
    }

    fun setFontWeight(weight:Int){
        sharedPreferences.edit().putInt(FONTWEIGHT,weight).apply()
    }

    fun setFseek(progess : Int){
        sharedPreferences.edit().putInt(FSEEK,progess).apply()
    }

    fun setIseek(progess : Int){
        sharedPreferences.edit().putInt(ISEEK,progess).apply()
    }

    fun getIconHeight():Int {
        return sharedPreferences.getInt(ICONHEIGHT,100)
    }

    fun getIconWidth() : Int{
        return sharedPreferences.getInt(ICONWIDTH,100)
    }

    fun getFontSize() : Float{
        return sharedPreferences.getFloat(FONTSIZE, 16F)
    }

    fun getClockSize() : Float {
        return sharedPreferences.getFloat(CLOCKSIZE,42F)
    }

    fun getDateSize() : Float {
        return sharedPreferences.getFloat(DATESIZE,14F)
    }

    fun getFont(context: Context) : Typeface{
        val font = sharedPreferences.getString(FONT,"K2d").toString()
        return Typeface.createFromAsset(context?.assets, "Fonts/$font.ttf")
    }

    fun getFontName():String{
        return sharedPreferences.getString(FONT,"K2d").toString()
    }

    fun getClockFont(context: Context) : Typeface{
        val font = sharedPreferences.getString(CLOCKFONT,"K2d")
        return Typeface.createFromAsset(context?.assets, "Fonts/$font.ttf")
    }

    fun getFontWeight():Int{
        return sharedPreferences.getInt(FONTWEIGHT,600)
    }

    fun getFseek() : Int{
        return sharedPreferences.getInt(FSEEK,40)
    }

    fun getIseek() : Int{
        return sharedPreferences.getInt(ISEEK,56)
    }
}