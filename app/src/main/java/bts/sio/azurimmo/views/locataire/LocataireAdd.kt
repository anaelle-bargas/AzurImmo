package bts.sio.azurimmo.views.locataire

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.viewmodel.LocataireViewModel
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun LocataireAdd(
    onAddLocataire : ()->Unit,
    viewModel: LocataireViewModel = viewModel()
) {
    var date_naissance by remember { mutableStateOf("") }
    var lieu_naissance by remember { mutableStateOf("") }
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        TextField(
            value = nom,
            onValueChange = {nom=it},
            label = {Text("Nom")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value=prenom,
            onValueChange = {prenom=it},
            label = { Text("Prenom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value=date_naissance,
            onValueChange={date_naissance=it},
            label = {Text("Date de naissance")},
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value=lieu_naissance,
            onValueChange={lieu_naissance=it},
            label = {Text("Lieu de naissance")},
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value=email,
            onValueChange={email=it},
            label = {Text("Email")},
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value=telephone,
            onValueChange={telephone=it},
            label = {Text("Telephone")},
            modifier=Modifier.fillMaxWidth()
        )
        Button(
            onClick = {

                if(date_naissance!=null && lieu_naissance.isNotEmpty() && nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && telephone.isNotEmpty()){
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val parsedDate: java.util.Date = sdf.parse(date_naissance)!!
                    val sqlDate = java.sql.Date(parsedDate.time)
                    val locataire = Locataire(id=0, nom=nom, prenom = prenom, date_naissance = sqlDate.toString(), lieu_naissance=lieu_naissance, email=email, telephone=telephone, archive=false)
                    viewModel.addLocataire(locataire)
                    onAddLocataire()
                }
            },
            enabled = date_naissance.isNotEmpty() && lieu_naissance.isNotEmpty() && nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && telephone.isNotEmpty(),
            modifier=Modifier.align(Alignment.End),

        ) {
            Text("Ajouter un locataire")
        }
    }

}