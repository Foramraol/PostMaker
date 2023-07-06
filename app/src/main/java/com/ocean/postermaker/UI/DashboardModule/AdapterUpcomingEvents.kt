package com.ocean.postermaker.UI.DashboardModule

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterUpcomingEventsBinding

class AdapterUpcomingEvents(var context: Activity, list: ArrayList<String>, OnClicked: BtnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<String>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    var selectedPosition: Int = -1
    lateinit var binding: AdapterUpcomingEventsBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_upcoming_events,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterUpcomingEventsBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterUpcomingEventsBinding

        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder
        mViewHolder.binding.tvInterest.text = list[position]
        mViewHolder.itemView.setOnClickListener {
            OnClicked.onClick(
                list[position],
                position,
                mViewHolder
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    open interface BtnClickListener {
        fun onClick(list: String, position: Int, mViewHolder: DataHolder)
    }
}