package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.DetailFragmentBinding
import com.udacity.shoestore.models.Shoe

class DetailFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: DetailFragmentBinding
    private val defaultShoe: Shoe = Shoe("", 0.0, "", null, null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        binding.shoe = defaultShoe
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentDestinationToListingFragmentDestination())
        }

        binding.saveButton.setOnClickListener {
            saveAction()
        }

        return binding.root
    }

    private fun saveAction() {
        binding.apply {

            if (shoe?.name?.isNotBlank() == true && shoe?.company?.isNotBlank() == true
                && !shoe?.size?.equals(0.0)!!
            ) {
                viewModel.addNewShoe(shoe)
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentDestinationToListingFragmentDestination())
            } else {
                Toast.makeText(context, "Complete fields correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }
}