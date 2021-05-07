package com.example.wtmobile.ui.participation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtmobile.R
import com.example.wtmobile.data.models.Participation

//import java.time.format.DateTimeFormatter

class ParticipationAdapter(
    private val participations: MutableList<Participation>
) : RecyclerView.Adapter<ParticipationAdapter.ParticipationViewHolder>() {
    class ParticipationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addParticipation(participation: Participation) {
        participations.add(participation)
        notifyItemInserted(participations.size - 1)
    }

    fun deleteParticipation(index : Int) {
        participations.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clear() {
        participations.clear();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipationViewHolder {
        return ParticipationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_participation,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ParticipationViewHolder, position: Int) {
        val participation = participations[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvId).text = participation.id.toString();
            findViewById<TextView>(R.id.tvPrice).text = participation.price.toString();
            findViewById<TextView>(R.id.tvCommentsPartic).text = participation.comments.toString();
            findViewById<TextView>(R.id.tvResponsiblePartic).text = participation.responsible.toString();
            findViewById<TextView>(R.id.tvEventPartic).text = participation.event?.label;
            findViewById<TextView>(R.id.tvDateAtPartic).text = participation.event?.date.toString();
        }
    }

    override fun getItemCount(): Int {
        return participations.size
    }
}