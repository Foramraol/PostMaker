package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterBusinessListBinding

class AdapterBusiness(var context: Activity, list: ArrayList<String>, OnClicked: BtnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<String>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    var selectedPosition: Int = -1
    lateinit var binding: AdapterBusinessListBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_business_list,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterBusinessListBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterBusinessListBinding

        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder
        for (i in list.indices) {
            if (selectedPosition != -1 && selectedPosition == position) {
                mViewHolder.binding.llBusinessList.background =
                    ContextCompat.getDrawable(context, R.drawable.iv_background)
                mViewHolder.binding.llBusinessLogo.background =
                    ContextCompat.getDrawable(context, R.drawable.iv_circle_background)
                mViewHolder.binding.tvSetAs.text = "Default"
                mViewHolder.binding.tvSetAs.setTextColor(ContextCompat.getColor(context, R.color.white))
                mViewHolder.binding.tvSetAs.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_gradient_10dp)
                mViewHolder.binding.tvChoose.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.tvFrame.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.llBusinessLogo.setPadding(2, 2, 2, 2)
                break
            } else {
                mViewHolder.binding.llBusinessList.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.llBusinessLogo.background =
                    ContextCompat.getDrawable(context, R.drawable.iv_circle_black_background)
                mViewHolder.binding.tvSetAs.text = "Set As"
                mViewHolder.binding.tvSetAs.setTextColor(ContextCompat.getColor(context, R.color.black))
                mViewHolder.binding.tvSetAs.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.tvChoose.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.tvFrame.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_stroke_8dp)
                mViewHolder.binding.llBusinessLogo.setPadding(0, 0, 0, 0)
            }
        }
        mViewHolder.binding.tvBusinessName.text = list[position]
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