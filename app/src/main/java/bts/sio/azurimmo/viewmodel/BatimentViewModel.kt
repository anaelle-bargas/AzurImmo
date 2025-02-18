package bts.sio.azurimmo.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import bts.sio.azurimmo.model.Batiment
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch

class BatimentViewModel : ViewModel() {
    private val _batiments = mutableStateOf<List<Batiment>>(emptyList())
    val batiments: State<List<Batiment>> = _batiments

    private val _batiment = mutableStateOf<Batiment?>(null)
    val batiment : State<Batiment?> = _batiment

    private val _isLoading = mutableStateOf(false)
    val isLoading : State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage : State<String?> = _errorMessage

    init {
        getBatiments()
    }

    fun getBatiments(){
        viewModelScope.launch{
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getBatiments()
                _batiments.value = response
            }catch (e:Exception){
                _errorMessage.value = "Erreur : ${e.message}"
            }finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }


    fun getBatiment(id:Int){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                val response= RetrofitInstance.api.getBatimentById(id)
                _batiment.value = response
            }catch (e:Exception){
                _errorMessage.value = "Erreur dans la récupératioin du batiment ${id}"
            }finally{
                _isLoading.value=false
            }
        }

    }

    fun addBatiment(batiment:Batiment){
        viewModelScope.launch {
            _isLoading.value=true
            try{
                Log.d("batimentadd", "Le batiment : ${batiment.toString()}")
                val response = RetrofitInstance.api.addBatiment(batiment)
                Log.d("bla", "La")
                if(response.isSuccessful()){
                    getBatiments()
                }
                else{
                    _errorMessage.value="Erreur dans l'ajout d'un batiment ${response.message()}"
                }
            }catch (e:Exception){
                _errorMessage.value="Erreur dans l'ajout d'un batiment : ${e.message}"
            }finally {
                _isLoading.value=false
            }
        }
    }
}