package com.i4bchile.avisos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.i4bchile.avisos.R
import com.i4bchile.avisos.databinding.FragmentDetailBinding
import com.i4bchile.avisos.model.Ad
import com.i4bchile.avisos.viewmodel.AvisosVM

class DetailFragment(val value: String) : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: AvisosVM by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        viewModel.getDetail(value).observe(viewLifecycleOwner, {
           updateUI(it)

        })

        viewModel.getEvals(value).observe(viewLifecycleOwner,{ eval ->
            val acct = GoogleSignIn.getLastSignedInAccount(activity)
            val evaluation=eval.filter{ it.userName == acct?.displayName }
            if (evaluation.isNotEmpty()){
                binding.btEvaluate.visibility=View.INVISIBLE
                binding.tvAlreadyRated.visibility=View.VISIBLE
            }
            else {
                binding.btEvaluate.visibility=View.VISIBLE
                binding.tvAlreadyRated.visibility=View.INVISIBLE
            }
        })

        viewModel.getRatings(value).observe(viewLifecycleOwner, {
            Log.d("TAG", "onCreateView: $value")
            Log.d("TAG", "onCreateView: $it")

            if (it != null) {

                binding.ratingBar.rating = (it.sumaRating / it.evaluations).toFloat()
                binding.tvEvalNumber.text = it.evaluations.toString()
            }
        })

        binding.btShareAd.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action=Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Hola, te comparto esto que puede interesarte." +
                        " Pyme: ${binding.tvPymeDetail.text} - " +
                        "Qué hace: ${binding.tvTitleDetail.text} - " +
                        "Teléfono: ${binding.tvTelefono.text}"
            )
            startActivity(Intent.createChooser(shareIntent,"Compartir con"))
        }
        binding.btPhoneMe.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel: ${binding.tvTelefono.text}")
            startActivity(dialIntent)
        }


        binding.btEvaluate.setOnClickListener {
                        openEvalFragment()
             }


        return binding.root
    }



    private fun updateUI(it: Ad?) {

        binding.tvNamePublisherDetail.text = it?.namePublisher
        binding.tvPymeDetail.text = it?.pyme
        binding.tvTitleDetail.text = it?.details?.title
        binding.tvDetalleAviso.text = it?.details?.description
        binding.tvTelefono.text = it?.details?.cellPhone
        binding.tvEmail.text = it?.details?.email
        binding.tvDireccion.text = it?.details?.address
        binding.tvCity.text = it?.details?.city

    }

    private fun openEvalFragment(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.rv_fragment_container, FragmentEvaluation(value))?.addToBackStack("volver")
            ?.commit()

    }

    override fun onResume() {
        super.onResume()

        viewModel.getEvals(value).observe(viewLifecycleOwner,{ eval ->
            val acct = GoogleSignIn.getLastSignedInAccount(activity)
            val evaluation=eval.filter{ it.userName == acct?.displayName }
            if (evaluation.isNotEmpty()){
                binding.btEvaluate.visibility=View.INVISIBLE
                binding.tvAlreadyRated.visibility=View.VISIBLE
            }
            else {
                binding.btEvaluate.visibility=View.VISIBLE
                binding.tvAlreadyRated.visibility=View.INVISIBLE
            }
        })
    }
}