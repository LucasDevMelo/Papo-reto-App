package com.example.paporeto.core.authentication.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paporeto.R
import com.example.paporeto.core.inbox.view.ToolbarCallback
import com.example.paporeto.databinding.ActiveNowItemBinding
import com.example.paporeto.databinding.ActiveNowListBinding
import com.example.paporeto.databinding.FragmentInboxBinding
import com.example.paporeto.databinding.FragmentLoginBinding
import com.example.paporeto.databinding.FragmentSignUpBinding
import com.example.paporeto.databinding.InboxRowItemBinding

class InboxFragment : Fragment() , MenuProvider{

    private lateinit var toolbarCallback: ToolbarCallback
    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = "Conversas"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_person_24_yellow)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        
        if (context is ToolbarCallback) {
            toolbarCallback = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inboxAdapter = InboxAdapter()
        binding.rvInbox.adapter = inboxAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class ActiveNowAdapter: RecyclerView.Adapter<ActiveNowAdapter.ActiveNowView>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ActiveNowAdapter.ActiveNowView {
            return ActiveNowView(ActiveNowItemBinding.inflate(layoutInflater, parent, false))
        }

        override fun onBindViewHolder(holder: ActiveNowAdapter.ActiveNowView, position: Int) {
        }

        override fun getItemCount(): Int {
            return 10
        }

        private inner class ActiveNowView(val item: ActiveNowItemBinding) : RecyclerView.ViewHolder(item.root)

    }

    private inner class InboxAdapter: RecyclerView.Adapter<InboxAdapter.InboxView>(){

        private val scrollState = mutableMapOf<Int, Parcelable?>()
        override fun getItemViewType(position: Int): Int {
            return if (position > 0) 1 else 0
        }

        override fun onViewRecycled(holder: InboxView) {
            super.onViewRecycled(holder)
            if (holder is ActiveNowView) {
                val key = holder.layoutPosition
                scrollState[key] = holder.view.rvActiveNow.layoutManager?.onSaveInstanceState()
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxView {
            val view = if (viewType == 0) {
                ActiveNowView(ActiveNowListBinding.inflate(layoutInflater, parent, false))
            } else {
                RowView(InboxRowItemBinding.inflate(layoutInflater, parent, false))
            }
            return view
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: InboxView, position: Int) {
            holder.bind()
        }

        abstract inner class InboxView(view: View) : RecyclerView.ViewHolder(view){
            abstract fun bind()
        }
        private inner class RowView(view: InboxRowItemBinding): InboxView(view.root) {
            override fun bind() {

            }
        }

        private inner class ActiveNowView(val view: ActiveNowListBinding): InboxView(view.root) {
            override fun bind() {
                val key = layoutPosition
                val state = scrollState[key]

                if(state != null) {
                    view.rvActiveNow.layoutManager?.onRestoreInstanceState(state)
                } else {
                    val activieAdapter = ActiveNowAdapter()
                    view.rvActiveNow.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    view.rvActiveNow.adapter = activieAdapter
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val menuHost: MenuHost = requireActivity()
        menuHost.removeMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.inbox_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            android.R.id.home -> {
                toolbarCallback.goToProfile()
                true
            }
            R.id.new_action ->{
                toolbarCallback.goToNewMessage()
                true
            }
            else -> false
        }
    }
}