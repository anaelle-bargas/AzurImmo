package bts.sio.azurimmo.viewmodel

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

    fun getAppartements(){
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

    fun getAppartementsByBatimentId(batimentId : Int){
        viewModelScope.launch{
            _isLoading.value = true
            try{
                val response= RetrofitInstance.api.getAppartementsByBatimentId(batimentId)
                _appartements.value = response
            }catch(e:Exception){
                _errorMessage.value="Erreur dans la récupération des appartements du batiment ${batimentId} : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun addAppartement(appartement : Appartement){
        viewModelScope.launch {
            _isLoading.value=false
            try {
                val response = RetrofitInstance.api.addAppartement(appartement)
                if(response.isSuccessful()){
                    getAppartements()
                }
                else{
                    _errorMessage.value="Erreur dnas la création d'un appartement ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la création d'un appartement ${e.message}"
            }finally{
                _isLoading.value=false
            }
        }
    }
}