package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Locataire
import kotlinx.coroutines.launch

class LocataireViewModel : ViewModel() {
    private val _locataires = mutableStateOf<List<Locataire>>(emptyList())
    val locataires : State<List<Locataire>> = _locataires

    private val _isLoading = mutableStateOf(true)
    val isLoading : State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage : State<String?> = _errorMessage

    init {
        getLocataires()
    }

    fun getLocataires(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getLocataires()
                _locataires.value = response
            }catch (e:Exception){
                _errorMessage.value = "Erreur dans la réception des locataires : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun addLocataire(locataire: Locataire){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("locataire", "Le locataire : ${locataire.toString()}")
                val response = RetrofitInstance.api.addLocataire(locataire)
                if(response.isSuccessful()){
                    getLocataires()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'un locataire ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'un locataire : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun delLocataire(locataireId: Int){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("locataireId", "Le locataireId : ${locataireId}")
                val response = RetrofitInstance.api.delLocataire(locataireId)
                if(response.isSuccessful()){
                    getLocataires()
                } else{
                    _errorMessage.value="Erreur dans la suppression d'un locataire ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la suppression d'un locataire : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }
}