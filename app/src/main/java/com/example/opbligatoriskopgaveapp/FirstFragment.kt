package com.example.opbligatoriskopgaveapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.opbligatoriskopgaveapp.databinding.FragmentFirstBinding
import com.example.opbligatoriskopgaveapp.models.ItemsAdapterData
import com.example.opbligatoriskopgaveapp.models.ItemsViewModelData
import com.example.opbligatoriskopgaveapp.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val itemsviewModel: ItemsViewModelData by activityViewModels()

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
            binding.UserName.text = "Hello ${user?.email}"

            user?.email?.let { itemsviewModel.FilterSeller(currentUser = it) }

            itemsviewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
                //Log.d("APPLE", "observer $books")
                //binding.progressbar.visibility = View.GONE
                binding.recyclerview.visibility = if (items == null) View.GONE else View.VISIBLE
                if (items != null) {
                    val adapter = ItemsAdapterData(items) { position ->
                        val action = FirstFragmentDirections.actionFirstFragmentToItemDetail(position)
                        findNavController().navigate(action /*R.id.action_FirstFragment_to_SecondFragment*/)
                    }

                    binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerview.adapter = adapter
                }
            }

            /*itemsviewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
                //Log.d("APPLE", "observer $books")
                //binding.progressbar.visibility = View.GONE
                binding.recyclerview.visibility = if (items == null) View.GONE else View.VISIBLE
                if (items != null) {
                    val currentUser = auth.currentUser
                    val adapter =
                        ItemsAdapterData(items.filter { i -> i.seller == currentUser?.email }) { position ->
                            val action =
                                FirstFragmentDirections.actionFirstFragmentToItemDetail(position)
                            findNavController().navigate(action /*R.id.action_FirstFragment_to_SecondFragment*/)
                        }

                    binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerview.adapter = adapter
                }
            }*/
        } else {
            // No user is signed in
            binding.recyclerview.visibility = View.GONE
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