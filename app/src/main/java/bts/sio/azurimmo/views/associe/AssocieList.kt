package bts.sio.azurimmo.views.associe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodel.AssocieViewModel

@Composable
fun AssocieList (){
    val viewModel: AssocieViewModel = viewModel()
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value
    val associes = viewModel.associeList.value

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        when{
            isLoading==true ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            errorMessage != null -> {
                Text("Erreur : ${errorMessage}")
            }

            else -> {
                LazyColumn {
                    items(associes){ associe ->
                        AssocieCard(associe)
                    }
                }
            }
        }
    }
}