package bts.sio.azurimmo.views.locataire

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodel.LocataireViewModel

@Composable
fun LocataireList(
    viewModel: LocataireViewModel = viewModel(),
    onAddLocataireClick : ()->Unit
) {
    val locataires = viewModel.locataires.value
    val errorMessage = viewModel.errorMessage.value
    val isLoading = viewModel.isLoading.value


    Box(modifier = Modifier.fillMaxSize()){

        when {
            isLoading == true -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null ->{
                Text(text = "Erreur : ${errorMessage}", modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
            }

            else->{
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                        onClick = onAddLocataireClick,
                        shape = RoundedCornerShape(16.dp)

                    ) {
                        Text("Ajouter un locataire")
                    }

                    Text("Liste des locataires", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))

                    LazyColumn {
                        items(locataires) { locataire ->
                            LocataireCard(locataire)
                        }
                    }
                }
            }

        }
    }
}