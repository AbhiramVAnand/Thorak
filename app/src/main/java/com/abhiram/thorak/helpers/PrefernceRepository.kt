package com.abhiram.thorak.helpers

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.compose.foundation.shape.CornerBasedShape
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.Database.ShapeIcon
import com.abhiram.thorak.ui.theme.IconShapes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// To those who are reading this
// I would like to thank Vishnu Sanal (https://github.com/VishnuSanal/) for this idea of "sharedpreferencehelper"


class PrefernceRepository(context : Context) {
    val context = context
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private val ISFIRSTRUN : String = "isFirstRun"
    private val FONTSIZE : String = "fontsize"
    private val CLOCKSIZE : String = "clocksize"
    private val DATESIZE : String = "datesize"
    private val FONT : String = "fontname"
    private val CLOCKFONT : String = "clockfont"
    private val FONTWEIGHT : String = "fontweight"
    private val FSEEK : String = "fseekvalue"
    private val ISEEK :String = "iseekvalue"
    private val ISHAPE : String = "ishape"

//    fun addShape(){
//        var appDb : AppDatabase = AppDatabase.getDatabse(context)
//        val shape : ShapeIcon = ShapeIcon(1, IconShapes.small)
//        GlobalScope.launch(Dispatchers.IO) {
//            appDb.appDao().addShape(shape)
//        }
//    }

//    fun getShape() : CornerBasedShape{
//        var appDb : AppDatabase = AppDatabase.getDatabse(context)
//        var shape = IconShapes.small
//        GlobalScope.launch(Dispatchers.IO) {
//            shape = appDb.appDao().getShape()
//        }
//        return shape
//    }

//    fun setShape(shape: CornerBasedShape){
//        var appDb : AppDatabase = AppDatabase.getDatabse(context)
//        GlobalScope.launch(Dispatchers.IO) {
//            appDb.appDao().setShape(shape)
//        }
//    }

    fun isFirstRun():Boolean{
        return sharedPreferences.getBoolean(ISFIRSTRUN,true)
    }

    fun isNotFirstRun(isfirst : Boolean){
        sharedPreferences.edit()!!.putBoolean(ISFIRSTRUN,false).apply()
    }

//  Icon size
    fun setIseek(progess : Float){
        sharedPreferences.edit().putFloat(ISEEK,progess).apply()
    }


    fun getIseek() : Float{
        return sharedPreferences.getFloat(ISEEK,35.5F)
    }

//    Icon Shape
    fun getIconShape() : Int{
        return sharedPreferences.getInt(ISHAPE,2)
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


    fun setFontWeight(weight:Int){
        sharedPreferences.edit().putInt(FONTWEIGHT,weight).apply()
    }

    fun setFseek(progess : Int){
        sharedPreferences.edit().putInt(FSEEK,progess).apply()
    }

    fun getFontSize() : Float{
        return sharedPreferences.getFloat(FONTSIZE, 14F)
    }

    fun getClockSize() : Float {
        return sharedPreferences.getFloat(CLOCKSIZE,42F)
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


    fun getFseek() : Int{
        return sharedPreferences.getInt(FSEEK,25)
    }

}