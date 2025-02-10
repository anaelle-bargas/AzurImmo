package bts.sio.azurimmo.model

import java.util.Date

data class Intervention(
    val description:String,
    val date:Date,
    val appartement: Appartement,
    val intervenant: Intervenant,
    val typeIntervention: TypeIntervention
)
