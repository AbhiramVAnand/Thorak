package com.abhiram.thorak.fragments.startup

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Relation
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.FontAdapter
import com.abhiram.thorak.helpers.SharedPreferenceHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.roundToInt

class CustomFragment : Fragment() {
    val fontsList = ArrayList<String>()
    val sharedPrefernceHelper = SharedPreferenceHelper()
    lateinit var fontName : TextView
    lateinit var demof2 : TextView
    lateinit var demof1 : TextView
    lateinit var demof3 : TextView
    lateinit var demoi1 : ImageView
    lateinit var demoi2 : ImageView
    lateinit var demoi3 : ImageView
    lateinit var fseek : SeekBar
    lateinit var iseek : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.transparent)
        val fontArray = resources.getStringArray(R.array.fonts)
        for(i in fontArray){
            fontsList.add(i.toString())
        }
        fontsList.sort()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_custom, container, false)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        fontName = inflate.findViewById(R.id.fontName)
        sharedPrefernceHelper.SharedPreferenceHelperInit(requireContext())
        fontName.text  = sharedPrefernceHelper.getFontName()
        val fseekVal = sharedPrefernceHelper.getFseek()
        val iseekVal = sharedPrefernceHelper.getIseek()
        val fsize = 12+(fseekVal*0.08)
        val isize = 72+iseekVal

        demoi1 = inflate.findViewById(R.id.demoi1)
        demoi2 = inflate.findViewById(R.id.demoi2)
        demoi3 = inflate.findViewById(R.id.demoi3)

        demof2 = inflate.findViewById(R.id.demof2)
        demof1 = inflate.findViewById(R.id.demof1)
        demof3  = inflate.findViewById(R.id.demof3)

        demof1.textSize = fsize.toFloat()
        demof2.textSize = fsize.toFloat()
        demof3.textSize = fsize.toFloat()

        demoi1.layoutParams.height= isize
        demoi1.layoutParams.width = isize
        demoi2.layoutParams.height= isize
        demoi2.layoutParams.width = isize
        demoi3.layoutParams.height= isize
        demoi3.layoutParams.width = isize

        demoi1.layoutParams
        fseek = inflate.findViewById(R.id.fsize)
        fseek.progress = fseekVal
        iseek = inflate.findViewById(R.id.isize)
        iseek.progress = iseekVal

        demof1.typeface = sharedPrefernceHelper.getFont(requireContext())
        demof2.typeface = sharedPrefernceHelper.getFont(requireContext())
        demof3.typeface = sharedPrefernceHelper.getFont(requireContext())

        demoi1.setImageDrawable(resources.getDrawable(R.drawable.google))
        demoi2.setImageDrawable(resources.getDrawable(R.drawable.thorak))
        demoi3.setImageDrawable(resources.getDrawable(R.drawable.messages))

        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fontChange : RelativeLayout = view.findViewById(R.id.fontchange)
        fontChange.setOnClickListener {
            showFontBtmSht(requireContext())
        }
        fseek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e("Fseek",fseek.progress.toString())
                val fsize = 12+(fseek.progress*0.08)
                demof1.textSize = fsize.toFloat()
                demof2.textSize = fsize.toFloat()
                demof3.textSize = fsize.toFloat()
                sharedPrefernceHelper.setFontSize(fsize.toFloat())
                sharedPrefernceHelper.setFseek(fseek.progress)
            }

        })
        iseek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                val isize : Int = 72+(iseek.progress)
                demoi1.layoutParams.height= isize
                demoi1.layoutParams.width = isize
                demoi1.requestLayout()
                demoi2.layoutParams.height= isize
                demoi2.layoutParams.width = isize
                demoi2.requestLayout()
                demoi3.layoutParams.height= isize
                demoi3.layoutParams.width = isize
                demoi3.requestLayout()
                sharedPrefernceHelper.setIconHeight(isize)
                sharedPrefernceHelper.setIconWidth(isize)
                sharedPrefernceHelper.setIseek(iseek.progress)
            }

        })
    }
    fun showFontBtmSht(context : Context){
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.font_btmsht)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.nubBlu)
        val fontRv : RecyclerView = dialog.findViewById(R.id.fontRV)!!
        val apply : TextView = dialog.findViewById(R.id.applyfont)!!
        val cancel : TextView = dialog.findViewById(R.id.cancelfont)!!
        val adapter = FontAdapter(fontsList,requireContext(),parentFragmentManager)
        apply.setOnClickListener {
            requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
            Toast.makeText(context,sharedPrefernceHelper.getFontName()+" set as app font", Toast.LENGTH_SHORT).show()
            fontName.text = sharedPrefernceHelper.getFontName()
            demof1.typeface = sharedPrefernceHelper.getFont(requireContext())
            demof2.typeface = sharedPrefernceHelper.getFont(requireContext())
            demof3.typeface = sharedPrefernceHelper.getFont(requireContext())
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
            dialog.dismiss()
        }
        fontRv.layoutManager = LinearLayoutManager(requireContext())
        fontRv.adapter = adapter
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}