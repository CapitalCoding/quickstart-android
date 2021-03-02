package br.com.flasco.leanworks.presentation.event.ui;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import br.com.flasco.leanworks.R;
import br.com.flasco.leanworks.databinding.EventDetailFragmentBinding;
import br.com.flasco.leanworks.domain.model.Event;
import br.com.flasco.leanworks.presentation.event.ui.dialog.CheckInDialogFragment;
import br.com.flasco.leanworks.utils.PresentationUtils;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class EventDetailFragment extends Fragment {

    private EventDetailViewModel mViewModel;
    private EventDetailFragmentBinding eventDetailFragmentBinding;
    private Callback callback;

    public static EventDetailFragment newInstance(String eventId) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        if(eventId != null && !eventId.isEmpty()) {
            Bundle args = new Bundle();
            args.putString("event_id", eventId);
            eventDetailFragment.setArguments(args);
        }
        return eventDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EventDetailViewModel.class);
        eventDetailFragmentBinding = EventDetailFragmentBinding.inflate(inflater, container, false  );
        eventDetailFragmentBinding.setViewModel(mViewModel);
        eventDetailFragmentBinding.setLifecycleOwner(getViewLifecycleOwner());
        Bundle bundle = getArguments();
        if(bundle != null){
            int eventId = Integer.valueOf(bundle.getString("event_id"));
            mViewModel.setEventId(eventId);
        }
        subscribeUi();
        return eventDetailFragmentBinding.getRoot();
    }

    private void subscribeUi() {
        mViewModel.eventMutableLiveData.observe(getViewLifecycleOwner(), event -> {
//            eventDetailFragmentBinding.notifyPropertyChanged(BR.event);
        });
        eventDetailFragmentBinding.toolbar.setOnMenuItemClickListener(item -> {
            Log.d(TAG, "subscribeUi: "+item.getItemId());
            switch(item.getItemId()){
                case R.id.action_share:
                    Event event = mViewModel.eventMutableLiveData.getValue();
                    startActivity(PresentationUtils.shareIntent(event.getDescription()));
                    return true;
                case R.id.action_back:
                    getActivity().onBackPressed();
                    return true;
            }
            return false;
        });

        eventDetailFragmentBinding.fab.setOnClickListener(v -> {
            CheckInDialogFragment checkInDialogFragment = new CheckInDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("event_id", mViewModel.eventMutableLiveData.getValue().getId());
            checkInDialogFragment.setArguments(bundle);
            checkInDialogFragment.show(getActivity().getSupportFragmentManager(), "checkin_dialog");
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private static final String TAG = "EventDetailFragment";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: "+item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public interface Callback {
        void checkIn(Event event);
    }
}