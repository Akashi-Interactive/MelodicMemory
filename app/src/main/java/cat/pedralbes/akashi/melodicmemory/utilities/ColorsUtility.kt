package cat.pedralbes.akashi.melodicmemory.utilities

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat

class ColorsUtility {
    companion object {
        // Hex to ARGB loaded from resources;
        fun hexToArgb(hexColor: Int, context: Context): Int {
            Log.e("String", "HtA: The color ID is: $hexColor")
            val colorString = getColorFromResources(context, hexColor)
            return Color.parseColor(colorString)
        }

        // Hex to ARGB direct format;
        fun hexToArgb(hexColor: String): Int {
            Log.e("String", "HtA: The color code received is: $hexColor")
            val colorString =
                if (hexColor.startsWith("#"))
                    hexColor.substring(1)
                else
                    hexColor
            return Color.parseColor("#$colorString")
        }

        // Get Resource from context;
        private fun getColorFromResources(context: Context, colorResId: Int): String {
            val resource = ContextCompat.getColor(context, colorResId)
            val resConverted =  "#" + Integer.toHexString(resource)
            Log.e("String", "HtA: Resource Converted: $resConverted")
            return resConverted
        }

        // Change alpha value
        fun changeAlphaValue(color: Int, alpha: Int): Int {

            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)

            return Color.argb(alpha, red, green, blue)
        }
    }
}