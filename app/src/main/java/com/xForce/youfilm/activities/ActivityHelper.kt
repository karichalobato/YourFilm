package com.xForce.youfilm.activities

import androidx.recyclerview.widget.RecyclerView

interface ActivityHelper {
    fun getLayoutManager(): RecyclerView.LayoutManager
    fun showToast(msg:String)
    fun internetIsAvailable():Boolean
}