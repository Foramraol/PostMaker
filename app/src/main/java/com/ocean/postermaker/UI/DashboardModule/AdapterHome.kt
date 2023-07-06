package com.ocean.postermaker.UI.DashboardModule

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterHomeBinding
import com.squareup.picasso.Picasso

class AdapterHome(var context: Activity, list: ArrayList<Int>, OnClicked: BtnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<Int>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    lateinit var binding: AdapterHomeBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_home,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterHomeBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterHomeBinding

        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder
        Picasso.get().load(list[position]).into(mViewHolder.binding.ivInterest)
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
        fun onClick(list: Int, position: Int, mViewHolder: DataHolder)
    }
}