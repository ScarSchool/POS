package com.example.wtmobile.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.User
import com.example.wtmobile.ui.user.UsersAdapter
import kotlinx.coroutines.launch


class UserFragment : Fragment() {
    private lateinit var usersAdapter: UsersAdapter;
    private lateinit var rootView: View;
    private lateinit var recyclerView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_users, container, false)

        val pullToRefresh: SwipeRefreshLayout = rootView.findViewById(R.id.pullToRefresh_users)
        pullToRefresh.setOnRefreshListener {
            if (usersAdapter.itemCount > 0)
                usersAdapter.clear();
            fetchData()
            pullToRefresh.isRefreshing = false;
        }

        return rootView
    }


    override fun onStart() {
        Log.i("DEBUG", "onCreate Fragment UsersFragment")
        super.onStart()

        recyclerView = rootView.findViewById(R.id.lv_users)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        usersAdapter = UsersAdapter(mutableListOf<User>())
        recyclerView.adapter = usersAdapter

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            makeList(API.getAPI().getUsers())
        }
    }

    private fun makeList(users: List<User>) {
        Log.i("DEBUG", "Got Users of size: " + users.size)

        users.forEach {
            usersAdapter.addUser(it)
        }
        usersAdapter.notifyDataSetChanged()
    }

}