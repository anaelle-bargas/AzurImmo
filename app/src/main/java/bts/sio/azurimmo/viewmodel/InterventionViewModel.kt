package bts.sio.azurimmo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Intervention
import kotlinx.coroutines.launch

class InterventionViewModel:ViewModel() {
    private val _interventions= mutableStateOf<List<Intervention>>(emptyList())
    val interventions:State<List<Intervention>> = _interventions

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init {
        getIntervention()
    }

    private fun getIntervention(){
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


}