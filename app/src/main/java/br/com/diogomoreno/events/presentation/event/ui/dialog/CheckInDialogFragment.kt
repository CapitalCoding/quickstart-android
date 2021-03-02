package br.com.diogomoreno.events.presentation.event.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import br.com.diogomoreno.events.presentation.event.ui.dialog.CheckInDialogFragment
import br.com.flasco.leanworks.databinding.FragmentDialogCheckinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckInDialogFragment : DialogFragment() {
    private var binding: FragmentDialogCheckinBinding? = null
    private var mViewModel: CheckInViewModel? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogCheckinBinding.inflate(LayoutInflater.from(context))
        mViewModel = ViewModelProvider(this).get(CheckInViewModel::class.java)
        binding!!.viewModel = mViewModel
        binding!!.lifecycleOwner = this@CheckInDialogFragment
        val bundle = arguments
        if (bundle != null) {
            val eventId = bundle.getString("event_id")
            mViewModel?.eventId = eventId
        }
        subscribeUi()
        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setView(binding!!.root)
        return alertDialog.create()
    }

    private fun subscribeUi() {
        binding!!.btnCheckInFragment.setOnClickListener { v: View? ->
            mViewModel!!.checkInAction(binding!!.tieName.text.toString(),
                    binding!!.tieEmail.text.toString(), mViewModel?.eventId)
        }
        //        mViewModel.getShowProgress().observe(CheckInDialogFragment.this,
//                aBoolean -> binding);
    }
}