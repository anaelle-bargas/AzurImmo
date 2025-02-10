package bts.sio.azurimmo.views.contrat

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.views.batiment.BatimentCard
import bts.sio.azurimmo.viewsmodel.batiment.ContratViewModel

@Composable
fun ContratList(){
    val viewModel:ContratViewModel= viewModel()
    val contrats=viewModel.contrats.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    Box(modifier = Modifier.fillMaxSize()){

        when{
            isLoading->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage!=null->{
                Text(text=errorMessage?:"Erreur inconnue", modifier = Modifier.align(Alignment.Center).padding(16.dp), color = MaterialTheme.colorScheme.error)
            }else->{
                LazyColumn {
                    items(contrats){ contrat ->
                        ContratCard(contrat=contrat)
                    }
                }
            }

        }
    }

}