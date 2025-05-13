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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.viewmodel.ContratViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContratDel(
    onDelContrat: () -> Unit,
    viewModel: ContratViewModel = viewModel(),
    idAppartement: Int?=null
) {
    LaunchedEffect(Unit) {
        if(idAppartement==null) {
            viewModel.getContrats()
        }else{
            viewModel.getContratsByAppartementId(idAppartement)

        }
    }

    val contrats = viewModel.contrats.value
    var expanded by remember { mutableStateOf(false) }
    var contratChoisi by remember { mutableStateOf(contrats.firstOrNull()) }

    val deleteText = when {
        !contratChoisi?.date_entree.isNullOrBlank() && !contratChoisi?.date_sortie.isNullOrBlank() -> "${contratChoisi?.appartement?.batiment?.adresse}, ${contratChoisi?.appartement?.batiment?.ville} : ${contratChoisi?.date_entree} - ${contratChoisi?.date_sortie}"
        !contratChoisi?.date_entree.isNullOrBlank() -> contratChoisi?.date_entree
        else -> "Sélectionner un contrat"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Supprimer un contrat",
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
                    text = deleteText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            contrats.forEach { contrat ->
                DropdownMenuItem(
                    text = {
                        Text(contrat?.appartement?.batiment?.adresse + contrat?.appartement?.batiment?.ville +": "+contrat?.date_entree+"-"+contrat?.date_sortie, style = MaterialTheme.typography.bodyLarge)

                    },
                    onClick = {
                        contratChoisi = contrat
                        expanded = false
                    }
                )
            }
        }

        Button(
            onClick = {
                if (contratChoisi != null) {
                    contratChoisi?.id?.let { viewModel.delContrat(it) }
                    onDelContrat()

                }
            },
            enabled = contratChoisi != null,
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Supprimer")
        }
    }
}

