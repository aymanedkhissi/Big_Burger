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


class ListProductsAdapter() : androidx.recyclerview.widget.ListAdapter<Product,
        ListProductsViewHolder>(Product.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ListProductsViewHolder(view)
    }

    override fun onBindViewHolder (holder : ListProductsViewHolder, position : Int ) {
        val product = getItem(position)
        holder.txt_description.text = product.description
        holder.txt_title.text = product.title
        holder.txt_price.text = product.price.toString()
        holder.img_product.load(product.thumbnail)



    }


}

class ListProductsViewHolder (view : View) : RecyclerView.ViewHolder ( view ) {

    val txt_title: TextView = view.findViewById(R.id.txt_title_product )
    val txt_description : TextView = view.findViewById(R.id.txt_description_product )
    val txt_price : TextView = view.findViewById(R.id.txt_price_product )
    val img_product : ImageView = view.findViewById(R.id.img_product )

}