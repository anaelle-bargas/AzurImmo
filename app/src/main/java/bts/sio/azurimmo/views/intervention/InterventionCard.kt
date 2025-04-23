package bts.sio.azurimmo.views.intervention

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
import bts.sio.azurimmo.model.Intervention
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun InterventionCard(intervention: Intervention){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Description : "+intervention.description, style = MaterialTheme.typography.labelLarge)
            Text(text="Appartement "+intervention.appartement?.numero, style=MaterialTheme.typography.bodyMedium)
            Text(text="Adresse : "+intervention.appartement?.batiment?.adresse, style = MaterialTheme.typography.bodyMedium)
            Text(text="Ville : "+intervention.appartement?.batiment?.ville, style = MaterialTheme.typography.bodyMedium)
            Text(text="Date : "+ intervention.date, style = MaterialTheme.typography.bodyMedium)
            Text(text="Intervenant : "+intervention.intervenant?.nom, style = MaterialTheme.typography.bodyMedium)
            Text(text="Type : "+intervention.typeIntervention?.libelle, style=MaterialTheme.typography.bodyMedium)

        }
    }
}