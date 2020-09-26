package com.azhar.recyclerdragdrop.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.azhar.recyclerdragdrop.R
import com.azhar.recyclerdragdrop.adapter.MainAdapter.ListViewHolder
import com.azhar.recyclerdragdrop.utils.DragListener
import com.azhar.recyclerdragdrop.utils.Listener
import kotlinx.android.synthetic.main.list_item_main.view.*

/**
 * Created by Azhar Rivaldi on 19-09-2020
 */

class MainAdapter(var list: List<String>,
                  private val listener: Listener?) : RecyclerView.Adapter<ListViewHolder>(), OnTouchListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ListViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvGrid.text = list[position]
        holder.cvGrid.tag = position
        holder.cvGrid.setOnTouchListener(this)
        holder.cvGrid.setOnDragListener(DragListener(listener))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = DragShadowBuilder(view)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0)
            } else {
                view.startDrag(data, shadowBuilder, view, 0)
            }
            return true
        }
        return false
    }

    fun updateList(list: List<String>) {
        this.list = list
    }

    val dragInstance: DragListener?
        get() = if (listener != null) {
            DragListener(listener)
        } else {
            null
        }

    inner class ListViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvGrid: TextView
        var cvGrid: CardView

        init {
            tvGrid = itemView.tvGrid
            cvGrid = itemView.cvGrid
        }
    }
}