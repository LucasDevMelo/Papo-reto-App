package com.example.paporeto.core.newmessage.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.paporeto.R
import com.example.paporeto.databinding.ContactRowItemBinding
import com.example.paporeto.databinding.FragmentNewMessageBinding

class NewMessageFragment : Fragment(), MenuProvider {

    private var _binding: FragmentNewMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ContactAdapter()
        binding.rvContacts.adapter = adapter

        val activity = (requireActivity() as AppCompatActivity)
        activity.supportActionBar?.title = "Nova mensagem"

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val menuHost: MenuHost = activity
        menuHost.removeMenuProvider(this)
    }

    private inner class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactView>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactView {
            return ContactView(ContactRowItemBinding.inflate(layoutInflater,parent,false))
        }

        override fun onBindViewHolder(holder: ContactView, position: Int) {

        }

        override fun getItemCount(): Int {
            return 4
        }

        private inner class ContactView(item: ContactRowItemBinding): RecyclerView.ViewHolder(item.root)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
            return true
        }

        return false
    }
}