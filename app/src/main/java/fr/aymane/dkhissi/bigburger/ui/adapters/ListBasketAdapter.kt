package fr.aymane.dkhissi.bigburger.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.aymane.dkhissi.bigburger.R
import fr.aymane.dkhissi.bigburger.entities.Product


class ListBasketAdapter() : androidx.recyclerview.widget.ListAdapter<Product,
        ListBasketViewHolder>(Product.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListBasketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ListBasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListBasketViewHolder, position: Int) {
        val product = getItem(position)
        holder.txt_description.text = product.description
        holder.txt_title.text = product.title
        holder.txt_price.text = centToEuro(product.price)
        holder.img_product.load(product.thumbnail)
        holder.img_add_to_basket.visibility = View.GONE

    }
}

class ListBasketViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val txt_title: TextView = view.findViewById(R.id.txt_title_product)
    val txt_description: TextView = view.findViewById(R.id.txt_description_product)
    val txt_price: TextView = view.findViewById(R.id.txt_price_product)
    val img_product: ImageView = view.findViewById(R.id.img_product)
    val img_add_to_basket: ImageView = view.findViewById(R.id.img_add_to_basket)

}

private fun centToEuro(price: Double): String {
    return String.format("%.2f", price / 100) + " €"
}