package com.example.navigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.adapter.CountryListAdapter
import com.example.navigationcomponent.api.ApiService
import com.example.navigationcomponent.model.Country
import com.example.navigationcomponent.viewModel.FirstViewModel
import com.example.navigationcomponent.viewModel.MainViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var apiService: ApiService

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryListAdapter: CountryListAdapter

    private lateinit var  viewModel: FirstViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        apiService = MyApplication.instance.getApiService()!!
        recyclerView = view.findViewById(R.id.recycler_view)

        initRecyclerView()
        bindViewModel()
    }

    private fun bindViewModel() {
        initViewModel()
        observeData()
        if (viewModel.countries.value == null) {
            viewModel.fetchCountries()
        }
    }

    private fun initViewModel() {
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(FirstViewModel::class.java)
    }

    private fun observeData() {
        viewModel.countries.observe(requireActivity(), Observer {
            countryListAdapter.update(it)
        })
    }

    private fun initRecyclerView() {
        val countries = ArrayList<Country>()
        countryListAdapter = CountryListAdapter(countries, requireContext())
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = countryListAdapter
    }

    private fun fetchCountries() {
        apiService.getCountries().enqueue(object : Callback<ArrayList<Country>> {
            override fun onFailure(call: Call<ArrayList<Country>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Country>>,
                response: Response<ArrayList<Country>>
            ) {
                Log.d("Response", response.body()?.size.toString())
                countryListAdapter.update(response.body()!!)
            }
        })
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {

            }
    }
}