package com.example.wtmobile.ui.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wtmobile.MainActivity
import com.example.wtmobile.ui.participation.Participations
import com.example.wtmobile.R
import com.example.wtmobile.data.models.Event

//import java.time.format.DateTimeFormatter

class EventsAdapter(
    private val events: MutableList<Event>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun deleteEvent(index: Int) {
        events.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clear() {
        events.clear();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvLabel).text = event.label
            findViewById<TextView>(R.id.tvDate).text =
                event.date.toString()                       // TODO: Make this variable a date again! .format(DateTimeFormatter.ofPattern("dd LLLL yyyy"))
            findViewById<TextView>(R.id.tvPrice).text = event.defaultPrice.toString()
            findViewById<TextView>(R.id.tvOrganizer).text = "${event.organiser?.name}"

            setOnClickListener({
                val intent = Intent(
                    rootView.context, Participations::class.java
                );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                MainActivity.globalEvent = event

                ContextCompat.startActivity(rootView.context, intent, null);
            })
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}