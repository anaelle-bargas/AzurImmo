package bts.sio.azurimmo.model

data class Appartement(
    val id:Int,
    val numero:Int? = null,
    val surface: Double? = null,
    val nbPiecesOriginal : Int? = null,
    val description: String? = null,
    val batiment: Batiment? = null
)
