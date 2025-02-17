package bts.sio.azurimmo.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppFooter (){
    Box(
        modifier = Modifier
            .background(color=MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
    ){
        Text("Hey", modifier = Modifier.padding(16.dp).align(Alignment.Center), color = MaterialTheme.colorScheme.surface, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
    }
}