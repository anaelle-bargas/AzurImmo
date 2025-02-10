package bts.sio.azurimmo.api

import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Associe
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.Locataire
import retrofit2.http.GET
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

    @GET("api/appartements/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId:Int) : List<Appartement>



}