package bts.sio.azurimmo.api

import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Associe
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Intervenant
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.model.TypeIntervention
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService{
    @GET("api/batiments/")
    suspend fun getBatiments() : List<Batiment>

    @GET("api/appartements/")
    suspend fun  getAppartements() : List<Appartement>

    @GET("api/contrats/")
    suspend fun getContrats() : List<Contrat>


    @GET("api/associes/")
    suspend fun getAssocies() : List<Associe>

    @GET("api/locataires/")
    suspend fun getLocataires() : List<Locataire>

    @GET("api/interventions/")
    suspend fun getInterventions() : List<Intervention>

    @GET("api/type_interventions/")
    suspend fun getTypeInterventions() : List<TypeIntervention>

    @GET("api/intervenants/")
    suspend fun getIntervenants() : List<Intervenant>

    @GET("api/appartements/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId:Int) : List<Appartement>

    @GET("api/interventions/appartement/{appartementId}")
    suspend fun getInterventionsByAppartementId(@Path("appartementId") appartementId:Int) : List<Intervention>

    @GET("api/contrats/appartement/{appartementId}")
    suspend fun getContratsByAppartementId(@Path("appartementId") appartementId:Int) : List<Contrat>


    @GET("api/batiments/id/{id}")
    suspend fun getBatimentById(@Path("id") id : Int) : Batiment

    @GET("api/appartements/id/{id}")
    suspend fun getAppartementById(@Path("id") id : Int) : Appartement


    @POST("api/batiments/")
    suspend fun addBatiment(@Body batiment : Batiment) : Response<Batiment>

    @POST("api/appartements/")
    suspend fun addAppartement(@Body appartement : Appartement) : Response<Appartement>

    @POST("api/locataires/")
    suspend fun addLocataire(@Body locataire : Locataire) : Response<Locataire>

    @POST("api/interventions/")
    suspend fun addIntervention(@Body intervention: Intervention) : Response<Intervention>

    @POST("api/intervenants/")
    suspend fun addIntervenant(@Body intervenant: Intervenant) : Response<Intervenant>

    @POST("api/contrats/")
    suspend fun addContrat(@Body contrat: Contrat) : Response<Contrat>


    @POST("api/type_interventions/")
    suspend fun addTypeIntervention(@Body typeIntervention: TypeIntervention) : Response<TypeIntervention>

    @PUT("api/batiments/archiver/{batimentId}")
    suspend fun delBatiment(@Path("batimentId") batimentId:Int) : Response<Batiment>

    @PUT("api/locataires/archiver/{locataireId}")
    suspend fun delLocataire(@Path("locataireId") locataireId:Int) : Response<Locataire>

    @PUT("api/appartements/archiver/{appartementId}")
    suspend fun delAppartement(@Path("appartementId") appartementId:Int) : Response<Appartement>

    @PUT("api/interventions/archiver/{interventionId}")
    suspend fun delIntervention(@Path("interventionId") interventionId:Int) : Response<Intervention>

    @PUT("api/contrats/archiver/{contratId}")
    suspend fun delContrat(@Path("contratId") contratId:Int) : Response<Contrat>


    @PUT("api/batiments/")
    suspend fun modifyBatiment(@Body batiment : Batiment) : Response<Batiment>

    @PUT("api/appartements/")
    suspend fun modifyAppartement(@Body appartement : Appartement) : Response<Appartement>

}