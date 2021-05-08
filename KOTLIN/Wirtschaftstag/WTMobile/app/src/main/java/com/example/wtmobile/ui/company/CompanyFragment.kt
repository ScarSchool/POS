package com.example.wtmobile.ui.company

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.Company
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class CompanyFragment : Fragment() {
    private lateinit var companiesAdapter: CompaniesAdapter;
    private lateinit var rootView: View;
    private lateinit var recyclerView: RecyclerView;

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_companies, container, false)

        val fab: FloatingActionButton = rootView.findViewById(R.id.fab_add_company)
        fab.setOnClickListener {
            val intent = Intent(
              context,
              CreateCompanies::class.java
            )
            ContextCompat.startActivity(rootView.context, intent, null)
        }

        val pullToRefresh: SwipeRefreshLayout = rootView.findViewById(R.id.pullToRefresh_companies)
        pullToRefresh.setOnRefreshListener {
            if (companiesAdapter.itemCount > 0)
                companiesAdapter.clear();
            fetchData()
            pullToRefresh.isRefreshing = false;
        }

        return rootView
    }


    override fun onStart() {
        Log.i("DEBUG", "onCreate Fragment Companys")
        super.onStart()

        recyclerView = rootView.findViewById(R.id.lv_companies)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        companiesAdapter = CompaniesAdapter(mutableListOf<Company>())
        recyclerView.adapter = companiesAdapter

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            makeList(API.getAPI().getCompanies())
        }
    }

    private fun makeList(companies: List<Company>) {
        Log.i("DEBUG", "Got Events of size: " + companies.size)

        companies.forEach {
            companiesAdapter.addCompany(it)
        }
        companiesAdapter.notifyDataSetChanged()
    }
}