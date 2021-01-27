package com.i4bchile.avisos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.i4bchile.avisos.databinding.FragmentDetailBinding
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
            binding.tvNamePublisherDetail.text = it.namePublisher
            binding.tvPymeDetail.text = it.pyme
            binding.tvTitleDetail.text = it.details.title
            binding.tvDetalleAviso.text = it.details.description
            binding.tvTelefono.text = it.details.cellPhone
            binding.tvEmail.text = it.details.email
            binding.tvDireccion.text = it.details.address
            binding.tvCity.text = it.details.city

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


        binding.btEvaluame.setOnClickListener {
            //TODO: open fragment evaluate add
        }


        return binding.root
    }


}