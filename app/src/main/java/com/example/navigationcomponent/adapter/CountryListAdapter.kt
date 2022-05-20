package com.example.navigationcomponent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.FirstFragmentDirections
import com.example.navigationcomponent.R
import com.example.navigationcomponent.model.Country

class CountryListAdapter(var countries: ArrayList<Country>, val context: Context):
    RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.text_item, parent, false)

        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return  countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countries.get(position)

        holder.countryTextView.text = country.Country
        holder.slug = country.Slug
    }

    fun update(data: ArrayList<Country>) {
        countries = data
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {
        var countryTextView: TextView = itemView.findViewById(R.id.text_view)
        var slug: String? = null

        init {
            itemView.setOnClickListener {
                val action = FirstFragmentDirections.navigateToSecondFragment(slug!!)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }
}