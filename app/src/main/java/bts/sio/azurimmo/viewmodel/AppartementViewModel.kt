package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Appartement
import kotlinx.coroutines.launch

class AppartementViewModel : ViewModel(){

    private val _appartements= mutableStateOf<List<Appartement>>(emptyList())
    val appartements : State<List<Appartement>> = _appartements

    private val _isLoading=mutableStateOf(false)
    val isLoading : State<Boolean?> = _isLoading

    private val _errorMessage= mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init{
        getAppartements()
    }

    private fun getAppartements(){
        viewModelScope.launch{
            _isLoading.value = true
            try{
                val response = RetrofitInstance.api.getAppartements()
                _appartements.value = response
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la réception des données des appartements : ${e.message}"
            }finally{
                _isLoading.value=false
            }
        }
    }
}