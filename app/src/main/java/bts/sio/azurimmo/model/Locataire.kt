package bts.sio.azurimmo.model

import java.time.LocalDate
import java.util.Date

data class Locataire(
    val id:Int,
    val nom:String,
    val prenom:String,
    val date_naissance: String,
    val lieu_naissance:String,
    val email:String,
    val telephone:String
)
