package com.example.paporeto.extensions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue

fun Drawable.toCircle(resources: Resources, dp: Int) : Drawable{
    val b = (this as BitmapDrawable).bitmap
    val aspectRatio = b.width.toDouble() / b.height.toDouble()

    val height: Int
    val width: Int
    if (aspectRatio < 1) {
        width = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
        height = (width / aspectRatio).toInt()
    } else {
        height = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
        width = (height * aspectRatio).toInt()
    }

    val newBitmapScaled = Bitmap.createScaledBitmap(b, width, height,  false)

    val dimension = if (aspectRatio < 1) width else height
    val output = Bitmap.createBitmap(
        dimension,
        dimension,
        Bitmap.Config.ARGB_8888
    )

    val paint = Paint()
    paint.isAntiAlias = true
    paint.color = Color.GREEN


    val x: Int
    val y: Int

    if (aspectRatio < 1) {
        x = 0
        y = -((newBitmapScaled.height - output.height)/ 2)
    } else {
        x = -((newBitmapScaled.width - output.width)/ 2)
        y = 0
    }

    val w = x + newBitmapScaled.width
    val h = y + newBitmapScaled.height
    val dest = Rect(x, y, w, h)

    val r = output.width / 2f
    val canvas = Canvas(output)
    canvas.drawARGB(0,0,0,0)
    canvas.drawCircle(r, r, r, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(newBitmapScaled, null, dest, paint)

    return BitmapDrawable(resources, output)
}