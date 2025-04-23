package bts.sio.azurimmo.views.intervention

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodel.InterventionViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Intervenant
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.TypeIntervention
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.IntervenantViewModel
import bts.sio.azurimmo.viewmodel.TypeInterventionViewModel
import java.text.SimpleDateFormat

@Composable
fun InterventionAdd(
    onAddIntervention: () -> Unit,
    viewModel: InterventionViewModel = viewModel(),
    appartementViewModel: AppartementViewModel = viewModel(),
    typeInterventionViewModel: TypeInterventionViewModel = viewModel(),
    intervenantViewModel: IntervenantViewModel = viewModel()
) {
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        appartementViewModel.getAppartements()
        intervenantViewModel.getIntervenants()
        typeInterventionViewModel.getTypeInterventions()
    }

    val appartements = appartementViewModel.appartements.value
    val intervenants = intervenantViewModel.intervenants.value
    val typeInterventions = typeInterventionViewModel.typeInterventions.value
    Log.d("typeInterventions", "Les type d'intervention dans intervention add' : ${typeInterventions.toString()}")
    Log.d("typeInterventions", "Les type d'intervention dans intervention add' : ${appartements.toString()}")


    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var expanded3 by remember { mutableStateOf(false) }

    var appartementChoisi by remember { mutableStateOf(appartements.firstOrNull()) }
    var intervenantChoisi by remember { mutableStateOf(intervenants.firstOrNull()) }
    var typeInterventionChoisi by remember { mutableStateOf(typeInterventions.firstOrNull()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = appartementChoisi?.numero?.toString() ?: "Sélectionner un appartement",
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            appartements.forEach { appartement ->
                DropdownMenuItem(
                    text = {
                        Text(
                            "${appartement.batiment?.adresse} - ${appartement.batiment?.ville} - ${appartement.numero}",
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = intervenantChoisi?.nom ?: "Sélectionner un intervenant",
            modifier = Modifier
                .clickable { expanded2 = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded2,
            onDismissRequest = { expanded2 = false }
        ) {
            intervenants.forEach { intervenant ->
                DropdownMenuItem(
                    text = {
                        Text("${intervenant.nom}", style = MaterialTheme.typography.bodyLarge)
                    },
                    onClick = {
                        intervenantChoisi = intervenant
                        expanded2 = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = typeInterventionChoisi?.libelle ?: "Sélectionner un type d'intervention",
            modifier = Modifier
                .clickable { expanded3 = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded3,
            onDismissRequest = { expanded3 = false }
        ) {
            typeInterventions.forEach { typeIntervention ->
                DropdownMenuItem(
                    text = {
                        Text(
                            "${typeIntervention.libelle}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        typeInterventionChoisi = typeIntervention
                        expanded3 = false
                    }
                )
            }
        }
        Button(
            onClick = {

                if (description.isNotEmpty() && appartementChoisi != null && typeInterventionChoisi!= null && intervenantChoisi!=null && date.isNotEmpty()) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val formattedDate = sdf.format(java.util.Date(date))
                    val intervention = Intervention(
                        id = 0,
                        description = description,
                        date = formattedDate,
                        appartement = Appartement(id = appartementChoisi!!.id),
                        intervenant = Intervenant(id = intervenantChoisi!!.id),
                        typeIntervention = TypeIntervention(id = typeInterventionChoisi!!.id)
                    )
                    viewModel.addIntervention(intervention)
                    onAddIntervention()
                    Log.d("Intervention", "Les type d'intervention dans intervention add' : ${intervention.toString()}")
                }

            },
            enabled = date.isNotEmpty() && description.isNotEmpty() && appartementChoisi != null && intervenantChoisi != null && typeInterventionChoisi != null,
            modifier = Modifier.align(Alignment.End),

            ) {
            Text("Ajouter une intervention")
        }
    }

}