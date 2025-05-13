package bts.sio.azurimmo.model

data class Batiment(
    val id: Int?,
    var adresse:String? = null,
    var ville:String? = null,
    var archive:Boolean?=null,
    val appartements:List<Appartement>?=null
)
