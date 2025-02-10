package bts.sio.azurimmo.views.locataire

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.batiment.LocataireViewModel

@Composable
fun LocataireList() {
    val viewModel:LocataireViewModel= viewModel()
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
                LazyColumn {
                    items(locataires){ locataire->
                        LocataireCard(locataire)
                    }
                }
            }

        }
    }
}