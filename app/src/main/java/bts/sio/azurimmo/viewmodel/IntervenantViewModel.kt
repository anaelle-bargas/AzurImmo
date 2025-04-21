package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Intervenant
import kotlinx.coroutines.launch

class IntervenantViewModel:ViewModel() {
    private val _intervenants= mutableStateOf<List<Intervenant>>(emptyList())
    val intervenants:State<List<Intervenant>> = _intervenants

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    init {
        getIntervenants()
    }

    fun getIntervenants(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val response = RetrofitInstance.api.getIntervenants()
                _intervenants.value = response
            }catch (e:Exception){
                _errorMessage.value="Erreur dans la récupération des intervenants: ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }


    fun addIntervenant(intervenant: Intervenant){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("intervention", "L'intervenant' : ${intervenant.toString()}")
                val response = RetrofitInstance.api.addIntervenant(intervenant)
                if(response.isSuccessful()){
                    getIntervenants()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'un intervenant ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'un intervenant : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }



}