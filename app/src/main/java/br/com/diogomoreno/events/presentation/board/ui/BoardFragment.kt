package br.com.diogomoreno.events.presentation.board.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.diogomoreno.events.domain.model.Event
import br.com.diogomoreno.events.presentation.board.ui.adapter.BoardAdapter
import br.com.diogomoreno.events.presentation.event.EventDetailActivity
import br.com.flasco.leanworks.R
import br.com.flasco.leanworks.databinding.BoardFragmentBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : Fragment(), OnItemClickListener {
    private var mViewModel: BoardViewModel? = null
    var boardFragmentBinding: BoardFragmentBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(BoardViewModel::class.java)
        boardFragmentBinding = BoardFragmentBinding.inflate(inflater, container, false)
        val adapter = BoardAdapter()
        boardFragmentBinding!!.eventsList.adapter = adapter
        subscribeUi(adapter)
        return boardFragmentBinding!!.root
    }

    private fun subscribeUi(adapter: BoardAdapter) {
        mViewModel?.eventsLiveData?.observe(viewLifecycleOwner, Observer { data: MutableList<Event?>? -> adapter.setNewData(data) })
        adapter.setOnItemClickListener(this)
        boardFragmentBinding!!.boardSwipeRefreshLayout.setOnRefreshListener { mViewModel!!.loadEvents() }
        mViewModel?.showProgress?.observe(viewLifecycleOwner,
                Observer { aBoolean: Boolean? ->
                    boardFragmentBinding!!.boardSwipeRefreshLayout.isRefreshing = aBoolean!!
                    if (!aBoolean) successNotification()
                })
    }

    private fun successNotification() {
//        Toast.makeText(getContext(), "Sucesso.", Toast.LENGTH_SHORT).show();
        val mySnackbar = Snackbar.make(view!!, "Eventos atualizados", Snackbar.LENGTH_LONG)
        mySnackbar.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val event = adapter.data[position] as Event
        val androidRobotView = view.findViewById<View>(R.id.event_item_image)
        var options: ActivityOptions? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this.activity, androidRobotView, "robot")
        }
        val intent = Intent(this.activity, EventDetailActivity::class.java)
        intent.putExtra("event_id", event.id)
        startActivity(intent, options!!.toBundle())
    }

    companion object {
        fun newInstance(): BoardFragment {
            return BoardFragment()
        }
    }
}