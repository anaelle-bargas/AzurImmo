package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.model.Appartement
import kotlinx.coroutines.launch

class AppartementViewModel : ViewModel(){

    private val _appartements= mutableStateOf(emptyList<Appartement>())
    val appartements : State<List<Appartement>> = _appartements

    init{
        getAppartements()
    }

    private fun getAppartements(){
        viewModelScope.launch{
            _appartements.value= listOf(
                Appartement(1, 2, 50.2, nbrPieces = 5, "Apparement en face", 1),
                Appartement(2, 3, 50.2, nbrPieces = 5,"Apparement a côté", 2)
            )
        }
    }
}