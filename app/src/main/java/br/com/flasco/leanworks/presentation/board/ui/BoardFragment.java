package br.com.flasco.leanworks.presentation.board.ui;

import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import br.com.flasco.leanworks.R;
import br.com.flasco.leanworks.databinding.BoardFragmentBinding;
import br.com.flasco.leanworks.domain.model.Event;
import br.com.flasco.leanworks.presentation.board.ui.adapter.BoardAdapter;
import br.com.flasco.leanworks.presentation.event.EventDetailActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BoardFragment extends Fragment implements OnItemClickListener {

    private BoardViewModel mViewModel;
    BoardFragmentBinding boardFragmentBinding;
    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        boardFragmentBinding = BoardFragmentBinding.inflate(inflater, container, false);
        BoardAdapter adapter = new BoardAdapter();
        boardFragmentBinding.eventsList.setAdapter(adapter);
        subscribeUi(adapter);

        return boardFragmentBinding.getRoot();
    }

    private void subscribeUi(BoardAdapter adapter) {
        mViewModel.getEventsLiveData().observe(getViewLifecycleOwner(), adapter::setNewData);
        adapter.setOnItemClickListener(this);
        boardFragmentBinding.boardSwipeRefreshLayout.setOnRefreshListener(() -> {
         mViewModel.loadEvents();
        });
        mViewModel.getShowProgress().observe(getViewLifecycleOwner(),
                aBoolean -> {
            boardFragmentBinding.boardSwipeRefreshLayout.setRefreshing(aBoolean);
            if(!aBoolean) successNotification();
        });

    }

    private void successNotification() {
//        Toast.makeText(getContext(), "Sucesso.", Toast.LENGTH_SHORT).show();
        Snackbar mySnackbar = Snackbar.make(getView(), "Eventos atualizados", Snackbar.LENGTH_LONG);
        mySnackbar.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        Event event = (Event) adapter.getData().get(position);
        final View androidRobotView = view.findViewById(R.id.event_item_image);
        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this.getActivity(), androidRobotView, "robot");
        }
        Intent intent = new Intent(this.getActivity(), EventDetailActivity.class);
        intent.putExtra("event_id", event.getId());
        startActivity(intent, options.toBundle());
    }
}