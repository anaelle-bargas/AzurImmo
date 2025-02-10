package bts.sio.azurimmo.model

import java.util.Date

data class Contrat(
    val date_entree:Date,
    val date_sortie: Date,
    val montant_loyer: Float,
    val montant_charges: Float,
    val appartement: Appartement,
    val associe:Associe,
    val locataire:Locataire
)
