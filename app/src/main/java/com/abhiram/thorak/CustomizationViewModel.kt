import android.content.Context
import android.graphics.fonts.FontFamily
import android.support.v4.os.IResultReceiver._Parcel
import android.widget.Toast
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhiram.thorak.helpers.PrefernceRepository
import com.abhiram.thorak.helpers.RoomRepository
import com.abhiram.thorak.ui.theme.IconShapes
import com.abhiram.thorak.ui.theme.JosefinSans
import com.abhiram.thorak.ui.theme.Jura
import com.abhiram.thorak.ui.theme.Lato
import com.abhiram.thorak.ui.theme.Papyrus
import com.abhiram.thorak.ui.theme.Phudu
import com.abhiram.thorak.ui.theme.Poppins
import com.abhiram.thorak.ui.theme.SquadaOne
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CustomizationViewModel(context: Context) : ViewModel(){
    var prefernceRepository : PrefernceRepository
    var roomRepository: RoomRepository
    init {
        prefernceRepository = PrefernceRepository(context)
        roomRepository = RoomRepository(context)
    }

//    Icon Size
    var iseektemp : Float = prefernceRepository.getIseek()
    private val _iseek : MutableLiveData<Float> = MutableLiveData(iseektemp)
    val iseek : LiveData<Float> = _iseek
    fun onISlide(newIseek : Float){
        _iseek.value = newIseek
        prefernceRepository.setIseek(newIseek)
    }


//    Icon Shape
    private val _iconShape = MutableStateFlow(IconShapes.small)
    val iconShape = _iconShape.asStateFlow()
    fun changeShape(shape : CornerBasedShape, choice : Int){
        _iconShape.value = shape
        roomRepository.addShape(choice)
    }

//    Font Name
    var Font = prefernceRepository.getFont()
    private val _font = MutableStateFlow(Font)
    val font = _font.asStateFlow()

    fun changeFont(font: String){
        _font.value = font
        prefernceRepository.setAppFont(font)
    }

//    Font slider
    var fSlider = prefernceRepository.getFontSize()
    private val _fSlider = MutableStateFlow(fSlider)
    val fPos = _fSlider.asStateFlow()

    fun onFSlide(pos : Float){
        _fSlider.value = pos
        prefernceRepository.setFontSize(pos)
    }
}