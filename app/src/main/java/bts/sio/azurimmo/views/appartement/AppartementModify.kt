package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.BatimentViewModel

@Composable
fun AppartementModify (
    idAppartement: Int,
    viewModel: AppartementViewModel = viewModel(),
    viewModelBat: BatimentViewModel = viewModel(),
    onModifyAppartement : () -> Unit
){
    var appartement = viewModel.appartement.value
    var batiments = viewModelBat.batiments.value
    var numero by remember {  mutableStateOf(appartement?.numero ?: "") }
    var surface by remember{  mutableStateOf(appartement?.surface ?: "") }
    var nbPO by remember{  mutableStateOf(appartement?.nbPiecesOriginal ?: "") }
    var description by remember{  mutableStateOf(appartement?.description ?: "") }
    var batimentChoisi by remember { mutableStateOf(batiments.firstOrNull()) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(appartement) {
        appartement?.let {
            numero = it.numero.toString()
            surface = it.surface.toString()
            nbPO = it.nbPiecesOriginal.toString()
            description = it.description.toString()
            batimentChoisi = it.batiment
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getAppartementById(idAppartement)
        viewModelBat.getBatiments()
    }
    Column (
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Row {
            Text(text="Numéro :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value=numero.toString(),
                onValueChange = {numero=it},
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Text(text="Surface :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value= surface.toString(),
                onValueChange = {surface=it},
                modifier=Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Text(text="Nombre de pièces :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value= nbPO.toString(),
                onValueChange = {nbPO=it},
                modifier=Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Text(text="Description :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value= description.toString(),
                onValueChange = {description=it},
                modifier=Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Text(text="Bâtiment :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            Text(
                text = batimentChoisi?.adresse ?: "null",
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(8.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                batiments.forEach { batiment ->
                    DropdownMenuItem(
                        text = {
                            Text("${batiment.ville} - ${batiment.adresse}", style = MaterialTheme.typography.bodyLarge)
                        },
                        onClick = {
                            batimentChoisi = batiment
                            expanded = false
                        }
                    )
                }
            }
        }


        Spacer(modifier=Modifier.height(16.dp))
        Button(
            onClick = {
                if(numero!=null && surface!=null && nbPO!=null && description !=null && batimentChoisi!=null){
                    if (appartement != null) {
                        appartement.numero = numero.toString().toInt()
                        appartement.surface = surface.toString().toDouble()
                        appartement.nbPiecesOriginal = nbPO.toString().toInt()
                        appartement.description = description.toString()
                        appartement.batiment = batimentChoisi
                        viewModel.modifyAppartement(appartement)
                        onModifyAppartement()
                    }
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = numero!=null && surface!=null
        ) {
            Text("Modifier l'appartement")
        }
    }

}