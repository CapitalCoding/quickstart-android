package br.com.dbccompany.sicred.presentation.board.ui.adapter;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.dbccompany.sicred.R;
import br.com.dbccompany.sicred.databinding.ListItemEventBinding;
import br.com.dbccompany.sicred.domain.model.Event;

public class BoardAdapter extends BaseQuickAdapter<Event, BaseViewHolder> {


    public BoardAdapter() {
        super(R.layout.list_item_event);
    }

    @Override
    protected void convert(BaseViewHolder helper, Event item) {
        if(item == null)
            return;
//        CardView cardView = helper.getView(R.id.event_card_item);
        ListItemEventBinding binding = helper.getBinding();
        if(binding != null){
            binding.setEvent(item);
            binding.executePendingBindings();
        }
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }
}
