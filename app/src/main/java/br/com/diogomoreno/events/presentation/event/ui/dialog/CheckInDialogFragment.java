package br.com.diogomoreno.events.presentation.event.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import br.com.flasco.leanworks.databinding.FragmentDialogCheckinBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CheckInDialogFragment extends DialogFragment {

    private FragmentDialogCheckinBinding binding;
    private CheckInViewModel mViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = FragmentDialogCheckinBinding.inflate(LayoutInflater.from(getContext()));
        mViewModel = new ViewModelProvider(this).get(CheckInViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(CheckInDialogFragment.this);
        Bundle bundle = getArguments();
        if(bundle != null){
            String eventId = bundle.getString("event_id");
            mViewModel.setEventId(eventId);
        }

        subscribeUi();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(binding.getRoot());

        return alertDialog.create();
    }

    private void subscribeUi() {
        binding.btnCheckInFragment.setOnClickListener(v -> {
            mViewModel.checkInAction(binding.tieName.getText().toString(),
                    binding.tieEmail.getText().toString(), mViewModel.getEventId());
        });
//        mViewModel.getShowProgress().observe(CheckInDialogFragment.this,
//                aBoolean -> binding);
    }
}
