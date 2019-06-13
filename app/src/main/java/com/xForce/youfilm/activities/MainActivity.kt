package com.xForce.youfilm.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xForce.youfilm.R
import com.xForce.youfilm.fragments.MainListFragment

class MainActivity : AppCompatActivity(),ActivityHelper {
    override fun internetIsAvailable():Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork!!.isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun getLayoutManager(): RecyclerView.LayoutManager   = LinearLayoutManager(this)

    override fun showToast(msg:String) = Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
