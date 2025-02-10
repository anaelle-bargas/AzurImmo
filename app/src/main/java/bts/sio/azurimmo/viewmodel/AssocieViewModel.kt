package bts.sio.azurimmo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Associe
import kotlinx.coroutines.launch

class AssocieViewModel : ViewModel(){

    private val _associesList = mutableStateOf<List<Associe>>(emptyList())
    val associeList:State<List<Associe>> = _associesList

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage:State<String?> = _errorMessage

    private val _isLoading = mutableStateOf(true)
    val isLoading : State<Boolean> = _isLoading

    init {
        getAssocies()
    }

    private fun getAssocies(){
        viewModelScope.launch{
            _isLoading.value = true
            try{
                val response = RetrofitInstance.api.getAssocies()
                _associesList.value = response
            } catch (e:Exception){
                _errorMessage.value = "Erreur dans la récupération des associés : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }

    }


}