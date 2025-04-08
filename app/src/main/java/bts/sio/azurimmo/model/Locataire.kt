package bts.sio.azurimmo.model

import java.time.LocalDate

data class Locataire(
    val id:Int,
    val nom:String,
    val prenom:String,
    val date_naissance: LocalDate,
    val lieu_naissance:String,
    val email:String,
    val telephone:String
)
