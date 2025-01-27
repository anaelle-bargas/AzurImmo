package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import bts.sio.azurimmo.model.Batiment
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch

class BatimentViewModel : ViewModel() {
    private val _batiments = mutableStateOf(emptyList<Batiment>())
    val batiments: State<List<Batiment>> = _batiments

    init {
        getBatiments()
    }

    private fun getBatiments(){
        viewModelScope.launch{
            _batiments.value=listOf(
                    Batiment(1, "123 Rue Principale", "Nice"),
                    Batiment(2, "456 Avenue des Champs", "Marseille"),
                    Batiment(3, "789 Boulevard Haussmann", "Marseille")
            )
        }
    }
}