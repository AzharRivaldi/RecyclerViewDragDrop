package com.azhar.recyclerdragdrop.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.recyclerdragdrop.R
import com.azhar.recyclerdragdrop.adapter.MainAdapter
import com.azhar.recyclerdragdrop.utils.Listener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by Azhar Rivaldi on 19-09-2020
 */

class MainActivity : AppCompatActivity(), Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTopRecyclerView()
        setBottomRecyclerView()

        tvEmptyListTop.setVisibility(View.GONE)
        tvEmptyListBottom.setVisibility(View.GONE)
    }

    private fun setTopRecyclerView() {
        rvTop.setHasFixedSize(true)
        rvTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val topList: MutableList<String> = ArrayList()
        topList.add("A")
        topList.add("B")

        val topListAdapter = MainAdapter(topList, this)
        rvTop.adapter = topListAdapter
        tvEmptyListTop.setOnDragListener(topListAdapter.dragInstance)
        rvTop.setOnDragListener(topListAdapter.dragInstance)
    }

    private fun setBottomRecyclerView() {
        rvBottom.setHasFixedSize(true)
        rvBottom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val bottomList: MutableList<String> = ArrayList()
        bottomList.add("C")
        bottomList.add("D")

        val bottomListAdapter = MainAdapter(bottomList, this)
        rvBottom.adapter = bottomListAdapter
        tvEmptyListBottom.setOnDragListener(bottomListAdapter.dragInstance)
        rvBottom.setOnDragListener(bottomListAdapter.dragInstance)
    }

    override fun setEmptyListTop(visibility: Boolean) {
        tvEmptyListTop.visibility = if (visibility) View.VISIBLE else View.GONE
        rvTop.visibility = if (visibility) View.GONE else View.VISIBLE
    }

    override fun setEmptyListBottom(visibility: Boolean) {
        tvEmptyListBottom.visibility = if (visibility) View.VISIBLE else View.GONE
        rvBottom.visibility = if (visibility) View.GONE else View.VISIBLE
    }
}