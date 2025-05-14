package bts.sio.azurimmo.views.contrat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Contrat

@Composable
fun ContratCard(contrat: Contrat){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text="Appartement n° "+contrat.appartement?.numero, style=MaterialTheme.typography.labelLarge)
            Text(text="Adresse : "+contrat.appartement?.batiment?.adresse, style = MaterialTheme.typography.bodyLarge)
            Text(text="Ville : "+contrat.appartement?.batiment?.ville, style = MaterialTheme.typography.bodyMedium)
            Text(text="Locataire : "+contrat.locataire?.nom+" "+contrat.locataire?.prenom, style=MaterialTheme.typography.bodyLarge)
            Text(text = "Du : "+contrat.date_entree+" au "+contrat.date_sortie, style = MaterialTheme.typography.bodyLarge)

        }
    }
}