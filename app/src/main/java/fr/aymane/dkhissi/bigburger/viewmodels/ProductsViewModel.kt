package fr.aymane.dkhissi.bigburger.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.aymane.dkhissi.bigburger.entities.Product
import fr.aymane.dkhissi.bigburger.entities.RequestState
import fr.aymane.dkhissi.bigburger.model.repositories.BigBurgerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val bigBurgerRepository: BigBurgerRepository) :
    ViewModel() {

    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState>
        get() = _requestState

    fun getListProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            _requestState.postValue(RequestState.Pending)

            if (bigBurgerRepository.getListProducts().isSuccessful) {
                _requestState.postValue(
                    RequestState.Success(
                        bigBurgerRepository.getListProducts().body()
                    )
                )
            } else {
                _requestState.postValue(RequestState.Failure)
            }

        }
    }

    fun updateBasketList(listProducts : List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            bigBurgerRepository.updateBasketList(listProducts)
        }
    }
}