package fr.aymane.dkhissi.bigburger.model.repositories

import fr.aymane.dkhissi.bigburger.model.api.BigBurgerApiService
import fr.aymane.dkhissi.bigburger.model.database.BigBurgerDao
import fr.aymane.dkhissi.bigburger.entities.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class BigBurgerRepository @Inject constructor(
    val bigBurgerApiService: BigBurgerApiService,
    val bigBurgerDao: BigBurgerDao
) {


    suspend fun getListProducts(): Response<List<Product>> {
        return bigBurgerApiService.getListProducts()
    }

    suspend fun updateBasketList(listProducts: List<Product>) {
        bigBurgerDao.insertAll(listProducts)
    }

    fun getBasketList(): Flow<List<Product>> {
        return bigBurgerDao.getBasketList()
    }

}