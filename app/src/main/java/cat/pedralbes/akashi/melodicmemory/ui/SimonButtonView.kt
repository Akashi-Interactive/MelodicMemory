package cat.pedralbes.akashi.melodicmemory.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SimonButtonView (context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val buttonColors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
    private val paint = Paint()

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        val width = width
        val height = height

        val buttonSize = width / 4
        val buttonMargin = 16
        val startX = (width - buttonSize * 2 - buttonMargin) / 2
        val startY = (height - buttonSize * 2 - buttonMargin) / 21

        for (i in 0 until 2) {
            for (j in 0 until 2) {
                val left = startX + (buttonSize + buttonMargin) * j
                val top = startY + (buttonSize + buttonMargin) * i
                val right = left + buttonSize
                val bottom = top + buttonSize
                paint.color = buttonColors[i * 2 + j]
                canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
            }
        }
    }
}