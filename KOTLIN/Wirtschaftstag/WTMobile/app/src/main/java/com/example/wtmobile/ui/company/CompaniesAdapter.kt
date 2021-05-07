package com.example.wtmobile.ui.company

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtmobile.R
import com.example.wtmobile.data.models.Company
import com.example.wtmobile.data.models.Event

//import java.time.format.DateTimeFormatter

class CompaniesAdapter(
    private val companies : MutableList<Company>
) : RecyclerView.Adapter<CompaniesAdapter.CompanyViewHolder>() {
    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addCompany(company: Company) {
        companies.add(company)
    }

    fun deleteCompany(index : Int) {
        companies.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clear() {
        companies.clear();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_company,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val company = companies[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvCompanyName).text = company.name
            findViewById<TextView>(R.id.tvZipTown).text = company.zipTown
            findViewById<TextView>(R.id.tvStreet).text = company.street
            findViewById<TextView>(R.id.tvPhone).text = company.phone
            findViewById<TextView>(R.id.tvReplyTo).text = company.replyTo
            findViewById<TextView>(R.id.tvComments).text = company.comments
            findViewById<TextView>(R.id.tvEmail).text = company.email
        }
    }

    override fun getItemCount(): Int {
        return companies.size
    }
}