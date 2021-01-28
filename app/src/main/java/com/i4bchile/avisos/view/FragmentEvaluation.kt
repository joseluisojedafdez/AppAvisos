package com.i4bchile.avisos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.i4bchile.avisos.R
import com.i4bchile.avisos.databinding.FragmentEvaluationBinding
import com.i4bchile.avisos.model.Evaluation
import com.i4bchile.avisos.viewmodel.AvisosVM


class FragmentEvaluation(val value: String): Fragment() {
    val RC_SIGN_IN=9001
    private lateinit var binding:FragmentEvaluationBinding
    private val viewModel: AvisosVM by viewModels()
    private lateinit var mGoogleSignInClient:GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Creando Google Sign In Options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso)



        binding= FragmentEvaluationBinding.inflate(layoutInflater)
        binding.tvResultado.visibility=View.INVISIBLE
        binding.button2.visibility=View.VISIBLE
        binding.signInButton.visibility=View.INVISIBLE
        binding.tvUserName.visibility=View.INVISIBLE
        binding.tvNamePublisherEvaluation.text=value

        binding.signInButton.setOnClickListener {
            signIn()

        }


        binding.button2.setOnClickListener {

            val numStars=binding.rbEvaluationSingle.rating.toInt()

            val comment=binding.etComments.text.toString()
            val userName=binding.tvUserName.text.toString()

            if(userName.isNotEmpty()) {

                val eval=Evaluation(value, userName, numStars, comment)
                viewModel.addEval(eval)
                binding.button2.visibility=View.INVISIBLE
                binding.tvResultado.visibility=View.VISIBLE
                binding.tvResultado.setText(R.string.gracias_por_evaluarnos)
            }
             else {
                Toast.makeText(
                    this.context,
                    "Necesita identificarse para enviar una evaluación",
                    Toast.LENGTH_LONG
                )
                    .show()
            }



        }


        return binding.root
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient....
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }


    }
    fun handleSignInResult(completedTask:Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

             // Signed in successfully, show authenticated UI.
            if (account != null) {
                Log.d("TAG", "handleSignInResult: ${account.displayName}")
            }
                    updateUI(account)
        } catch (e:ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this.context)
        updateUI(account)

    }

    private fun updateUI(account: GoogleSignInAccount?) {

        if (account==null){

            Log.d("TAG", "updateUI sin account: ${account?.displayName}")
           binding.signInButton.visibility=View.VISIBLE


        }
        else {
            Log.d("TAG", "updateUI con account: ${account.displayName}")
            binding.signInButton.visibility=View.INVISIBLE
            binding.ivUserImage.visibility=View.VISIBLE
            binding.ivUserImage.load(account.photoUrl)
            binding.tvUserName.visibility=View.VISIBLE
            binding.tvUserName.text=account.displayName
        }
    }


}