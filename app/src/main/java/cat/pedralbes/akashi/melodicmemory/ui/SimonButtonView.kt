package cat.pedralbes.akashi.melodicmemory.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import android.os.Handler
import androidx.core.graphics.alpha
import cat.pedralbes.akashi.melodicmemory.R
import cat.pedralbes.akashi.melodicmemory.utilities.ColorsUtility
import cat.pedralbes.akashi.melodicmemory.utilities.ColorsUtility.Companion.changeAlphaValue

class SimonButtonView (context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    // Buttons colors lists
    private val buttonColors = arrayOf(
        ColorsUtility.hexToArgb(R.color.button1, context),
        ColorsUtility.hexToArgb(R.color.button2, context),
        ColorsUtility.hexToArgb(R.color.button3, context),
        ColorsUtility.hexToArgb(R.color.button4, context)
    )
    private val paint = Paint()
    private val buttons: MutableList<Rect> = mutableListOf()
    private val handler = Handler()

    // Reset color handler
    private val resetColorRunnable = Runnable {
        resetButtonColors()
        invalidate()
    }

    // Draw canvas
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        // Get current width and height
        val width = width
        val height = height

        // Calculate size, margin and starts cords
        val buttonSize = width / 4
        val buttonMargin = 16
        val startX = (width - buttonSize * 2 - buttonMargin) / 2
        val startY = (height - buttonSize * 2 - buttonMargin) / 2

        // Clear buttons list just in case;
        buttons.clear()

        // Draw in grid form 2x2;
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                // Calculate extreme cords;
                val left = startX + (buttonSize + buttonMargin) * j
                val top = startY + (buttonSize + buttonMargin) * i
                val right = left + buttonSize
                val bottom = top + buttonSize
                // Determinate the rect form;
                val rect = Rect(left, top, right, bottom)
                // Add to the list
                buttons.add(rect)
                // Determinate color position of the button;
                paint.color = buttonColors[i * 2 + j]
                // Draw on canvas;
                canvas.drawRect(rect, paint)
                // Print debug data;
                val index = buttons.indexOf(rect)
                Log.e("string", "Button color onDraw Color${buttonColors[index]}")
            }
        }
    }

    // Listen for touch events;
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Check for action;
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Get cords;
            val x = event.x.toInt()
            val y = event.y.toInt()

            for (rect in buttons) { // For each button check position;
                if (rect.contains(x, y)) { // If touch position inside the button  cords;
                    Log.e("string","Button $rect locked")
                    lightenButton(rect) // Change button bright;
                    invalidate() // Update canvas;
                    Log.e("string","Canvas invalidated")
                    break
                }
            }
        }
        return true
    }

    // Increase selected button bright
    private fun lightenButton(rect: Rect) {
        // Get index from list;
        val index = buttons.indexOf(rect)
        Log.e("string", "Index: $index")
        // Update color to alpha 255
        buttonColors[index] = changeAlphaValue(buttonColors[index], 255)
        invalidate()
        Log.e("string", "Button color after change: ${buttonColors[index]}")
        // Handler call in ms
        handler.postDelayed({ resetButtonColor(index) }, 1000)    }

    private fun resetButtonColor(index: Int) {
        buttonColors[index] = ColorsUtility.changeAlphaValue(buttonColors[index], 64)
        invalidate()
    }

    private fun resetButtonColors() {
        for (i in buttonColors.indices) {
            buttonColors[i] = ColorsUtility.changeAlphaValue(buttonColors[i], 64)
        }
    }
}