package bts.sio.azurimmo.views.appartement

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.InterventionViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppartementDel(
    onDelAppartement: () -> Unit,
    viewModel: AppartementViewModel = viewModel(),
    idBatiment: Int?=null
) {
    LaunchedEffect(Unit) {
        if(idBatiment==null){
            viewModel.getAppartements()
        }else{
            viewModel.getAppartementsByBatimentId(idBatiment)
        }
    }

    val appartements = viewModel.appartements.value
    var expanded by remember { mutableStateOf(false) }
    var appartementChoisi by remember { mutableStateOf(appartements.firstOrNull()) }

    val deleteText = when {
        !appartementChoisi?.numero.toString().isNullOrBlank() && !appartementChoisi?.batiment?.adresse.isNullOrBlank() && !appartementChoisi?.batiment?.ville.isNullOrBlank() -> "${appartementChoisi?.numero} ${appartementChoisi?.batiment?.adresse}, ${appartementChoisi?.batiment?.ville}"
        else -> "Sélectionner un appartement"
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Supprimer un appartement",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (deleteText != null) {
                    Text(
                        text = deleteText.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                appartements.forEach { appartement ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                "" + appartement?.numero + " " + appartement?.batiment?.adresse + ", " + appartement?.batiment?.ville,
                                style = MaterialTheme.typography.bodyLarge
                            )

                        },
                        onClick = {
                            appartementChoisi = appartement
                            expanded = false
                        }
                    )
                }
            }

            Button(
                onClick = {
                    if (appartementChoisi != null) {
                        appartementChoisi?.id?.let { viewModel.delAppartement(it) }
                        onDelAppartement()


                    }
                },
                enabled = appartementChoisi != null,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Supprimer")
            }
        }

}

