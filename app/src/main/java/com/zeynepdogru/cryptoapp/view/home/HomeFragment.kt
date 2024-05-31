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
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.InterstitialAd
import com.huawei.hms.ads.banner.BannerView
import android.util.Log

class HomeFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }
    lateinit var bannerView: BannerView
    private val TAG = "HUAWEI_ADS"
    companion object{
        var interstitialAd: InterstitialAd? = null
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


        loadBannerAd()
        loadInterstitialAd()

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
    private fun loadBannerAd(){

        // Obtain BannerView.
        bannerView = binding.hwBannerView
        bannerView.adListener = getAdListener()
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.adId = "testw6vs28auh3"
        bannerView.bannerAdSize = BannerAdSize.BANNER_SIZE_360_57
        // Set the refresh interval to 60 seconds.
        bannerView.setBannerRefresh(60)
        // Create an ad request to load an ad.
        val adParam = AdParam.Builder().build()
        bannerView.loadAd(adParam)

    }

    private fun loadInterstitialAd(){

        interstitialAd = InterstitialAd(this.context)
        interstitialAd!!.adListener = getAdListener()
        // "testb4znbuh3n2" is a dedicated test ad unit ID. Before releasing your app, replace the test ad unit ID with the formal one.
        interstitialAd!!.adId = "testb4znbuh3n2"
        // Load an interstitial ad.
        val adParam = AdParam.Builder().build()
        interstitialAd!!.loadAd(adParam)

    }

    private fun getAdListener(): AdListener{
        val adListener: AdListener = object : AdListener() {
            override fun onAdLoaded() {
                // Called when an ad is loaded successfully.
                Log.i(TAG,"onAdLoaded")
            }
            override fun onAdFailed(errorCode: Int) {
                // Called when an ad fails to be loaded.
                Log.i(TAG,"onAdFailed")
                Log.i(TAG,errorCode.toString())
            }
            override fun onAdOpened() {
                // Called when an ad is opened.
                Log.i(TAG,"onAdOpened")
            }
            override fun onAdClicked() {
                // Called when an ad is clicked.
                Log.i(TAG,"onAdClicked")
            }
            override fun onAdLeave() {
                // Called when an ad leaves an app.
                Log.i(TAG,"onAdLeave")
            }
            override fun onAdClosed() {
                // Called when an ad is closed.
                Log.i(TAG,"onAdClosed")
            }
        }
        return adListener
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerView.destroy()
    }
}