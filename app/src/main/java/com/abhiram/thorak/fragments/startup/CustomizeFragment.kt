package com.abhiram.thorak.fragments.startup

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.abhiram.thorak.R
import com.abhiram.thorak.helpers.SharedPreferenceHelper

class CustomizeFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_font, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)

        val customize : TextView = inflate.findViewById(R.id.customize)
        val changeFont : TextView = inflate.findViewById(R.id.changefont)
        val apName : TextView = inflate.findViewById(R.id.previewName)
        val iconSize : TextView = inflate.findViewById(R.id.iconSize)
        val fontSize : TextView = inflate.findViewById(R.id.fontSize)
        val appIcon : ImageView = inflate.findViewById(R.id.previewIcon)
        val next : ImageView = inflate.findViewById(R.id.rightarrow)
        val fontname : TextView = inflate.findViewById(R.id.fontName)
        val iseek = inflate.findViewById<SeekBar>(R.id.isize)
        val fseek = inflate.findViewById<SeekBar>(R.id.fsize)

        val ctime : TextClock = inflate.findViewById(R.id.ctime)
        val cdate : TextClock = inflate.findViewById(R.id.cdate)

        val sharedPreferenceHelper = SharedPreferenceHelper()
        sharedPreferenceHelper.SharedPreferenceHelperInit(requireContext())

        val typeface = sharedPreferenceHelper.getFont(requireContext())
        val textsize = sharedPreferenceHelper.getFontSize()
        val iconheight = sharedPreferenceHelper.getIconHeight()
        val iconwidth = sharedPreferenceHelper.getIconWidth()
        val fontName = sharedPreferenceHelper.getFontName()




        customize.typeface = typeface
        apName.typeface = typeface
        changeFont.typeface = typeface
        fontname.typeface = typeface
        fontSize.typeface = typeface
        iconSize.typeface = typeface


        apName.textSize = textsize
        fontname.textSize = textsize
        iconSize.textSize = textsize
        fontSize.textSize = textsize



        fontname.text = fontName
        apName.text = resources.getString(R.string.app_name)

        appIcon.layoutParams.width = iconwidth
        appIcon.layoutParams.height = iconheight

        ctime.format12Hour = "hh:mm"
        cdate.format12Hour = "dd MMM yy"

        val pm = requireContext().packageManager
        appIcon.setImageDrawable(pm.getApplicationIcon("com.android.vending"))

        iseek.progress = sharedPreferenceHelper.getIseek()
        fseek.progress = sharedPreferenceHelper.getFseek()

        iseek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                val isize : Int = 72+(iseek.progress)
                appIcon.layoutParams.height= isize
                appIcon.layoutParams.width = isize
                appIcon.requestLayout()
                sharedPreferenceHelper.setIconHeight(isize)
                sharedPreferenceHelper.setIconWidth(isize)
                sharedPreferenceHelper.setIseek(iseek.progress)
            }
        })

        fseek?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val fsize = 12+fseek.progress/12
                apName.textSize = fsize.toFloat()
                sharedPreferenceHelper.setFontSize(fsize.toFloat())
                sharedPreferenceHelper.setFseek(fseek.progress)
            }

        })

        next.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view, DirectionsFragment()).addToBackStack("startup").commit()
        }

        fontname.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frag_view, ChangeFontFragment()).addToBackStack("startup").commit()
        }

        return inflate
    }
}

