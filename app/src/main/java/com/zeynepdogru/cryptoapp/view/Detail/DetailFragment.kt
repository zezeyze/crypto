package com.zeynepdogru.cryptoapp.view.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zeynepdogru.cryptoapp.R
import com.zeynepdogru.cryptoapp.databinding.FragmentDetailBinding
import com.zeynepdogru.cryptoapp.util.ApplicationViewModelFactory
import com.zeynepdogru.cryptoapp.viewmodel.MainViewModel


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding:FragmentDetailBinding
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container, false)
        viewModel.findByName(args.crypto.name)
        initUI()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initUI(){
        //val crypto =args.crypto
        viewModel.crypto.observe(viewLifecycleOwner){crypto ->
            with(binding){
                textView5.text=crypto.name
                textView6.text=crypto.percent_change_1h
                textView7.text=crypto.percent_change_24h
                textView8.text=crypto.percent_change_7d

            }
        }

    }

}