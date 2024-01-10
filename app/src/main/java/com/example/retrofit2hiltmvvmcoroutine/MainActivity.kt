package com.example.retrofit2hiltmvvmcoroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit2hiltmvvmcoroutine.btn01.ui.activity.Test01Activity
import com.example.retrofit2hiltmvvmcoroutine.btn02.ui.activity.Test02Activity
import com.example.retrofit2hiltmvvmcoroutine.btn03.ui.activity.Test03Activity
import com.example.retrofit2hiltmvvmcoroutine.databinding.AtyMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bind: AtyMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = AtyMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.btn01.setOnClickListener(this)
        bind.btn02.setOnClickListener(this)
        bind.btn03.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            bind.btn01 -> {
                val intent = Intent(this, Test01Activity::class.java)
                startActivity(intent)
            }
            bind.btn02 -> {
                val intent = Intent(this, Test02Activity::class.java)
                startActivity(intent)
            }
            bind.btn03 -> {
                val intent = Intent(this, Test03Activity::class.java)
                startActivity(intent)
            }
        }
    }

}