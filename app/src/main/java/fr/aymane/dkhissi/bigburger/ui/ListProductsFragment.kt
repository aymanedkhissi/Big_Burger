package fr.aymane.dkhissi.bigburger.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.aymane.dkhissi.bigburger.R
import fr.aymane.dkhissi.bigburger.databinding.FragmentListProductsBinding
import fr.aymane.dkhissi.bigburger.entities.Product
import fr.aymane.dkhissi.bigburger.entities.RequestState
import fr.aymane.dkhissi.bigburger.ui.adapters.ListProductsAdapter
import fr.aymane.dkhissi.bigburger.viewmodels.ListProductsViewModel

@AndroidEntryPoint
class ListProductsFragment : Fragment() {

    private lateinit var binding: FragmentListProductsBinding
    private val viewModel: ListProductsViewModel by viewModels()
    private val listProductsAdapter = ListProductsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListProductsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listProductsAdapter
        }

        viewModel.getListProducts()
        viewModel.requestState.observe(viewLifecycleOwner) { requestState ->
            when (requestState) {
                is RequestState.Pending -> showProgressBar()
                is RequestState.Success -> showProductsList(requestState.productsList)
                is RequestState.Failure -> showErrorMessage()
            }
        }

        binding.refresh.setOnRefreshListener {
            viewModel.getListProducts()
            binding.refresh.isRefreshing=false
        }
    }

    private fun showProductsList(productsList : List<Product>?) {
        listProductsAdapter.submitList(productsList)
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showErrorMessage() {
        Toast.makeText(activity, getString(R.string.error_message), Toast.LENGTH_LONG).show()
        binding.progressBar.visibility = View.GONE
    }

}