package bts.sio.azurimmo.views.batiment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodel.BatimentViewModel

@Composable
fun BatimentAdd(
    onBatimentAdd : ()->Unit,
    viewModel : BatimentViewModel = viewModel()
){
    var adresse by remember { mutableStateOf("") }
    var ville by remember{ mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        TextField(
            value=adresse,
            onValueChange = {adresse=it},
            label = { Text("adresse")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value=ville,
            onValueChange = {ville=it},
            label = { Text("ville")},
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))
        Button(
            onClick = {
                if(adresse.isNotBlank() && ville.isNotBlank()){
                    val batiment = Batiment(id = 0, adresse =adresse, ville =ville, archive=false)
                    viewModel.addBatiment(batiment)
                    onBatimentAdd()
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = adresse.isNotBlank() && ville.isNotBlank()
        ) {
            Text("Ajouter un bâtiment")
        }
    }

}