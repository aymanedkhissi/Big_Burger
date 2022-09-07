package fr.aymane.dkhissi.bigburger.repositories

import fr.aymane.dkhissi.bigburger.api.BigBurgerApiService
import fr.aymane.dkhissi.bigburger.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class BigBurgerRepository @Inject constructor(val bigBurgerApiService: BigBurgerApiService) {


    suspend fun getListProducts(): Response<List<Product>> {

        return bigBurgerApiService.getListProducts()
    }
}