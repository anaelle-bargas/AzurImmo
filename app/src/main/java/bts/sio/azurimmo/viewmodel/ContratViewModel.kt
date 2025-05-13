package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Contrat
import kotlinx.coroutines.launch

class ContratViewModel:ViewModel() {
    private val _contrats= mutableStateOf<List<Contrat>>(emptyList())
    val contrats:State<List<Contrat>> = _contrats

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init {
        getContrats()
    }

    fun getContrats(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val response = RetrofitInstance.api.getContrats()
                _contrats.value = response
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la récupération des contrats: ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun addContrat(contrat: Contrat){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("contrat", "Le contrat' : ${contrat.toString()}")
                val response = RetrofitInstance.api.addContrat(contrat)
                if(response.isSuccessful()){
                    getContrats()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'une contrat ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'une contrat : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun delContrat(contratId: Int){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("contratId", "Le contratId : ${contratId}")
                val response = RetrofitInstance.api.delContrat(contratId)
                if(response.isSuccessful()){
                    getContrats()
                } else{
                    _errorMessage.value="Erreur dans la suppression d'un contrat ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la suppression d'un contrat : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }


    }

    fun getContratsByAppartementId(appartementId : Int){
        viewModelScope.launch{
            _isLoading.value = true
            try{
                val response= RetrofitInstance.api.getContratsByAppartementId(appartementId)
                _contrats.value = response
            }catch(e:Exception){
                _errorMessage.value="Erreur dans la récupération des contrats de l'appartement ${appartementId} : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }


}