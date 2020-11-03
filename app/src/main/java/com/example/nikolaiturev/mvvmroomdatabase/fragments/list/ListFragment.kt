package com.example.nikolaiturev.mvvmroomdatabase.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikolaiturev.mvvmroomdatabase.R
import com.example.nikolaiturev.mvvmroomdatabase.viewmodel.UserViewModel
import com.example.nikolaiturev.mvvmroomdatabase.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return  inflater.inflate(R.layout.fragment_list, container, false)
        binding = FragmentListBinding.inflate(layoutInflater)

        // RecyclerView
        val adapterUsers = ListUsersAdapter()
        val recyclerView = binding.recyclerViewUsers
        recyclerView.adapter = adapterUsers
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // viewLifecycleOwner вместо this тк это жз цикл фрагмента
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { users ->
                adapterUsers.setData(users)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment) // переход на другой фрагметн
        }
        return binding.root
    }
}