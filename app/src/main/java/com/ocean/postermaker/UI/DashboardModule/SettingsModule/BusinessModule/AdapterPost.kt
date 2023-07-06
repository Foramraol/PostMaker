package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterPostBinding

class AdapterPost(var context: Activity, list: ArrayList<String>, OnClicked: BtnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<String>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    var selectedPosition: Int = -1
    lateinit var binding: AdapterPostBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_post,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterPostBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterPostBinding

        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder
        mViewHolder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                OnClicked.onClick(list[position], position, mViewHolder)
            }
        })
    }

    fun setSelected(selectedPos: Int) {
        for (i in list.indices) {
            if (selectedPos == i) {
                selectedPosition = i
                notifyDataSetChanged()
                Log.d("TAG", "setSelected: " + list[i])
            } else {
                Log.d("TAG", "setNotSelected: " + list[i])
            }
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