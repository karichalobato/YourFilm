package com.xForce.youfilm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.xForce.youfilm.R
import com.xForce.youfilm.fragments.MainListFragment

class MainActivity : AppCompatActivity() {

    var listaFragment = MainListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(findViewById<FrameLayout>(R.id.ContenedorFragments) != null){
            if(savedInstanceState != null){

            }
            supportFragmentManager.beginTransaction().replace(R.id.ContenedorFragments,listaFragment).commit()
        }

    }
}
