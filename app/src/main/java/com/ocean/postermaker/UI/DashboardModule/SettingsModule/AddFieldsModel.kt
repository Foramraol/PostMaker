package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import com.google.gson.annotations.SerializedName

class AddFieldsModel {
    @SerializedName("Data")
    var fieldsList: ArrayList<AddFieldsList>? = null

    override fun toString(): String {
        return "AddFieldsModel{" +
                "AddFieldsList=" + fieldsList +
                '}'
    }

    class AddFieldsList {
        @SerializedName("name")
        var name: String? = null

        @SerializedName("value")
        var value: String? = null

        @SerializedName("id")
        var id = 0
        override fun toString(): String {
            return "AddFieldsList{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", id=" + id +
                    '}'
        }
    }
}