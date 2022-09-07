package fr.aymane.dkhissi.bigburger.entities

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ref")
    val ref: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
) {

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.ref == newItem.ref
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.description == newItem.description
                    && oldItem.price == newItem.price
                    && oldItem.thumbnail == newItem.thumbnail
                    && oldItem.ref == newItem.ref

        }
    }
}