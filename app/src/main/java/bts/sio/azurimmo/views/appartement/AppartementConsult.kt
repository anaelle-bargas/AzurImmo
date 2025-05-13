package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.ContratViewModel
import bts.sio.azurimmo.viewmodel.InterventionViewModel
import bts.sio.azurimmo.views.appartement.AppartementCard
import bts.sio.azurimmo.views.contrat.ContratCard
import bts.sio.azurimmo.views.intervention.InterventionCard


@Composable
fun AppartementConsult (
    viewModel: AppartementViewModel = viewModel(),
    viewModelIntervention : InterventionViewModel = viewModel(),
    viewModelContrat : ContratViewModel = viewModel(),
    appartementId: Int? = null,
    onModifyAppartementClick : (Int?) -> Unit,
    onDeleteInterventionClick : (Int?) -> Unit,
    onAddInterventionClick : (Int?) -> Unit,
    onDeleteContratClick : (Int?) -> Unit,
    onAddContratClick : (Int?) -> Unit
){
    val interventions=viewModelIntervention.interventions.value
    val contrats=viewModelContrat.contrats.value
    val appartement = viewModel.appartement.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value
    var listInterventions by remember { mutableStateOf(false) }
    var listContrats by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(appartementId!=null){
            viewModel.getAppartementById(appartementId)
            viewModelIntervention.getInterventionsByAppartementId(appartementId)
            viewModelContrat.getContratsByAppartementId(appartementId)
        }

    }
    Box(modifier =Modifier.fillMaxSize()) {
        when {
            isLoading == true -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erreur inconnue",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                Column (

                    modifier = Modifier.fillMaxSize()
                ){

                    LazyColumn {
                        if (appartement != null) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.1f
                                            )
                                        )
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Informations sur l'appartement",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "Numéro : ${appartement.numero}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Surface : ${appartement.surface}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Nombre de pièces : ${appartement.nbPiecesOriginal}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Description : ${appartement.description}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Adresse : ${appartement.batiment?.adresse}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Ville : ${appartement.batiment?.ville}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                    }

                    Button(
                        onClick = {
                            listInterventions=true
                            listContrats=false
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
                    ) {
                        Text("Liste des interventions")
                    }
                    Button(
                        onClick = {
                            listContrats=true
                            listInterventions=false
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Liste des contrats")
                    }
                    LazyColumn {

                        if (interventions.isNotEmpty() && listInterventions == true) {
                            item {
                                Text(
                                    text = "Liste des interventions",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .padding(vertical = 1.dp)
                                )
                            }
                            items(interventions) { intervention ->
                                InterventionCard(intervention = intervention)
                            }
                        } else if(listInterventions == true){
                            item {
                                Text(
                                    text = "Il n'y a pas d'interventions dans cet appartement",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .padding(vertical = 1.dp)
                                        .fillMaxWidth()
                                )
                            }
                        } else if(contrats.isNotEmpty() && listContrats==true){
                            item {
                                Text(
                                    text = "Liste des contrats",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .padding(vertical = 1.dp)
                                )
                            }
                            items(contrats) { contrat ->
                                ContratCard(contrat = contrat)
                            }
                        }else if(listContrats == true){
                            item {
                                Text(
                                    text = "Il n'y a pas de contrats dans cet appartement",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .padding(vertical = 1.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                FloatingActionButton(
                    onClick = {onModifyAppartementClick(appartementId)},
                    modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = (if(listContrats==true || listInterventions==true) 160.dp else 16.dp), horizontal = 16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    content = { Icon(Icons.Default.Edit, contentDescription = "Modifier un appartement")}
                )

                if(listContrats==true || listInterventions==true){
                    FloatingActionButton(
                        onClick = { if(listContrats==true) onDeleteContratClick(appartementId) else onDeleteInterventionClick(appartementId)},
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(vertical = 85.dp, horizontal = 16.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        content = {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Supprimer un ${if(listContrats==true) "contrat" else "intervention"}"
                            )
                        }
                    )

                    FloatingActionButton(
                        onClick ={ if(listContrats==true) onAddContratClick(appartementId) else onAddInterventionClick(appartementId)},
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd),
                        content = {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Ajouter un ${if(listContrats==true) "contrat" else "intervention"}"
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                }


            }
        }
    }
}