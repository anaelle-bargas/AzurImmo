package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
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

    private fun getContrats(){
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


}