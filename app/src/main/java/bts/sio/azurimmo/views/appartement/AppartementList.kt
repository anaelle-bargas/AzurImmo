package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.views.batiment.BatimentCard
import bts.sio.azurimmo.viewsmodel.batiment.AppartementViewModel


@Composable
fun AppartementList (){
    val appartementViewModel: AppartementViewModel= viewModel()
    val appartements = appartementViewModel.appartements.value
    LazyColumn (modifier = Modifier.padding(8.dp)){
        items(appartements) { appartement ->
            AppartementCard(appartement = appartement)
        }
    }
}