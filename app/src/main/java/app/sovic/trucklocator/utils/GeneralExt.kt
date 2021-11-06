package app.sovic.trucklocator.utils

import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.MutableLiveData
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow


fun View.viewVisible() {
    this.visibility = View.VISIBLE
}


fun View.viewGone() {
    this.visibility = View.GONE
}


fun View.viewInvisible() {
    this.visibility = View.INVISIBLE
}




fun prettyCount(number: Number): String? {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = number.toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            numValue / 10.0.pow((base * 3).toDouble())
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}



fun hideSoftKeyboard(act: Activity?) {
    val inputMethodManager = act?.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(
            act.currentFocus!!.windowToken,
            0
        )
    }
}





//
//fun createPartFromString(partString: String): RequestBody {
//    return RequestBody.create(MultipartBody.FORM, partString)
//}
//
//fun createPartFromFile(partName: String, partFile: File): MultipartBody.Part {
//    val requestFile = RequestBody.create(MultipartBody.FORM, partFile)
//    return MultipartBody.Part.createFormData(partName, partFile.name, requestFile)
//}





fun isFormValid(
    currentUserName: MutableLiveData<String>,
    currentPassword: MutableLiveData<String>
): String? {

    val currentUserName = currentUserName.value
    val currentPassword = currentPassword.value
    return when {
        currentUserName.isNullOrEmpty() -> "Please enter Username"
        !currentUserName.isValidUserName() -> "Please enter username"
        currentPassword.isNullOrEmpty() -> "Please enter password"
        currentPassword.length < 8 -> "Invalid password"
        else -> null
    }
}

fun String.isValidUserName(): Boolean =
    this.isNotBlank() && this.matches("^[a-zA-Z0-9]*$".toRegex())

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


val <T> T.exhaustive: T
    get() = this

fun Any.logd(message: String?) {
    Log.d(this::class.java.simpleName, "" + message)
}

fun Any.loge(message: String?) {
    Log.e(this::class.java.toString(), "" + message)
}



/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */
fun View.getString(stringResId: Int): String = resources.getString(stringResId)

//To use this simple call:
//textViewName.getString(R.string.name_title)



//code for real path end here

//time conversion
//fun ConvertSectoDay(n: Long):String{
//    var n = n
//    val day = n / (24 * 3600)
//    n = n % (24 * 3600)
//    val hour = n / 3600
//    n %= 3600
//    val minutes = n / 60
//    n %= 60
//    val seconds = n
//    println(
//        day.toString() + " " + "days " + hour
//                + " " + "hours " + minutes + " "
//                + "minutes " + seconds + " "
//                + "seconds "
//    )
//
//    return  day.toString()
//}


fun ConvertSectoDay(n: Long):String {
    val milliseconds = n
    val dy: Long = TimeUnit.NANOSECONDS.toDays(milliseconds)
    val hr: Long = (TimeUnit.NANOSECONDS.toHours(milliseconds)
            - TimeUnit.DAYS.toHours(TimeUnit.NANOSECONDS.toDays(milliseconds)))
    val min: Long = (TimeUnit.NANOSECONDS.toMinutes(milliseconds)
            - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(milliseconds)))
    val sec: Long = (TimeUnit.NANOSECONDS.toSeconds(milliseconds)
            - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(milliseconds)))
    val ms: Long = (TimeUnit.NANOSECONDS.toMillis(milliseconds)
            - TimeUnit.SECONDS.toMillis(TimeUnit.NANOSECONDS.toSeconds(milliseconds)))



    println(
        String.format(
            "%d Days %d Hours %d Minutes %d Seconds %d Milliseconds",
            dy,
            hr,
            min,
            sec,
            ms
        )
    )
    return when {
        dy >= 2 -> {
            "$dy days"
        }
        hr > 24 -> {
            "$dy day ago"}
        min > 60 -> "$hr hours"
        min < 60 -> "$min mins"
        sec <60 -> "$sec sec"
        else -> "$ms milliseconds"
    }
}

