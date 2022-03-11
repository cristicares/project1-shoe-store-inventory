package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.ListItemBinding
import com.udacity.shoestore.databinding.ListingFragmentBinding
import com.udacity.shoestore.models.Shoe

class ListingFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: ListingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.listing_fragment,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            if (!shoesList.isNullOrEmpty()) {
                populateShoesListView(shoesList)
            }
        }

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentDestinationToDetailFragment())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun populateShoesListView(shoesList: MutableList<Shoe>) {
        shoesList.forEach { shoe ->
            DataBindingUtil.inflate<ListItemBinding>(
                layoutInflater,
                R.layout.list_item,
                binding.shoesList,
                true
            ).apply {
                this.shoe = shoe
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}