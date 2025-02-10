package bts.sio.azurimmo.views.locataire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Locataire

@Composable
fun LocataireCard(locataire:Locataire) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column (modifier = Modifier.padding(8.dp)){
            Text(text = locataire.nom+locataire.prenom, style = MaterialTheme.typography.bodyLarge)
            Text(text = locataire.date_naissance.toString(), style=MaterialTheme.typography.bodyMedium)
            Text(text = locataire.lieu_naissance, style=MaterialTheme.typography.bodySmall)
        }
    }
}