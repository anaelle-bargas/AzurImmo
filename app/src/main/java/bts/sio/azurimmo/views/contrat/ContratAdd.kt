package bts.sio.azurimmo.views.contrat

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
import bts.sio.azurimmo.viewmodel.ContratViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.LocataireViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ContratAdd(
    onAddContrat: () -> Unit,
    viewModel: ContratViewModel = viewModel(),
    appartementViewModel: AppartementViewModel = viewModel(),
    locataireViewModel: LocataireViewModel = viewModel(),
    idAppartement: Int? = null
) {
    var date_entree by remember { mutableStateOf("") }
    var date_sortie by remember { mutableStateOf("") }
    var montant_loyer by remember { mutableStateOf("") }
    var montant_charges by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        appartementViewModel.getAppartements()
        locataireViewModel.getLocataires()
    }

    val appartements = appartementViewModel.appartements.value
    val locataires = locataireViewModel.locataires.value
    

    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    var appartementChoisi by remember { mutableStateOf(appartements.firstOrNull()) }
    var locataireChoisi by remember { mutableStateOf(locataires.firstOrNull()) }

    val addText = when {
        !locataireChoisi?.nom.isNullOrBlank() && !locataireChoisi?.prenom.isNullOrBlank() -> "${locataireChoisi?.nom}, ${locataireChoisi?.prenom}"
        !locataireChoisi?.nom.isNullOrBlank() -> locataireChoisi?.prenom
        else -> "Sélectionner un locataire"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = date_entree,
            onValueChange = { date_entree = it },
            label = { Text("Date de début de contrat") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = date_sortie,
            onValueChange = { date_sortie = it },
            label = { Text("Date de fin de contrat") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = montant_loyer,
            onValueChange = { montant_loyer = it },
            label = { Text("Montant du loyer") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = montant_charges,
            onValueChange = { montant_charges = it },
            label = { Text("Montant des charges") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (idAppartement == null) {

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
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (addText != null) {
            Text(
                text =  addText,
                modifier = Modifier
                    .clickable { expanded2 = true }
                    .padding(8.dp)
            )
        }
        DropdownMenu(
            expanded = expanded2,
            onDismissRequest = { expanded2 = false }
        ) {
            locataires.forEach { locataire ->
                DropdownMenuItem(
                    text = {
                        Text("${locataire.nom} ${locataire.prenom}", style = MaterialTheme.typography.bodyLarge)
                    },
                    onClick = {
                        locataireChoisi = locataire
                        expanded2 = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {

                if (date_entree.isNotEmpty() && montant_charges.isNotEmpty() && montant_loyer.isNotEmpty() && (appartementChoisi != null || idAppartement!=null) && locataireChoisi!=null && date_sortie.isNotEmpty()) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val parsedDateEntree: Date = sdf.parse(date_entree)!!
                    val sqlDateEntree = java.sql.Date(parsedDateEntree.time)
                    val parsedDateSortie: Date = sdf.parse(date_sortie)!!
                    val sqlDateSortie = java.sql.Date(parsedDateSortie.time)
                    val contrat = Contrat(
                        id = 0,
                        date_entree = sqlDateEntree.toString(),
                        date_sortie = sqlDateSortie.toString(),
                        montant_loyer = montant_loyer.toFloat(),
                        montant_charges = montant_charges.toFloat(),
                        appartement = Appartement(id = (if(idAppartement!=null) idAppartement else appartementChoisi?.id) ),
                        locataire = Locataire(id = locataireChoisi!!.id),
                        archive=false
                    )
                    viewModel.addContrat(contrat)
                    onAddContrat()
                    Log.d("Contrat", "Le contrat add' : ${contrat.toString()}")
                }

            },
            enabled = date_sortie.isNotEmpty() && date_entree.isNotEmpty() && montant_charges.isNotEmpty() && montant_loyer.isNotEmpty() && (appartementChoisi != null || idAppartement!=null) && locataireChoisi != null,
            modifier = Modifier.align(Alignment.End),

            ) {
            Text("Ajouter une contrat")
        }
    }

}