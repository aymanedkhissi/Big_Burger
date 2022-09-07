package fr.aymane.dkhissi.bigburger.model.api

import fr.aymane.dkhissi.bigburger.entities.Product
import retrofit2.Response
import retrofit2.http.GET

interface BigBurgerApiService {

    @GET("bigburger")
    suspend fun getListProducts() : Response<List<Product>>
}