package com.invictusbytes.gemaries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.data.db.entities.UsersEntity

class ClientsAdapter(state: String) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {

    private val clients: ArrayList<UsersEntity> = ArrayList()


    fun setData(data: ArrayList<UsersEntity>) {
        clients.clear()
        clients.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.single_user_row,
            parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = clients[position]
        holder.populateView(client)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvClientName: TextView? = null
        private var tvClientPhoneNumber: TextView? = null

        init {
            tvClientName = view.findViewById(R.id.tvClientName)
            tvClientPhoneNumber = view.findViewById(R.id.tvClientPhoneNumber)
        }

        fun populateView(client: UsersEntity) {
            tvClientPhoneNumber?.text = client.phone.toString()
            tvClientName?.text = client.name
        }
    }

}