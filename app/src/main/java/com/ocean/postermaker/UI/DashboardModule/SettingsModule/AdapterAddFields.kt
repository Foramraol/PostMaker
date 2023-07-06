package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterAddFieldsBinding

class AdapterAddFields(
    var context: Activity,
    list: ArrayList<AddFieldsModel.AddFieldsList>,
    OnClicked: BtnClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<AddFieldsModel.AddFieldsList>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    var selectedPosition: Int = 0
    lateinit var binding: AdapterAddFieldsBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_add_fields,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterAddFieldsBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterAddFieldsBinding

        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder

        mViewHolder.binding.edtName.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            list[i].name= mViewHolder.binding.edtName.text.toString()
            Log.d("TAG", "onBindViewHolder: ${list[i].name}")
            return@OnEditorActionListener true
        })

        mViewHolder.itemView.setOnClickListener {
            OnClicked.onClick(
                list[position],
                position,
                mViewHolder
            )
        }
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

    fun addItems(items: ArrayList<AddFieldsModel.AddFieldsList>) {
        val size: Int = list.size
        list.addAll(items)
        notifyItemRangeChanged(size, items.size)
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
        fun onClick(list: AddFieldsModel.AddFieldsList, position: Int, mViewHolder: DataHolder)
    }
}