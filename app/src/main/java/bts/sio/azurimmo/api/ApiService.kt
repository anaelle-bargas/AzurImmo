package bts.sio.azurimmo.api

import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import retrofit2.http.GET

interface ApiService{
    @GET("Api/batiments/")
    suspend fun getBatiments() : List<Batiment>

    @GET("api/appartements/")
    suspend fun  getAppartements() : List<Appartement>

    @GET("api/contrats/")
    suspend fun getContrats() : List<Contrat>

}