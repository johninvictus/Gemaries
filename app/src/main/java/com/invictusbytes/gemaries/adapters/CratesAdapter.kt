package com.invictusbytes.gemaries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.common.base.Predicate
import com.google.common.collect.Iterables
import com.invictusbytes.gemaries.R
import com.invictusbytes.gemaries.adapters.CratesAdapter.ViewHolder
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import io.reactivex.subjects.PublishSubject

class CratesAdapter(private var state: String) : RecyclerView.Adapter<ViewHolder>() {

    private val cratesArrayList: ArrayList<CratesEntity> = ArrayList()
    private val cratesArrayListTemp: ArrayList<CratesEntity> = ArrayList()
    val crateItemLongClick = PublishSubject.create<CratesEntity>()

    fun setData(crates: ArrayList<CratesEntity>) {
        cratesArrayList.clear()
        cratesArrayList.addAll(crates)
        cratesArrayListTemp.addAll(crates)
        notifyDataSetChanged()
    }

    fun filterData(value: String) {
        cratesArrayList.clear()
        cratesArrayList.addAll(cratesArrayListTemp)

        Iterables.removeIf(cratesArrayList, Predicate { input ->
            return@Predicate !input!!.code.contains(value)

        })
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

        holder.crateParent?.setOnLongClickListener {
            crateItemLongClick.onNext(crate)
            return@setOnLongClickListener true
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var crateParent: LinearLayout? = null
        private var tvCode: TextView? = null

        init {
            crateParent = view.findViewById(R.id.crateParent)
            tvCode = view.findViewById(R.id.tvCode)
        }

        fun populateView(crate: CratesEntity) {
            tvCode?.text = crate.code
        }
    }
}