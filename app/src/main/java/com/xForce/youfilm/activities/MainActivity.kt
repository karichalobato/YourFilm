package com.xForce.youfilm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xForce.youfilm.R
import com.xForce.youfilm.fragments.MainListFragment

class MainActivity : AppCompatActivity(),ActivityHelper {


    var listaFragment = MainListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun getLayoutManager(): RecyclerView.LayoutManager   = LinearLayoutManager(this)

    override fun showEmptySearchToast() = Toast.makeText(this,"No search param added!",Toast.LENGTH_SHORT).show()
}
