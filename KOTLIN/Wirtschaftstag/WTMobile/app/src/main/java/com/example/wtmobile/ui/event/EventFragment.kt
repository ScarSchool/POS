package com.example.wtmobile.ui.event

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
import com.example.wtmobile.data.models.Event
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class EventFragment : Fragment() {
    private lateinit var eventsAdapter: EventsAdapter;
    private lateinit var rootView: View;
    private lateinit var recyclerView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_events, container, false)

        val fab: FloatingActionButton = rootView.findViewById(R.id.fab_add_event)
        fab.setOnClickListener {
            val intent = Intent(
                context,
                CreateEvents::class.java
            )
            ContextCompat.startActivity(rootView.context, intent, null)
        }

        val pullToRefresh: SwipeRefreshLayout = rootView.findViewById(R.id.pullToRefresh_events)
        pullToRefresh.setOnRefreshListener {
            if (eventsAdapter.itemCount > 0)
                eventsAdapter.clear();
            fetchData()
            pullToRefresh.isRefreshing = false;
        }

        return rootView
    }


    override fun onStart() {
        Log.i("DEBUG", "onCreate Fragment EventsFragment")
        super.onStart()

        recyclerView = rootView.findViewById(R.id.lv_events)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        eventsAdapter = EventsAdapter(mutableListOf<Event>())
        recyclerView.adapter = eventsAdapter

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            makeList(API.getAPI().getEvents())
        }
    }

    private fun makeList(events: List<Event>) {
        Log.i("DEBUG", "Got Events of size: " + events.size)

        events.forEach {
            eventsAdapter.addEvent(it)
        }
        eventsAdapter.notifyDataSetChanged()
    }

}