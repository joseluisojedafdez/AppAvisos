package com.i4bchile.avisos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.i4bchile.avisos.R
import com.i4bchile.avisos.databinding.FragmentEvaluationBinding
import com.i4bchile.avisos.model.Evaluation
import com.i4bchile.avisos.viewmodel.AvisosVM

class FragmentEvaluation(val value:String): Fragment() {

    private lateinit var binding:FragmentEvaluationBinding
    private val viewModel: AvisosVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentEvaluationBinding.inflate(layoutInflater)
        binding.tvResultado.visibility=View.INVISIBLE
        binding.button2.visibility=View.VISIBLE
        binding.tvNamePublisherEvaluation.text=value


        binding.button2.setOnClickListener {

            val numStars=binding.rbEvaluationSingle.rating.toInt()
            val email=binding.editTextTextEmailAddress.text.toString()
            val comment=binding.etComments.text.toString()

            if(email.isNotEmpty()) {

                val eval=Evaluation(value,email,numStars,comment)
                viewModel.addEval(eval)
                binding.button2.visibility=View.INVISIBLE
                binding.tvResultado.visibility=View.VISIBLE
                binding.tvResultado.setText(R.string.gracias_por_evaluarnos)
            }
             else {
                Toast.makeText(this.context,
                    "Necesita indicar un email para enviar una evaluación",
                    Toast.LENGTH_LONG)
                    .show()
            }



        }


        return binding.root
    }




}