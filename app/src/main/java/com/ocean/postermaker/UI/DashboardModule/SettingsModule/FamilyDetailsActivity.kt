package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonParser
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.ActivityFamilyDetailsBinding
import org.json.JSONArray
import org.json.JSONObject

class FamilyDetailsActivity : BaseActivity() {

    val context: FamilyDetailsActivity = this@FamilyDetailsActivity
    private lateinit var binding: ActivityFamilyDetailsBinding
    var mArrayFieldsChild: ArrayList<AddFieldsModel.AddFieldsList> =
        ArrayList<AddFieldsModel.AddFieldsList>()
    var mArrayFieldsBrother: ArrayList<AddFieldsModel.AddFieldsList> =
        ArrayList<AddFieldsModel.AddFieldsList>()
    var mArrayFieldsSister: ArrayList<AddFieldsModel.AddFieldsList> =
        ArrayList<AddFieldsModel.AddFieldsList>()
    var adapterMultiFieldsChild: AdapterAddFields? = null
    var adapterMultiFieldsBrother: AdapterAddFields? = null
    var adapterMultiFieldsSister: AdapterAddFields? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_family_details)
        setTitle()
        setChildFieldsList()
        setBrotherFieldsList()
        setSisterFieldsList()
        onCLick()
    }

    private fun setChildFieldsList() {
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvChild.layoutManager = layoutManager
        adapterMultiFieldsChild = AdapterAddFields(
            context as Activity,
            mArrayFieldsChild,
            object : AdapterAddFields.BtnClickListener {
                override fun onClick(
                    list: AddFieldsModel.AddFieldsList,
                    position: Int,
                    mViewHolder: AdapterAddFields.DataHolder
                ) {

                }
            })
        binding.rvChild.adapter = adapterMultiFieldsChild
    }

    private fun setBrotherFieldsList() {
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvBrother.layoutManager = layoutManager
        adapterMultiFieldsBrother = AdapterAddFields(
            context as Activity,
            mArrayFieldsBrother,
            object : AdapterAddFields.BtnClickListener {
                override fun onClick(
                    list: AddFieldsModel.AddFieldsList,
                    position: Int,
                    mViewHolder: AdapterAddFields.DataHolder
                ) {

                }
            })
        binding.rvBrother.adapter = adapterMultiFieldsBrother
    }

    private fun setSisterFieldsList() {
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvSister.layoutManager = layoutManager
        adapterMultiFieldsSister = AdapterAddFields(
            context as Activity,
            mArrayFieldsSister,
            object : AdapterAddFields.BtnClickListener {
                override fun onClick(
                    list: AddFieldsModel.AddFieldsList,
                    position: Int,
                    mViewHolder: AdapterAddFields.DataHolder
                ) {

                }
            })
        binding.rvSister.adapter = adapterMultiFieldsSister
    }

    private fun setTitle() {
        binding.toolBar.tvTitle.text = getString(R.string.family_details)
        binding.toolBar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
        binding.toolBar.tvTitle.textSize = 18F
        binding.toolBar.tvTitle.isSelected = true
        binding.toolBar.ivBack.visibility = View.VISIBLE
        binding.toolBar.tvTitle.visibility = View.VISIBLE
    }

    private fun onCLick() {
        binding.tvSave.setOnClickListener {
            var intent = Intent()
            intent.putExtra("Father", binding.edtFather.text);
            intent.putExtra("Mother", binding.edtMother.text);
            intent.putExtra("Spouse", binding.edtSpouse.text);
            if (mArrayFieldsChild.size != 0) {
                var childArray = JSONArray()
                Log.d("TAG", "onCLickSubmit: ${mArrayFieldsChild.size}")
                for (i in mArrayFieldsChild.indices) {
                    val childObj = JSONObject()
                    Log.d("TAG", "onCLickSubmit: ${mArrayFieldsChild[i].name}")
                    childObj.put("name", "$i")
                    Log.d("TAG", "onCLickSubmit: ${(childObj)}")
                    val jsonParser = JsonParser()
                    childArray.put(
                        childObj
                    )
                }
                Log.d("TAG", "onCLickSubmit: ${(childArray.toString())}")
                intent.putExtra("Children", childArray.toString());
            }
            if (mArrayFieldsBrother.size != 0) {
                var brotherArray = JSONArray()
                Log.d("TAG", "onCLickSubmit: ${mArrayFieldsBrother.size}")
                for (i in mArrayFieldsBrother.indices) {
                    val brotherObj = JSONObject()
                    Log.d("TAG", "onCLickSubmit: ${mArrayFieldsBrother[i].name}")
                    brotherObj.put("name", "$i")
                    Log.d("TAG", "onCLickSubmit: ${(brotherObj)}")
                    val jsonParser = JsonParser()
                    brotherArray.put(
                        brotherObj
                    )
                }
                Log.d("TAG", "onCLickSubmit: ${(brotherArray.toString())}")
                intent.putExtra("Brother", (brotherArray.toString()));
            }
            if (mArrayFieldsSister.size != 0) {
                var sisterArray = JSONArray()
                Log.d("TAG", "onCLickSubmit: ${mArrayFieldsSister.size}")
                for (i in mArrayFieldsSister.indices) {
                    val sisterObj = JSONObject()
                    Log.d("TAG", "onCLickSubmit: ${mArrayFieldsSister[i].name}")
                    sisterObj.put("name", "$i")
                    Log.d("TAG", "onCLickSubmit: ${(sisterObj)}")
                    val jsonParser = JsonParser()
                    sisterArray.put(
                        sisterObj
                    )
                }
                Log.d("TAG", "onCLickSubmit: ${(sisterArray).toString()}")
                intent.putExtra("Sister", sisterArray.toString());
            }
//            setResult(Activity.RESULT_OK, intent)
//            finish()
        }
        binding.tvReset.setOnClickListener {
            binding.edtFather.hint = getString(R.string.father_name)
            binding.edtMother.hint = getString(R.string.mother_name)
            binding.edtSpouse.hint = getString(R.string.husband_name)
            binding.edtChild.hint = getString(R.string.children_name)
            binding.edtBrother.hint = getString(R.string.brother_name)
            binding.edtSister.hint = getString(R.string.sister_name)
            mArrayFieldsChild.removeAll(mArrayFieldsChild.toSet())
            mArrayFieldsBrother.removeAll(mArrayFieldsBrother.toSet())
            mArrayFieldsSister.removeAll(mArrayFieldsSister.toSet())
            adapterMultiFieldsChild?.notifyDataSetChanged()
            adapterMultiFieldsBrother?.notifyDataSetChanged()
            adapterMultiFieldsSister?.notifyDataSetChanged()
        }
        binding.ivAddChild.setOnClickListener {
            mArrayFieldsChild.add(0, AddFieldsModel.AddFieldsList())
            adapterMultiFieldsChild?.notifyItemInserted(0)
        }
        binding.ivAddBrother.setOnClickListener {
            mArrayFieldsBrother.add(0, AddFieldsModel.AddFieldsList())
            adapterMultiFieldsBrother?.notifyItemInserted(0)
        }
        binding.ivAddSister.setOnClickListener {
            mArrayFieldsSister.add(0, AddFieldsModel.AddFieldsList())
            adapterMultiFieldsSister?.notifyItemInserted(0)
        }
    }
}