package br.com.diogomoreno.events.presentation.event.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.diogomoreno.events.domain.model.Event
import br.com.diogomoreno.events.presentation.event.ui.dialog.CheckInDialogFragment
import br.com.diogomoreno.events.utils.PresentationUtils
import br.com.flasco.leanworks.R
import br.com.flasco.leanworks.databinding.EventDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {
    private var mViewModel: EventDetailViewModel? = null
    private var eventDetailFragmentBinding: EventDetailFragmentBinding? = null
    private val callback: Callback? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        eventDetailFragmentBinding = EventDetailFragmentBinding.inflate(inflater, container, false)
        eventDetailFragmentBinding!!.viewModel = mViewModel
        eventDetailFragmentBinding!!.lifecycleOwner = viewLifecycleOwner
        val bundle = arguments
        if (bundle != null) {
            val eventId = Integer.valueOf(bundle.getString("event_id")!!)
            mViewModel!!.setEventId(eventId)
        }
        subscribeUi()
        return eventDetailFragmentBinding!!.root
    }

    private fun subscribeUi() {
        mViewModel!!.eventMutableLiveData.observe(viewLifecycleOwner, Observer { event: Event? -> })
        eventDetailFragmentBinding!!.toolbar.setOnMenuItemClickListener { item: MenuItem ->
            Log.d(TAG, "subscribeUi: " + item.itemId)
            when (item.itemId) {
                R.id.action_share -> {
                    val event = mViewModel!!.eventMutableLiveData.value
                    startActivity(PresentationUtils.shareIntent(event?.description))
                    return@setOnMenuItemClickListener true
                }
                R.id.action_back -> {
                    activity!!.onBackPressed()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
        eventDetailFragmentBinding!!.fab.setOnClickListener { v: View? ->
            val checkInDialogFragment = CheckInDialogFragment()
            val bundle = Bundle()
            bundle.putString("event_id", mViewModel!!.eventMutableLiveData.value?.id)
            checkInDialogFragment.arguments = bundle
            checkInDialogFragment.show(activity!!.supportFragmentManager, "checkin_dialog")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected: " + item.itemId)
        return super.onOptionsItemSelected(item)
    }

    interface Callback {
        fun checkIn(event: Event?)
    }

    companion object {
        fun newInstance(eventId: String?): EventDetailFragment {
            val eventDetailFragment = EventDetailFragment()
            if (eventId != null && !eventId.isEmpty()) {
                val args = Bundle()
                args.putString("event_id", eventId)
                eventDetailFragment.arguments = args
            }
            return eventDetailFragment
        }

        private const val TAG = "EventDetailFragment"
    }
}