package fr.aymane.dkhissi.bigburger.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.aymane.dkhissi.bigburger.entities.Product
import fr.aymane.dkhissi.bigburger.model.repositories.BigBurgerRepository
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(val bigBurgerRepository: BigBurgerRepository) : ViewModel() {

    fun getBasketList() : LiveData<List<Product>> {
        return bigBurgerRepository.getBasketList().asLiveData()
    }
}