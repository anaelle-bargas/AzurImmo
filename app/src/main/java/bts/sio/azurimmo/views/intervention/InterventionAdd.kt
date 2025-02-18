package bts.sio.azurimmo.views.intervention

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodel.InterventionViewModel

@Composable
fun InterventionAdd (
    viewModel: InterventionViewModel = viewModel()
){
    val description by remember { mutableStateOf("") }
    val date by remember { mutableStateOf("") }
}