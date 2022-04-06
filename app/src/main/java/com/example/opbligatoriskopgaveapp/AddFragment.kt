package com.example.opbligatoriskopgaveapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.opbligatoriskopgaveapp.databinding.FragmentAddBinding
import com.example.opbligatoriskopgaveapp.models.ItemsViewModelData

import com.example.opbligatoriskopgaveapp.models.Item
import androidx.navigation.NavArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private val itemsviewModel: ItemsViewModelData by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = auth.currentUser

        binding.SubmitButton.setOnClickListener {
            val title = binding.EditTextTitle.text.toString().trim()
            val priceStr = binding.EditTextPrice.text.toString().trim()
            val description = binding.EditTextDescription.text.toString().trim()
            val seller = currentUser?.email.toString()
            val date = System.currentTimeMillis()/1000
            if (title.isEmpty()) {
                //inputField.error = "No word"
                Snackbar.make(binding.root, "No title", Snackbar.LENGTH_LONG).show()
            }
            if (priceStr.isEmpty()) {
                //inputField.error = "No word"
                Snackbar.make(binding.root, "No price", Snackbar.LENGTH_LONG).show()
            }
            // https://firebase.google.com/docs/auth/android/password-auth
            val item = Item(title,
                priceStr.toInt(),
                description,
                seller,
                date.toInt())
            Log.d("APPLE", "elementer Id: $id price: $priceStr, description: $description, seller: $seller, date: $date")

            Log.d("APPLE", "add $item")
            itemsviewModel.add(item)
            findNavController().popBackStack()
            }

    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

}