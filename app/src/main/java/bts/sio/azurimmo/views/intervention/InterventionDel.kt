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
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.viewmodel.InterventionViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InterventionDel(
    onDelIntervention: () -> Unit,
    viewModel: InterventionViewModel = viewModel(),
    idAppartement: Int?=null
) {
    LaunchedEffect(Unit) {
        if(idAppartement==null) {
            viewModel.getInterventions()
        }else{
            viewModel.getInterventionsByAppartementId(idAppartement)

        }
    }

    val interventions = viewModel.interventions.value
    var expanded by remember { mutableStateOf(false) }
    var interventionChoisi by remember { mutableStateOf(interventions.firstOrNull()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Supprimer un intervention",
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
            Text(
                text = interventionChoisi?.description ?: "Sélectionner un interventions",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            interventions.forEach { intervention ->
                DropdownMenuItem(
                    text = {
                        Text(intervention?.description?:"Description non connue", style = MaterialTheme.typography.bodyLarge)

                    },
                    onClick = {
                        interventionChoisi = intervention
                        expanded = false
                    }
                )
            }
        }

        Button(
            onClick = {
                if (interventionChoisi != null) {
                    interventionChoisi?.id?.let { viewModel.delIntervention(it) }
                    onDelIntervention()

                }
            },
            enabled = interventionChoisi != null,
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Supprimer")
        }
    }
}

