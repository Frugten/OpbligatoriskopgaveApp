package com.example.opbligatoriskopgaveapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.opbligatoriskopgaveapp.databinding.ActivityMainBinding
import com.example.opbligatoriskopgaveapp.databinding.FragmentFirstBinding
import com.example.opbligatoriskopgaveapp.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        if (user != null) {

            binding.ItemList.setOnClickListener {
                binding.ItemList.visibility = View.INVISIBLE
                FirebaseRepository().signOut()
                Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_LONG).show()
            }
            binding.UserName.text = "Hello ${user?.email}"
        } else {
            // No user is signed in
            binding.ItemList.visibility = View.INVISIBLE
            binding.UserName.visibility = View.INVISIBLE
        }

            binding.buttonFirst.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }

    /*companion object {
        fun newInstance() = FirstFragment()
    }*/

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

}