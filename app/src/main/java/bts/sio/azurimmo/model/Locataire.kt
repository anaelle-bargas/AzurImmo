package bts.sio.azurimmo.model

import java.util.Date

data class Locataire(
    val nom:String,
    val prenom:String,
    val date_naissance: Date,
    val lieu_naissance:String
)
