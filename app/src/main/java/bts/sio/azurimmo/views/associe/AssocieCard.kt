package bts.sio.azurimmo.views.associe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Associe


@Composable
fun AssocieCard (associe : Associe){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ){
            Text(text = associe.nom?:"pas de nom", style = MaterialTheme.typography.bodyLarge)
            Text(text = associe.prenom?:"pas de prenom", style = MaterialTheme.typography.bodyLarge)
            Text(text = associe.date_naissance.toString()?:"pas de date", style = MaterialTheme.typography.bodyMedium)
        }
    }
}