package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodel.AppartementViewModel
import bts.sio.azurimmo.viewmodel.BatimentViewModel


@Composable
fun AppartementList (
    viewModel:AppartementViewModel= viewModel(),
    batimentId: Int? = null,
    onAddAppartementClick: (Int?) -> Unit,
    onDeleteAppartementClick : (Int?) -> Unit,
    onAppartementClick:(Int?) -> Unit
){
    val viewModelBat:BatimentViewModel= viewModel()
    val batiment = viewModelBat.batiment.value
    val appartements=viewModel.appartements.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
       viewModel.getAppartements()
    }

    Box(modifier =Modifier.fillMaxSize()){
        when{
            isLoading == true ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null->{
                Text(text = errorMessage?:"Erreur inconnue", modifier = Modifier.align(Alignment.Center).padding(16.dp), color = MaterialTheme.colorScheme.error)
            }
            else-> {

                LazyColumn {


                    if (appartements.isNotEmpty()) {
                        item {
                            Text(
                                text = "Liste des appartements",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .padding(vertical = 1.dp)
                            )
                        }
                        items(appartements) { appartement ->
                            AppartementCard(appartement = appartement, onClick= { onAppartementClick(appartement.id)} )
                        }
                    } else {
                        item {
                            Text(
                                text = "Il n'y a pas d'appartements dans ce bâtiment",
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

                FloatingActionButton(
                    onClick = {onDeleteAppartementClick(batimentId)},
                    modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 85.dp, horizontal = 16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    content = { Icon(Icons.Default.Delete, contentDescription = "Supprimer un appartement")}
                )

                FloatingActionButton(
                    onClick = { onAddAppartementClick(batimentId) },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    content = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Ajouter un appartement"
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                )


            }
        }
    }
}