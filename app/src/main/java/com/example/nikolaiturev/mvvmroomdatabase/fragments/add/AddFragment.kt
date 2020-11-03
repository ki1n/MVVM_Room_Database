package com.example.nikolaiturev.mvvmroomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nikolaiturev.mvvmroomdatabase.R
import com.example.nikolaiturev.mvvmroomdatabase.databinding.FragmentAddBinding
import com.example.nikolaiturev.mvvmroomdatabase.model.User
import com.example.nikolaiturev.mvvmroomdatabase.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_add, container, false)
        binding = FragmentAddBinding.inflate(layoutInflater)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addBt.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if (inputCheck(firstName, lastName, age)) {
            // create User object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // add data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment) // переход на дугой фрагмент
        } else {
            Toast.makeText(requireContext(), "Please fill out all field", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        // Класс TextUtils относится к пакету android.text и содержит несколько полезных и удобных методов для работы с текстом.
        // Проверить, что строка пуста или null можно с помощью метода isEmpty(),
        // указав в параметре строковую переменную.
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }
}