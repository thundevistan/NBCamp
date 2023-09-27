package bootcamp.sparta.disneym.ui.search

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class GridItemDecoration (
    // grid의 column 수
    private val spanCount: Int,

    // 간격
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)

        if(position >= 0) {
            // item colunm
            val column = position % spanCount
            outRect.apply {

                // spacing - column * ((1f / spanCount) * spacing)
                left = spacing - column * spacing / spanCount

                // (column + 1) * ((1f / spanCount) * spacing)
                right = (column + 1) * spacing / spanCount
                if(position < spanCount) top = spacing
                bottom = spacing
            }
        } else {
            outRect.apply {
                left = 0
                right = 0
                top = 0
                bottom = 0
            }
        }
    }

}