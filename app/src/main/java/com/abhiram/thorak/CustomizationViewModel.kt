import android.content.Context
import android.support.v4.os.IResultReceiver._Parcel
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhiram.thorak.helpers.PrefernceRepository
import com.abhiram.thorak.ui.theme.IconShapes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CustomizationViewModel(context: Context) : ViewModel(){
    val prefernceRepository = PrefernceRepository(context)
    var iseektemp : Float = prefernceRepository.getIseek()
    var iconChoice : CornerBasedShape = IconShapes.small

    private val _iseek : MutableLiveData<Float> = MutableLiveData(iseektemp)
    val iseek : LiveData<Float> = _iseek
    fun onISlide(newIseek : Float){
        _iseek.value = newIseek
        prefernceRepository.setIseek(newIseek)
    }

//    private val _ishape : MutableLiveData<CornerBasedShape> = MutableLiveData(iconChoice)
//    val ishape : LiveData<CornerBasedShape> = _ishape
//    fun onShapeChange(newShape : CornerBasedShape){
//        _ishape.value = newShape
//        iconChoice = newShape
////        prefernceRepository.setShape(shape = newShape)
//    }

    private val _iconShape = MutableStateFlow(IconShapes.small)

    val iconShape = _iconShape.asStateFlow()

    fun changeShape(shape : CornerBasedShape){
        _iconShape.value = shape
    }

}