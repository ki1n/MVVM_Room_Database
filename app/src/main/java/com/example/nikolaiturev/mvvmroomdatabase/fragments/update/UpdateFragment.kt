package com.example.nikolaiturev.mvvmroomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nikolaiturev.mvvmroomdatabase.R
import com.example.nikolaiturev.mvvmroomdatabase.databinding.FragmentUpdateBinding
import com.example.nikolaiturev.mvvmroomdatabase.model.User
import com.example.nikolaiturev.mvvmroomdatabase.viewmodel.UserViewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_update, container, false)
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        with(binding) {
            updateFirstNameEt.setText(args.currentUser.firstName)
            updateLastNameEt.setText(args.currentUser.lastName)
            updateAgeEt.setText(args.currentUser.age.toString())

            updateBt.setOnClickListener {
                updateItem()
            }
            // add menu
            setHasOptionsMenu(true)
        }

        return binding.root
    }

    private fun updateItem() {
        with(binding) {
            val firstName = updateFirstNameEt.text.toString()
            val lastName = updateLastNameEt.text.toString()
            val age = Integer.parseInt(updateAgeEt.text.toString())

            if (inputCheck(firstName, lastName, updateAgeEt.text)) {
                // Create User Object
                val newUser = User(args.currentUser.id, firstName, lastName, age)
                // Update Current User
                mUserViewModel.updateUser(newUser)
                Toast.makeText(requireContext(), "Update Successfully!", Toast.LENGTH_LONG).show()
                // Navigate Back
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill out all field.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        // Класс TextUtils относится к пакету android.text и содержит несколько полезных и удобных методов для работы с текстом.
        // Проверить, что строка пуста или null можно с помощью метода isEmpty(),
        // указав в параметре строковую переменную.
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment) // переход к фрагменту списка
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

}