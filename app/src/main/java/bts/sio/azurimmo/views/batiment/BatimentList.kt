package bts.sio.azurimmo.views.batiment

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.viewmodel.BatimentViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BatimentList(
    viewModel: BatimentViewModel =viewModel(),
    onBatimentClick : (Int?)->Unit,
    onAddBatimentClick: ()->Unit
){
    val batiments = viewModel.batiments.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.getBatiments()
    }
    Box(modifier=Modifier.fillMaxSize()){
        when{
            isLoading -> {
                CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                Text(
                    text=errorMessage ?:"Erreur Inconnue",
                    modifier= Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color=MaterialTheme.colorScheme.error
                )
            }
            else -> {
                Column  {

                    LazyColumn {
                        item{

                            Text(
                                text = "Liste des bâtiments",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )
                        }
                        items(batiments){ batiment ->
                            BatimentCard(batiment=batiment, onClick= { onBatimentClick(batiment.id) })
                        }
                    }
                }

                FloatingActionButton(
                    onClick = {onAddBatimentClick()},
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    content = { Icon(Icons.Default.Add, contentDescription = "Ajouter un batiment")}
                )
            }
        }
    }
}