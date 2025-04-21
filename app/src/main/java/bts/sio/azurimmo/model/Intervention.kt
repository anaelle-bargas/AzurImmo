package bts.sio.azurimmo.model

import java.util.Date

data class Intervention(
    val id : Int,
    val description:String,
    val date:String,
    val appartement: Appartement,
    val intervenant: Intervenant,
    val typeIntervention: TypeIntervention
)
