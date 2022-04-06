package com.example.opbligatoriskopgaveapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.opbligatoriskopgaveapp.databinding.FragmentItemDetailBinding
import com.example.opbligatoriskopgaveapp.models.ItemsViewModelData
import com.example.opbligatoriskopgaveapp.models.Item
import androidx.navigation.NavArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_item_detail.*
import kotlinx.coroutines.Dispatchers.Main
import java.nio.file.Files.delete

class ItemDetail : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private val itemsViewModel: ItemsViewModelData by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = auth.currentUser

        val bundle = requireArguments()
        val itemDetailArgs: ItemDetailArgs = ItemDetailArgs.fromBundle(bundle)
        val position = itemDetailArgs.position
        val item = itemsViewModel[position]
        if (item == null) {
            binding.textviewName.text = "No such book!"
            return
        }
        binding.textviewId.text = item.id.toString()
        binding.textviewName.text = item.title
        binding.textviewDescription.text = item.description
        binding.textviewSeller.text = item.seller
        binding.textviewPrice.text = item.price.toString()


        val format = SimpleDateFormat.getDateTimeInstance()
        val str = format.format(item.date*1000L)
        binding.textviewDate.text = str.toString()

        /*private fun showDialog() {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Word")
            //builder.setView(createEditText(this, "Enter a word", InputType.TYPE_CLASS_TEXT))
            builder.setPositiveButton("OK") { dialog, which ->
            }
            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
            builder.show()*/
       /* binding.editTextTitle.setText(book.title)
        binding.editTextPrice.setText(book.price.toString())

        binding.buttonBack.setOnClickListener {
            // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            // https://stackoverflow.com/questions/60003039/why-android-navigation-component-screen-not-go-back-to-previous-fragment-but-a-m
            findNavController().popBackStack()
        }*/

        if(currentUser?.email != item.seller)
        {
            binding.buttonDelete.setOnClickListener {
                itemsViewModel.delete(item.id)
                findNavController().popBackStack()
        }
            binding.buttonDelete.visibility = View.GONE

        }

        /*binding.buttonUpdate.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            //val publisher = binding.editTextPublisher.text.toString().trim()
            //val author = binding.editTextAuthor.text.toString().trim()
            val price = binding.editTextPrice.text.toString().trim().toDouble()
            val updatedBook = Book(book.id, title,  price)
            Log.d("APPLE", "update $updatedBook")
            booksViewModel.update(updatedBook)
            findNavController().popBackStack()
        }*/


        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


