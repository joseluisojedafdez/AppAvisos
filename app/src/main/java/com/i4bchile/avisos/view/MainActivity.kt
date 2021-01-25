package com.i4bchile.avisos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.i4bchile.avisos.R
import com.i4bchile.avisos.databinding.ActivityMainBinding
import com.i4bchile.avisos.viewmodel.AvisosVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.
            beginTransaction().
            replace(R.id.rv_fragment_container,CategoryFragment()).
            addToBackStack("volver").
            commit()

        setContentView(binding.root)
    }
}