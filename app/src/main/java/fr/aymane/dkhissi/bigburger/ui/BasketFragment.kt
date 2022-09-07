package fr.aymane.dkhissi.bigburger.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.aymane.dkhissi.bigburger.databinding.FragmentBasketBinding
import fr.aymane.dkhissi.bigburger.entities.Product
import fr.aymane.dkhissi.bigburger.ui.adapters.ListBasketAdapter
import fr.aymane.dkhissi.bigburger.viewmodels.BasketViewModel

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private val listBasketAdapter = ListBasketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.basketRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listBasketAdapter
        }

        viewModel.getBasketList().observe(viewLifecycleOwner) {
            listBasketAdapter.submitList(it)
            updateBtnPay(it)
        }


    }

    private fun updateBtnPay(basketList: List<Product>?) {
        var totalPrice = 0.0
        if (!basketList.isNullOrEmpty()) {
            for (element in basketList) {
                totalPrice += element.price
            }
        }
        binding.btnPay.text = "Pay "+String.format("%.2f", totalPrice / 100) + " €"
    }
}