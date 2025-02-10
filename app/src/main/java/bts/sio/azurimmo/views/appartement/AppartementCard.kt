package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Appartement
@Composable
fun AppartementCard (appartement: Appartement){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row {
                Text(text="Numéro :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text=appartement.numero.toString(), style=MaterialTheme.typography.bodyLarge)
            }
            Row{
                Text(text = "Description : ",style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text =appartement.description,style = MaterialTheme.typography.bodyLarge)
            }
            Row{
                Text(text="Surface :", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text=appartement.surface.toString(), style=MaterialTheme.typography.bodyLarge)
            }
        }
    }
}