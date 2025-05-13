package bts.sio.azurimmo.model

import java.util.Date

data class Intervention(
    val id : Int,
    val description:String?=null,
    val archive:Boolean?=null,
    val date:String?=null,
    val appartement: Appartement?=null,
    val intervenant: Intervenant?=null,
    val typeIntervention: TypeIntervention?=null
)
