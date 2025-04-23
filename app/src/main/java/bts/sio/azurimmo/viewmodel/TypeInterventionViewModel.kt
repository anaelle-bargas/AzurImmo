package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.TypeIntervention
import kotlinx.coroutines.launch

class TypeInterventionViewModel:ViewModel() {
    private val _typeInterventions= mutableStateOf<List<TypeIntervention>>(emptyList())
    val typeInterventions:State<List<TypeIntervention>> = _typeInterventions

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init {
        getTypeInterventions()
    }

    fun getTypeInterventions(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val response = RetrofitInstance.api.getTypeInterventions()
                _typeInterventions.value = response
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la récupération des types d'intervention: ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }


    fun addTypeIntervention(typeIntervention: TypeIntervention){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("typeIntervention", "Le type d'intervention' : ${typeIntervention.toString()}")
                val response = RetrofitInstance.api.addTypeIntervention(typeIntervention)
                if(response.isSuccessful()){
                    getTypeInterventions()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'un type d'intervention ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'un type d'intervention : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }



}