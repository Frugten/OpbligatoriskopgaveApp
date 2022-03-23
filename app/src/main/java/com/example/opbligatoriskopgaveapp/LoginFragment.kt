package com.example.opbligatoriskopgaveapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.opbligatoriskopgaveapp.databinding.FragmentLoginBinding
import com.example.opbligatoriskopgaveapp.models.ItemsViewModelData
import com.example.opbligatoriskopgaveapp.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val currentUser = auth.currentUser
        if (currentUser != null) {
            binding.emailInputField.setText(currentUser.email) // half automatic login
            // current user exists: No need to login again
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            binding.messageView.text = "Current user ${currentUser?.email}"
            binding.signIn.visibility = View.INVISIBLE
            binding.buttonCreateUser.visibility = View.GONE
            binding.passwordInputField.visibility = View.GONE
            binding.emailInputField.visibility = View.GONE
            binding.SignOutButton.visibility = View.VISIBLE

            binding.SignOutButton.setOnClickListener {
                FirebaseRepository().signOut()
                findNavController().navigate(R.id.loginFragment)
                Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_LONG).show()

            }

        }
        else {

            binding.messageView.text = "Please Log in"
            binding.SignOutButton.visibility = View.GONE
            // User is signed in


            binding.signIn.setOnClickListener {
                val email = binding.emailInputField.text.toString().trim()
                val password = binding.passwordInputField.text.toString().trim()
                if (email.isEmpty()) {
                    binding.emailInputField.error = "No email"
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    binding.passwordInputField.error = "No password"
                    return@setOnClickListener
                }
                // https://firebase.google.com/docs/auth/android/password-auth
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.FirstFragment)
                    } else {
                        binding.messageView.text = task.exception?.message
                    }
                }
            }
            binding.buttonCreateUser.setOnClickListener {
                val email = binding.emailInputField.text.toString().trim()
                val password = binding.passwordInputField.text.toString().trim()
                if (email.isEmpty()) {
                    binding.emailInputField.error = "No email"
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    binding.passwordInputField.error = "No password"
                    return@setOnClickListener
                }
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.messageView.text = "User created. Now please login"
                        // Alternative: goto next fragment (no need to login after register)
                    } else {
                        binding.messageView.text = task.exception?.message
                    }
                }
            }
        }







    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}