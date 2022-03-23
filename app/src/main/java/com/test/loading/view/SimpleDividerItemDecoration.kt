package com.test.loading.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.test.loading.R
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SimpleDividerItemDecoration(context: Context?) : ItemDecoration() {
    private val mDivider: Drawable? = context?.let { ContextCompat.getDrawable(it,
            R.drawable.recycler_horizontal_divider) }
    override fun onDrawOver(
            c: Canvas,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params =
                    child.getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

}
