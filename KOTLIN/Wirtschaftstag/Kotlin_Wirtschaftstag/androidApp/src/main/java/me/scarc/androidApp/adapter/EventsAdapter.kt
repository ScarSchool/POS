package me.scarc.androidApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.scarc.androidApp.R
import me.scarc.androidApp.data.Event
import java.time.format.DateTimeFormatter

class EventsAdapter(
    private val events : MutableList<Event>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addEvent(event : Event) {
        events.add(event)
        notifyItemInserted(events.size - 1)
    }

    fun deleteEvent(index : Int) {
        events.removeAt(index)
        notifyItemRemoved(index)
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
            findViewById<TextView>(R.id.tvDate).text = event.date.toString()
            findViewById<TextView>(R.id.tvPrice).text = event.defaultPrice.toString()
            findViewById<TextView>(R.id.tvOrganizer).text = "${event.organizer.name}"
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}