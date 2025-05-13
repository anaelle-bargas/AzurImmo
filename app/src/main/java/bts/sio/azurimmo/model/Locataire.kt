package bts.sio.azurimmo.model

import java.time.LocalDate
import java.util.Date

data class Locataire(
    val id:Int,
    val nom:String?=null,
    val archive:Boolean?=null,
    val prenom:String?=null,
    val date_naissance: String?=null,
    val lieu_naissance:String?=null,
    val email:String?=null,
    val telephone:String?=null
)
