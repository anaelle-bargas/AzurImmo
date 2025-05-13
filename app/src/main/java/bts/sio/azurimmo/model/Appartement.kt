package bts.sio.azurimmo.model

data class Appartement(
    val id:Int,
    var numero:Int? = null,
    var surface: Double? = null,
    var nbPiecesOriginal : Int? = null,
    var description: String? = null,
    var batiment: Batiment? = null
)
