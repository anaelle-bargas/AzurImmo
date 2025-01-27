package bts.sio.azurimmo.views.batiment

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BatimentList(){
    val viewModel: BatimentViewModel=viewModel()
    val batiments = viewModel.batiments.value
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(batiments){ batiment ->
            BatimentCard(batiment=batiment)
        }
    }
}