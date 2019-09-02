package com.invictusbytes.gemaries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.CratesAdapter.ViewHolder
import com.invictusbytes.gemaries.data.db.entities.CratesEntity

class CratesAdapter(private var state: String) : RecyclerView.Adapter<ViewHolder>() {

    private val cratesArrayList: ArrayList<CratesEntity> = ArrayList()


    fun setData(crates: ArrayList<CratesEntity>) {
        cratesArrayList.clear()
        cratesArrayList.addAll(crates)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.single_crate_row,
            parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cratesArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crate = cratesArrayList[position]
        holder.populateView(crate)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var tvCode: TextView? = null

        init {
            tvCode = view.findViewById(R.id.tvCode)
        }

        fun populateView(crate: CratesEntity) {
            tvCode?.text = crate.code
        }
    }
}