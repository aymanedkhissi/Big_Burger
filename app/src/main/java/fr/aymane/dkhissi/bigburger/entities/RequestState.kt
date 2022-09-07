package fr.aymane.dkhissi.bigburger.entities

sealed class RequestState {
    data class Success (val productsList : List<Product>?) : RequestState()
    object Failure : RequestState ()
    object Pending : RequestState()
}
