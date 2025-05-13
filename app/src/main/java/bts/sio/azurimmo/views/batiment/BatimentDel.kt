package bts.sio.azurimmo.views.batiment

import android.annotation.SuppressLint
import android.util.Log
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
import bts.sio.azurimmo.viewmodel.BatimentViewModel
import kotlinx.coroutines.launch
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BatimentDel(
    onDelBatiment: () -> Unit,
    viewModel: BatimentViewModel = viewModel(),
    appartementViewModel: AppartementViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getBatiments()
    }

    val batiments = viewModel.batiments.value
    var expanded by remember { mutableStateOf(false) }
    var batimentChoisi by remember { mutableStateOf(batiments.firstOrNull()) }
    val appartementsLies = appartementViewModel.appartements.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(batimentChoisi) {
        batimentChoisi?.id?.let { id ->
            appartementViewModel.getAppartementsByBatimentId(id)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Supprimer un bâtiment",
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
                    text = batimentChoisi?.adresse ?: "Sélectionner un bâtiment",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                batiments.forEach { batiment ->
                    DropdownMenuItem(
                        text = {
                            Text("${batiment.adresse} - ${batiment.ville}", style = MaterialTheme.typography.bodyLarge)

                        },
                        onClick = {
                            batimentChoisi = batiment
                            expanded = false
                        }
                    )
                }
            }

            Button(
                onClick = {
                    if (batimentChoisi != null) {
                        if (appartementsLies.isEmpty()) {
                            batimentChoisi?.id?.let { viewModel.delBatiment(it) }
                            onDelBatiment()
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Le bâtiment n'est pas vide, veuillez d'abord supprimer ses appartements."
                                )
                            }
                        }
                    }
                },
                enabled = batimentChoisi != null,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Supprimer")
            }
        }
    }
}
