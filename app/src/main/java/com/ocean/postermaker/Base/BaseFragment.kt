package com.ocean.postermaker.Base

import androidx.fragment.app.Fragment
import com.oceanmtech.dmt.Data.DataManager
import org.koin.android.ext.android.get

open class BaseFragment : Fragment() {
    protected val dataManager: DataManager = get()
}