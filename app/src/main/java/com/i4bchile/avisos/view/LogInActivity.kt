package com.i4bchile.avisos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.fragment.app.Fragment
import coil.load
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.i4bchile.avisos.databinding.ActivityLogInBinding

/*
class LogInActivity : Fragment(), View.OnClickListener {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var googleApiClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        googleApiClient = GoogleSignIn.getClient(this, gso)
        binding.signInButton.setOnClickListener(this)
        auth = Firebase.auth
        binding.signInButton.visibility = View.VISIBLE
        binding.tvNombre.visibility = View.INVISIBLE
        binding.ivUserPhoto.visibility = View.INVISIBLE
        binding.tvWelcomeUser.visibility = View.INVISIBLE

        setContentView(binding.root)

    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    override fun onClick(v: View?) {

        val signInIntent = googleApiClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed
                Log.w(TAG, "Google sign in failed", e)

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateView(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Snackbar.make(
                        binding.signInButton,
                        "Authentication Failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }

            }
    }

    private fun updateView(user: FirebaseUser?) {

        if (user != null) {

            binding.signInButton.visibility = View.INVISIBLE
            binding.tvNombre.visibility = View.VISIBLE
            binding.ivUserPhoto.visibility = View.VISIBLE
            binding.tvWelcomeUser.visibility = View.VISIBLE
            binding.tvNombre.text = user.displayName
            binding.ivUserPhoto.load(user.photoUrl)

        }


    }
}
*/
