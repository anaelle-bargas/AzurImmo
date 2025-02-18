package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.BatimentViewModel

@Composable
fun AppartementAdd (
    onAddAppartement: ()->Unit,
    viewModel:AppartementViewModel = viewModel(),
    viewModelBatiment: BatimentViewModel = viewModel(),
    idBatiment: Int? = null
){
    var numero by remember { mutableStateOf("") }
    var nb_pieces_original by remember { mutableStateOf("") }
    var surface by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        TextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Numéro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nb_pieces_original,
            onValueChange = { nb_pieces_original = it },
            label = { Text("Nombre de pièces") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = surface,
            onValueChange = { surface = it },
            label = { Text("Surface") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (idBatiment == null) {
            val batiments = listOf(viewModelBatiment.getBatiments())
            var expanded by remember { mutableStateOf(false) }
            var batimentChoisi by remember { mutableStateOf(batiments[0]) }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                batiments.forEach { batiment ->
                    DropdownMenuItem(
                        text = { Text("${batiment.toString()}") },
                        onClick = {
                            batimentChoisi = batiment
                            expanded = false
                        }
                    )
                }
            }
        }
        if (idBatiment != null) {
            Button(
                onClick = {
                    if (numero.isNotEmpty() && surface.isNotEmpty() && nb_pieces_original.isNotEmpty()) {
                        val appartement = Appartement(
                            id = 0,
                            description = description,
                            nbPiecesOriginal = nb_pieces_original.toInt(),
                            numero = numero.toInt(),
                            surface = surface.toDouble(),
                            batiment = Batiment(id = idBatiment)
                        )
                        viewModel.addAppartement(appartement)
                        onAddAppartement()
//
                    }
                },
                enabled = numero.isNotEmpty() && surface.isNotEmpty() && nb_pieces_original.isNotEmpty(),
                modifier = Modifier.align(Alignment.End),
            ) {
                Text("Ajouter un appartement")
            }
        }
    }
}