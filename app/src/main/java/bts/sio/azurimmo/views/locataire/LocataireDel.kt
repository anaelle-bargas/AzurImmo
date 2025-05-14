package bts.sio.azurimmo.views.locataire

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import bts.sio.azurimmo.viewmodel.LocataireViewModel
import kotlinx.coroutines.launch
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocataireDel(
    onDelLocataire: () -> Unit,
    viewModel: LocataireViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getLocataires()
    }

    val locataires = viewModel.locataires.value
    var expanded by remember { mutableStateOf(false) }
    var locataireChoisi by remember { mutableStateOf(locataires.firstOrNull()) }

    val deleteText = when {
        !locataireChoisi?.nom.isNullOrBlank() && !locataireChoisi?.prenom.isNullOrBlank() -> "${locataireChoisi?.nom} ${locataireChoisi?.prenom}"
        else -> "Sélectionner un locataire"
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Supprimer un locataire",
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
                    text = deleteText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                locataires.forEach { locataire ->
                    DropdownMenuItem(
                        text = {
                            Text("${locataire.nom} ${locataire.prenom}", style = MaterialTheme.typography.bodyLarge)

                        },
                        onClick = {
                            locataireChoisi = locataire
                            expanded = false
                        }
                    )
                }
            }

            Button(
                onClick = {
                    if (locataireChoisi != null) {
                        locataireChoisi?.id?.let { viewModel.delLocataire(it) }
                        onDelLocataire()

                    }
                },
                enabled = locataireChoisi != null,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Supprimer")
            }
        }
    }

