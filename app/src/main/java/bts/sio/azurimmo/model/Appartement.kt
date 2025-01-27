package bts.sio.azurimmo.model

data class Appartement(
    val id:Int,
    val numero:Int,
    val surface: Float,
    val description: String,
    val batiment_id: Batiment
)
