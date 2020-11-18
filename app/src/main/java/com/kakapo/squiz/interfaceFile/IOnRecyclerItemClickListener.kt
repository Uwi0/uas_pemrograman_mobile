package com.kakapo.squiz.interfaceFile

import android.view.View

interface IOnRecyclerItemClickListener {
    fun onClick(view: View, position: Int)
}