package com.ocean.postermaker.UI.LoginModule.Location

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterLocationBinding

class AdapterState(var context: Activity, list: java.util.ArrayList<StateModel.Data>, OnClicked: BtnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<StateModel.Data>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    public var selectedPosition: Int = -1
    lateinit var binding: AdapterLocationBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_location,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterLocationBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterLocationBinding

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
                mViewHolder.binding.llLanguage.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_gradient_10dp)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mViewHolder.binding.tvLanguage.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                }
            } else {
                mViewHolder.binding.llLanguage.background =
                    ContextCompat.getDrawable(context, R.drawable.rect_gray_10dp)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mViewHolder.binding.tvLanguage.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.black
                        )
                    )
                }
            }
        }
        mViewHolder.binding.tvLanguage.text = list[position].name
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
        fun onClick(list: StateModel.Data, position: Int, mViewHolder: DataHolder)
    }
}