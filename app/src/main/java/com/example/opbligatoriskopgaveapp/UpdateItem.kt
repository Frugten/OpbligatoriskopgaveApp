package com.example.opbligatoriskopgaveapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.opbligatoriskopgaveapp.databinding.FragmentUpdateItemBinding

class UpdateItem : Fragment() {
   /* private var _binding: FragmentUpdateItemBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val model: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (model.adding.value!!) {
            binding.textviewId1.visibility = View.GONE
            binding.editTextName.visibility = View.GONE
            //binding.buttonDelete.visibility = View.GONE
            //binding.buttonUpdate.visibility = View.GONE
        } else {
            //binding.buttonAdd.visibility = View.GONE
        }

        val selectedStudent: Item? = model.selected.value
        if (selectedStudent == null) {
            binding.textviewId1.text = "No student selected"
        } else {
            binding.textviewId1.text = selectedStudent.id.toString()
            binding.editTextName.setText(selectedStudent.name)
        }

        /*binding.buttonDelete.setOnClickListener {
            if (selectedStudent == null) {
                binding.textviewId.text = "No student selected"
            } else {
                model.remove(selectedStudent.id)
                findNavController().popBackStack()
            }
        }*/

        /*binding.buttonAdd.setOnClickListener {
            val name = binding.edittextName.text.trim().toString()
            //val address = binding.edittextAddress.text.trim().toString()
            //val semester = binding.edittextSemester.text.trim().toString().toInt()
            //val yearOfBirth = binding.edittextYearOfBirth.text.trim().toString().toInt()
            val student = Item(
                name = name,
            )
            model.add(student)
            findNavController().popBackStack()
        }*/

         binding.ButtonUpdate.setOnClickListener {
             val name = binding.editTextName.text.trim().toString()
             //val address = binding.edittextAddress.text.trim().toString()
             //val semester = binding.edittextSemester.text.trim().toString().toInt()
             //val yearOfBirth = binding.edittextYearOfBirth.text.trim().toString().toInt()
             val student = Item(
                 name = name,

             )
             model.update(selectedStudent!!.id, student)
             findNavController().popBackStack()
         }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}