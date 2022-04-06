package com.example.opbligatoriskopgaveapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opbligatoriskopgaveapp.databinding.FragmentItemListBinding
import com.example.opbligatoriskopgaveapp.models.ItemsAdapterData
import com.example.opbligatoriskopgaveapp.models.ItemsViewModelData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_item_list.*


class ItemFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val itemsviewModel: ItemsViewModelData by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var clicket = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsviewModel.reload()


        itemsviewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
            //Log.d("APPLE", "observer $books")
            //binding.progressbar.visibility = View.GONE
            binding.recyclerview.visibility = if (items == null) View.GONE else View.VISIBLE
            if (items != null) {
                val adapter = ItemsAdapterData(items) { position ->
                    val sellsItemId = items[position].id
                    val action = ItemFragmentDirections.actionItemFragmentToItemDetail(position)
                    findNavController().navigate(action /*R.id.action_FirstFragment_to_SecondFragment*/)
                }

                binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                binding.recyclerview.adapter = adapter
            }
        }

        fun filterDialog() {
            val builder = AlertDialog.Builder(activity)
            // set title
            builder.setTitle("Filter")

            val layout = LinearLayout(activity)
            layout.orientation = LinearLayout.VERTICAL

            //set content area
            builder.setMessage("Filter the price")
            val input = EditText(activity)
            val input1 = EditText(activity)

            input.setHint("Enter min")
            input.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(input)

            input1.setHint("Enter max")
            input1.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(input1)

            builder.setView(layout)

            //set negative button
            builder.setPositiveButton(
                "Update Now"
            ) { dialog, id ->

                val strmin = input.text.toString().trim()
                val strmax = input1.text.toString().trim()

                when {
                    strmin.isEmpty() ->
                        //inputField.error = "No word"
                        Snackbar.make(binding.root, "No min", Snackbar.LENGTH_LONG).show()
                    strmax.isEmpty() -> Snackbar.make(binding.root, "No max", Snackbar.LENGTH_LONG)
                        .show()
                    else -> {
                        val min = strmin.toInt()
                        val max = strmax.toInt()

                        if (min < max) {

                            Toast.makeText(
                                activity,
                                "min is {$min} and max is {$max}",
                                Toast.LENGTH_SHORT
                            ).show()

                            itemsviewModel.FilterPriceASC(min, max)
                        }else
                        {
                            Toast.makeText(
                                activity,
                                "min må ikke være større end max",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            //set positive button
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, id ->
                // User cancelled the dialog
            }

            builder.show()

        }


       /* ItemsViewModelData.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
        binding.textviewMessage.text = errorMessage
    }*/

        //ItemsViewModel.reload()

        /* binding.buttonFirst.setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/

        binding.sortby.setOnClickListener {
            onAddButtonClicket()
        }
            binding.sortbydate.setOnClickListener {
                itemsviewModel.SortDateASC()

                /*itemsviewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
                    //Log.d("APPLE", "observer $books")
                    //binding.progressbar.visibility = View.GONE
                    binding.recyclerview.visibility = if (items == null) View.GONE else View.VISIBLE
                    if (items != null) {
                        val adapter = ItemsAdapterData(items.sortedBy { i -> i.date }) { position ->
                            val action =
                                ItemFragmentDirections.actionItemFragmentToItemDetail(position)
                            findNavController().navigate(action)
                        }

                        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                        binding.recyclerview.adapter = adapter
                    }
                }*/
            }

            binding.sortbyprice.setOnClickListener {

                itemsviewModel.SortPriceASC()

               /* itemsviewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
                    //Log.d("APPLE", "observer $books")
                    //binding.progressbar.visibility = View.GONE
                    binding.recyclerview.visibility = if (items == null) View.GONE else View.VISIBLE
                    if (items != null) {
                        val adapter =
                            ItemsAdapterData(items.sortedBy { i -> i.price }) { position ->
                                val action =
                                    ItemFragmentDirections.actionItemFragmentToItemDetail(position)
                                findNavController().navigate(action)
                            }

                        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                        binding.recyclerview.adapter = adapter
                    }
                }*/

            }

            binding.filter.setOnClickListener {
                //DialogPopUp().show(getParentFragmentManager(), "MyCustomFragment")
                filterDialog()
            }
            val user = Firebase.auth.currentUser
            if (user != null) {

                binding.fab.setOnClickListener { v ->
                    findNavController().navigate(R.id.action_itemFragment_to_AddFragment)
                }
            } else {
                // No user is signed in
                binding.fab.visibility = View.INVISIBLE
            }
        }

    private fun onAddButtonClicket() {
        setVisibility(clicket)
        setClickable(clicket)
        clicket = !clicket
    }

    private fun setClickable(clicket: Boolean) {
        if(!clicket) {
            binding.sortbyprice.isClickable = true
            binding.sortbydate.isClickable = true
        }else {
            binding.sortbyprice.isClickable = false
            binding.sortbydate.isClickable = false
        }
    }

    private fun setVisibility(clicket: Boolean) {
        if(!clicket)
        {
            binding.sortbyprice.visibility = View.VISIBLE
            binding.sortbydate.visibility = View.VISIBLE
            binding.sortByprice.visibility = View.VISIBLE
            binding.SortBydate.visibility = View.VISIBLE
        }else {
            binding.sortbyprice.visibility = View.INVISIBLE
            binding.sortbydate.visibility = View.INVISIBLE
            binding.SortBydate.visibility = View.INVISIBLE
            binding.sortByprice.visibility = View.INVISIBLE
        }

        itemsviewModel.reload()

        binding.swiperefresh?.setOnRefreshListener {
            itemsviewModel.reload()
            binding.swiperefresh!!.isRefreshing = false
        }

    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}



