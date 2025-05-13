package bts.sio.azurimmo.model

import java.util.Date

data class Contrat(
    val id: Int?,
    val date_entree:String?=null,
    val archive:Boolean?=null,
    val date_sortie: String?=null,
    val montant_loyer: Float?=null,
    val montant_charges: Float?=null,
    val appartement: Appartement?=null,
    val locataire:Locataire?=null
)
