package bts.sio.azurimmo.views.batiment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodel.BatimentViewModel

@Composable
fun BatimentModify (
    idBatiment: Int,
    viewModel: BatimentViewModel = viewModel(),
    onModifyBatiment : () -> Unit
){
    var batiment = viewModel.batiment.value
    var adresse by remember {  mutableStateOf(batiment?.adresse ?: "") }
    var ville by remember{  mutableStateOf(batiment?.ville ?: "") }

    LaunchedEffect(batiment) {
        batiment?.let {
            adresse = it.adresse.toString()
            ville = it.ville.toString()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getBatiment(idBatiment)
    }
    Column (
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Row {
            Text(text="Adresse :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value=adresse,
                onValueChange = {adresse=it},
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Text(text="Ville :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            TextField(
                value= ville,
                onValueChange = {ville=it},
                modifier=Modifier.fillMaxWidth()
            )
        }


        Spacer(modifier=Modifier.height(16.dp))
        Button(
            onClick = {
                if(adresse.isNotBlank() && ville.isNotBlank()){
                    if (batiment != null) {
                        batiment.adresse = adresse
                        batiment.ville = ville
                        viewModel.modifyBatiment(batiment)
                        onModifyBatiment()
                    }
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = adresse.isNotBlank() && ville.isNotBlank()
        ) {
            Text("Modifier le bâtiment")
        }
    }

}