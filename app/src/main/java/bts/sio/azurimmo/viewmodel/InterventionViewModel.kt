package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.Locataire
import kotlinx.coroutines.launch

class InterventionViewModel:ViewModel() {
    private val _interventions= mutableStateOf<List<Intervention>>(emptyList())
    val interventions:State<List<Intervention>> = _interventions

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init {
        getInterventions()
    }

    fun getInterventions(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val response = RetrofitInstance.api.getInterventions()
                _interventions.value = response
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la récupération des intervention: ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }


    fun addIntervention(intervention: Intervention){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("intervention", "L'intervention' : ${intervention.toString()}")
                val response = RetrofitInstance.api.addIntervention(intervention)
                if(response.isSuccessful()){
                    getInterventions()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'une intervention ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'une intervention : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun getInterventionsByAppartementId(appartementId : Int){
        viewModelScope.launch{
            _isLoading.value = true
            try{
                val response= RetrofitInstance.api.getInterventionsByAppartementId(appartementId)
                _interventions.value = response
            }catch(e:Exception){
                _errorMessage.value="Erreur dans la récupération des intervenants de l'appartement ${appartementId} : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }

    fun delIntervention(interventionId: Int){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("interventionId", "Le interventionId : ${interventionId}")
                val response = RetrofitInstance.api.delIntervention(interventionId)
                if(response.isSuccessful()){
                    getInterventions()
                } else{
                    _errorMessage.value="Erreur dans la suppression d'un intervention ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la suppression d'un intervention : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }


}