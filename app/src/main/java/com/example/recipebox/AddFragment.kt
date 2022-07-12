package com.example.recipebox

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.recipebox.database.AppDatabase
import com.example.recipebox.database.Recipes
import com.example.recipebox.databinding.FragmentAddBinding

class AddFragment: Fragment(R.layout.fragment_add) {

    private var title: String = ""

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
    }

    private fun addToBase(
        nameGet: String,
        descriptionGet: String,
        ingredientsGet: String,
        urlGet: String
    ) {
        val newRecipes = Recipes(
            name = nameGet,
            description = descriptionGet,
            ingredients = ingredientsGet,
            url = urlGet
        )
        context?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, "database-name")
                .allowMainThreadQueries()
                .build()
                .recipeDao()
        }?.insertRecipe(newRecipes)
    }
}