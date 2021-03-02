package br.com.diogomoreno.events.presentation.board.ui.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.diogomoreno.events.domain.model.Event
import br.com.flasco.leanworks.R
import br.com.flasco.leanworks.databinding.ListItemEventBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BoardAdapter : BaseQuickAdapter<Event?, BaseViewHolder>(R.layout.list_item_event) {
    override fun convert(helper: BaseViewHolder, item: Event?) {
        if (item == null) return
        //        CardView cardView = helper.getView(R.id.event_card_item);
        val binding: ListItemEventBinding? = helper.getBinding()
        if (binding != null) {
            binding.event = item
            binding.executePendingBindings()
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }
}