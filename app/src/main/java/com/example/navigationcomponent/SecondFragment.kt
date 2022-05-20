package com.example.navigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.api.ApiService
import com.example.navigationcomponent.model.Stat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment() {

    private lateinit var apiService: ApiService

    private val args: SecondFragmentArgs by navArgs()

    private lateinit var countryTextView: TextView
    private lateinit var confirmedDataTextView: TextView
    private lateinit var deathsDataTextView: TextView
    private lateinit var recoveredDataTextView: TextView
    private lateinit var activeDataTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        apiService = MyApplication.instance.getApiService()!!

        countryTextView = view.findViewById(R.id.country_text_view)
        confirmedDataTextView = view.findViewById(R.id.confirmed_data_text_view)
        deathsDataTextView = view.findViewById(R.id.deaths_data_text_view)
        recoveredDataTextView = view.findViewById(R.id.recovered_data_text_view)
        activeDataTextView = view.findViewById(R.id.active_data_text_view)

        fetchStats()
    }

    private fun fetchStats() {
        val slug = args.slug

        apiService.getStats(slug).enqueue(object : Callback<ArrayList<Stat>> {
            override fun onFailure(call: Call<ArrayList<Stat>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<Stat>>, response: Response<ArrayList<Stat>>) {
                val stat = response.body()?.last()
                Log.d("Response", stat.toString())

                countryTextView.text = stat!!.Country
                confirmedDataTextView.text = stat!!.Confirmed.toString()
                deathsDataTextView.text = stat!!.Deaths.toString()
                recoveredDataTextView.text = stat!!.Recovered.toString()
                activeDataTextView.text = stat!!.Active.toString()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {

            }
    }
}