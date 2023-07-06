package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ocean.postermaker.AdManager.NativeBanner
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.AdapterFaqsBinding

class AdapterFAQs(
    var context: Activity,
    list: java.util.ArrayList<faqModel.Data>,
    OnClicked: BtnClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<faqModel.Data>
    var inflater: LayoutInflater
    var OnClicked: BtnClickListener
    lateinit var binding: AdapterFaqsBinding

    init {
        this.list = list
        this.OnClicked = OnClicked
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_faqs,
            parent,
            false
        )
        return DataHolder(binding.root, binding)
    }

    class DataHolder(view: View, binding: AdapterFaqsBinding) :
        RecyclerView.ViewHolder(view) {
        var binding: AdapterFaqsBinding
        init {
            this.binding = binding
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val mViewHolder: DataHolder = holder as DataHolder
        if(position%3==0){
            NativeBanner.loadNativeBannerAds(context, binding.llAdContainerShare)
            binding.llAdContainerShare.visibility = View.VISIBLE;
        }else{
            binding.llAdContainerShare.visibility = View.GONE;
        }
        mViewHolder.binding.tvAns.setText(list[position].description)
        mViewHolder.binding.tvQue.setText(list[position].title)
        mViewHolder.itemView.setOnClickListener {
            OnClicked.onClick(
                list[position],
                position,
                mViewHolder
            )
        }
        mViewHolder.binding.ivDownArrow.setOnClickListener {
            mViewHolder.binding.ivDownArrow.visibility = View.GONE
            mViewHolder.binding.ivUpArrow.visibility = View.VISIBLE
            mViewHolder.binding.tvAns.visibility = View.VISIBLE
        }
        mViewHolder.binding.ivUpArrow.setOnClickListener {
            mViewHolder.binding.ivDownArrow.visibility = View.VISIBLE
            mViewHolder.binding.ivUpArrow.visibility = View.GONE
            mViewHolder.binding.tvAns.visibility = View.GONE
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
        fun onClick(list: faqModel.Data, position: Int, mViewHolder: DataHolder)
    }
}