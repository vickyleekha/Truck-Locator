package app.sovic.trucklocator.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat


/**
 * [Drawable] helper class.
 *
 * @author Filipe Bezerra
 * @version 18/01/2016
 * @since 18/01/2016
 */
class DrawableHelper(var mContext: Context) {
    @ColorRes
    private var mColor = 0
    private var mDrawable: Drawable? = null
    private var mWrappedDrawable: Drawable? = null
    fun withDrawable(@DrawableRes drawableRes: Int): DrawableHelper {
        mDrawable = ContextCompat.getDrawable(mContext, drawableRes)
        return this
    }

    fun withDrawable(drawable: Drawable): DrawableHelper {
        mDrawable = drawable
        return this
    }

    @SuppressLint("ResourceType")
    fun withColor(@ColorRes colorRes: Int): DrawableHelper {
        mColor = ContextCompat.getColor(mContext, colorRes)
        return this
    }

    @SuppressLint("ResourceAsColor")
    fun tint(): DrawableHelper {
        if (mDrawable == null) {
            throw NullPointerException("É preciso informar o recurso drawable pelo método withDrawable()")
        }
        check(mColor != 0) { "É necessário informar a cor a ser definida pelo método withColor()" }
        mWrappedDrawable = mDrawable!!.mutate()
        mWrappedDrawable = DrawableCompat.wrap(mWrappedDrawable!!)
        DrawableCompat.setTint(mWrappedDrawable as Drawable, mColor)
        DrawableCompat.setTintMode(mWrappedDrawable as Drawable, PorterDuff.Mode.SRC_IN)
        return this
    }

    fun applyToBackground(view: View) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = mWrappedDrawable
        } else {
            view.setBackgroundDrawable(mWrappedDrawable)
        }
    }

    fun applyTo(imageView: ImageView) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        imageView.setImageDrawable(mWrappedDrawable)
    }

    fun applyTo(menuItem: MenuItem) {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        menuItem.setIcon(mWrappedDrawable)
    }

    fun get(): Drawable {
        if (mWrappedDrawable == null) {
            throw NullPointerException("É preciso chamar o método tint()")
        }
        return mWrappedDrawable as Drawable
    }

    companion object {
        fun withContext(context: Context): DrawableHelper {
            return DrawableHelper(context)
        }
    }
}