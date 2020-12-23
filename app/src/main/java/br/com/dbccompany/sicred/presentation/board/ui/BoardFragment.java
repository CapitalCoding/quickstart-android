package br.com.dbccompany.sicred.presentation.board.ui;

import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import br.com.dbccompany.sicred.R;
import br.com.dbccompany.sicred.databinding.BoardFragmentBinding;
import br.com.dbccompany.sicred.domain.model.Event;
import br.com.dbccompany.sicred.presentation.board.ui.adapter.BoardAdapter;
import br.com.dbccompany.sicred.presentation.event.EventDetailActivity;
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
        Toast.makeText(getContext(), "Sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        Event event = (Event) adapter.getData().get(position);
        Toast.makeText(getContext(), "Item:"+event.getTitle()+" id:"+event.getId(), Toast.LENGTH_SHORT).show();
        final View androidRobotView = view.findViewById(R.id.event_item_image);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this.getActivity(), androidRobotView, "robot");
        Intent intent = new Intent(this.getActivity(), EventDetailActivity.class);
        intent.putExtra("event_id", event.getId());
        startActivity(intent, options.toBundle());
    }
}