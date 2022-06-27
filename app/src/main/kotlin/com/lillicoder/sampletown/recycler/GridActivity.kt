package com.lillicoder.sampletown.recycler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.sampletown.DrawerActivity
import com.lillicoder.sampletown.R

/**
 * Activity that shows a grid.
 */
class GridActivity : DrawerActivity() {

    private lateinit var mRecyclerView: RecyclerView

    companion object {

        /**
         * Starts this activity with the given [Context].
         * @param context Starting context.
         */
        fun start(context: Context) {
            context.startActivity(Intent(context, GridActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerView = findViewById(R.id.grid)
        mRecyclerView.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = GridAdapter(getItems())
    }

    override fun getContentView(): Int {
        return R.layout.activity_grid
    }

    override fun getNavigationId(): Int {
        return R.id.navigation_grid
    }

    override fun onContentViewInflated() {
    }

    /**
     * Gets the list of [Item] this activity's grid displays.
     */
    private fun getItems(): List<Item> {
        return listOf(
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine)
        )
    }
}
