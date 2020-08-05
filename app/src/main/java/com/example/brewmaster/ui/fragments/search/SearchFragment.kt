package com.example.brewmaster.ui.fragments.search

import android.os.Bundle
import android.view.*
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewmaster.R
import com.example.brewmaster.adapter.MyAdapter
import com.example.brewmaster.model.Beer
import com.example.brewmaster.model.Data
import com.example.brewmaster.ui.WrapperActivity
import com.example.brewmaster.viewmodel.BeerViewModel
import retrofit2.Call
import javax.security.auth.callback.Callback


class SearchFragment : Fragment() {

    lateinit var viewModel: BeerViewModel
    lateinit var adapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var noBeetxt: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0F
        (requireActivity() as AppCompatActivity).supportActionBar?.show()


        noBeetxt = view.findViewById(R.id.noResultTxt)
        viewModel = (activity as WrapperActivity).viewModel
        setHasOptionsMenu(true)
        recyclerView = view.findViewById(R.id.searchRecyclerView)
        setupRecyclerView()

        //viewModel.getAllBeers()
        viewModel.allBeers.observe(viewLifecycleOwner, Observer { response ->
            response.body()?.data?.let {
                adapter.differ.submitList(it)
                adapter.notifyDataSetChanged()
            }


        })

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
                        viewModel.getBeers(query)
                        viewModel.beers.observe(viewLifecycleOwner, Observer { response ->
                            println("response code ${response.code()}")
                            if(response.body()?.totalResults!! > 0){
                                hideMissingBeerMessage()
                                response.body()?.data?.let {
                                    adapter.differ.submitList(it)
                                    println("list of data ${it[0].name}")
                                    println("adapter size ${adapter.differ.currentList.size}")
                                    //adapter.notifyDataSetChanged()
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
        noBeetxt.visibility = View.INVISIBLE
    }

    private fun showMissingBeerMessage() {
        noBeetxt.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        adapter = MyAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
}