package fr.aymane.dkhissi.bigburger.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.aymane.dkhissi.bigburger.entities.RequestState
import fr.aymane.dkhissi.bigburger.repositories.BigBurgerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProductsViewModel @Inject constructor(val bigBurgerRepository: BigBurgerRepository) :
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
}