package com.zeynepdogru.cryptoapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeynepdogru.cryptoapp.databinding.FragmentHomeBinding
import com.zeynepdogru.cryptoapp.R
import com.zeynepdogru.cryptoapp.adapter.CryptoAdapter
import com.zeynepdogru.cryptoapp.util.ApplicationViewModelFactory
import com.zeynepdogru.cryptoapp.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    //private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding
//    private var cryptoAdapter= CryptoAdapter(arrayListOf()) { position ->
//        //findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
//        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container,false)
        //viewModel= ViewModelProvider(this)[MainViewModel::class.java]

        binding.productRV.layoutManager= LinearLayoutManager(requireContext())
        viewModel.getDataFromAPI()
        setObserves()

        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setObserves(){
        viewModel.productData.observe(viewLifecycleOwner) { list ->
            val cryptoAdapter= CryptoAdapter(arrayListOf()) { position ->
         //findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
           findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(list[position]))
   }
            binding.productRV.adapter=cryptoAdapter
            cryptoAdapter.updateList(list)
           viewModel.insertAll(list) //bu çalıştırıldığında database e verilerin gelmesi gerekiyor

        }

        viewModel.productError.observe(viewLifecycleOwner) { error ->
            if (error) {
                binding.errorTV.visibility = View.VISIBLE

            } else
                binding.errorTV.visibility = View.GONE

        }

    }
}