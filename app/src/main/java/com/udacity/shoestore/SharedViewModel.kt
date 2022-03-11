package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    private var _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: MutableLiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        initShoeList()
    }

    private fun initShoeList() {
        val images: ArrayList<Int> = ArrayList()
        images.add(R.drawable.sneakers)

        val exampleShoe1 = Shoe("ZX 2K Flux White", 6.5, "Adidas",
            "Modern Urban sneakers", images)
        val exampleShoe2 = Shoe("Air Zoom Pegasus", 7.5, "Nike",
            "Unisex sneakers for running", images)
        val exampleShoe3 = Shoe("Predator Edge.3", 7.0, "Adidas",
            "Football sneakers for artificial grass", images)

        _shoesList.value = mutableListOf(exampleShoe1, exampleShoe2, exampleShoe3, exampleShoe1)
    }

    fun addNewShoe(shoe: Shoe?){
        if (null != shoe){
            _shoesList.value?.add(shoe)
        }
    }
}