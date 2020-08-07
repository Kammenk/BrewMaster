package com.example.brewmaster.ui.fragments.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewmaster.R
import com.example.brewmaster.adapter.BreweryAdapterAdapter
import com.example.brewmaster.model.brewerymodel.Data
import com.example.brewmaster.ui.WrapperActivity
import com.example.brewmaster.viewmodel.BeerViewModel

class SearchBreweryFragment : Fragment() {

    lateinit var viewModel: BeerViewModel
    lateinit var adapter: BreweryAdapterAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var noBrewTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_brewery, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0F
        (requireActivity() as AppCompatActivity).supportActionBar?.show()


        noBrewTxt = view.findViewById(R.id.noResultBrewTxt)
        viewModel = (activity as WrapperActivity).viewModel
        setHasOptionsMenu(true)
        recyclerView = view.findViewById(R.id.searchBrewRecyclerView)
        setupRecyclerView()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu,menu)

        val searchItem: MenuItem = menu.findItem(R.id.searchItem)
        if (searchItem != null) {
            var searchView = MenuItemCompat.getActionView(searchItem) as SearchView


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.getBreweries(query)
                        viewModel.breweries.observe(viewLifecycleOwner, Observer { response ->
                            if(response.body()?.totalResults!! > 0){
                                hideMissingBeerMessage()
                                response.body()?.data?.let {
                                   adapter.differ.submitList(it)

                                }
                            } else {
                                adapter.differ.submitList(emptyList<Data>())
                                showMissingBeerMessage()
                            }
                        })
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun hideMissingBeerMessage() {
        noBrewTxt.visibility = View.INVISIBLE
    }

    private fun showMissingBeerMessage() {
        noBrewTxt.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        adapter = BreweryAdapterAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
}