package bts.sio.azurimmo.views.contrat

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.viewmodel.InterventionViewModel
import bts.sio.azurimmo.views.intervention.InterventionCard

@Composable
fun InterventionList(
    onAddInterventionClick : ()->Unit,
    onDeleteInterventionClick : ()->Unit
){
    val viewModel: InterventionViewModel = viewModel()
    val interventions=viewModel.interventions.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.getInterventions()
    }
    Box(modifier = Modifier.fillMaxSize()){

        when{
            isLoading->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage!=null->{
                Text(text=errorMessage?:"Erreur inconnue", modifier = Modifier.align(Alignment.Center).padding(16.dp), color = MaterialTheme.colorScheme.error)
            }else->{

                LazyColumn {
                    item {
                        Text("Liste des interventions", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), modifier = Modifier.padding(16.dp).fillMaxWidth(), textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primary)
                    }
                    items(interventions){ intervention ->
                        InterventionCard(intervention=intervention)
                    }
                }
                FloatingActionButton(
                    onClick = {onDeleteInterventionClick()},
                    modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 85.dp, horizontal = 16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    content = { Icon(Icons.Default.Delete, contentDescription = "Supprimer un appartement")}
                )
                FloatingActionButton(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {onAddInterventionClick()},
                    content = { Icon(Icons.Default.Add, contentDescription = "Ajouter une intervention")}
                )
            }
        }
    }

}