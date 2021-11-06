package app.sovic.trucklocator

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


const val DEVICE_ID = 6
const val API_KEY =
    "geUaAfbbh0ULdinkx1E3AhF5JwnAovsNuy9BKf+ag1Lscm8wqeEfGK3XR/aXydrN8KRMlf2iwmOWK2L3xfR3JkKHRSCZKjj5AIx/sTpFp0iU6thx"
const val VERSION_ID = 160
const val APP_ID = 1


const val NOTIFICATION_REQUEST = "api/User/GetNotifications"

const val TYPE_NOTIFICATION_COMMENT = 1
const val TYPE_NOTIFICATION_POST = 2
const val TYPE_NOTIFICATION_PEOPLE = 3


const val ERROR_TYPE_API = 1
const val ERROR_TYPE_RETROFIT = 2
const val ERROR_TYPE_EXCEPTION = 3

const val EXPLORE_SEARCH = "api/User/GetExploreUsers"

const val COMMUNITIES_TYPE_API = 1
const val CHALLENGES_TYPE_API = 2
const val PRODUCTS_TYPE_API = 5
const val GAMING_TYPE_API = 3
const val SERVICE_TYPE_API = 6

const val TYPE_MY_POSTS = 0
const val TYPE_STARRED = 1
const val TYPE_FEATURED = 2


const val TYPE_FOLLOWING = 0
const val TYPE_FOLLOWER = 1
const val TYPE_SUGGESTION = 2

const val UPDATE_USER_PROFILE = "api/User/UpdateUserProfile"
const val GET_USER_POSTS = "api/Post/GetUserPosts"
const val GET_STARRED = "api/Post/GetMyPosts"
const val GET_POSTS="api/Post/GetPostsByChannelID"


const val TYPE_DIRECT = 0
const val TYPE_FOLLOWING_HOME= 1
const val TYPE_FOR_YOU = 2

const val PROFILE_PHOTO_PART = "ProfilePhoto"

const val themeKey = "currentTheme"
const val KEY_PAGE = "page"



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







/* Adding a fragment with slide

                                  */
//
//fun addSlidingFragmentBackStack(
//    fragmentManager: FragmentManager, fragment: Fragment,
//    tag: String, @IdRes container: Int
//) {
//    if (null != fragmentManager.findFragmentById(container)) {
//        Log.d("gi", "if is called")
//        fragmentManager.findFragmentById(container)?.let {
//            if (!it.tag.equals(tag, true)) {
//                fragmentManager.beginTransaction().setCustomAnimations(
//                    R.anim.overshoot_slide_right_in, 0, 0,
//                    R.anim.overshoot_slide_right_out
//                ).add(container, fragment, tag).addToBackStack(tag).commit()
//            }
//        }
//    } else {
//        Log.d("gi", "else is called")
//        fragmentManager.beginTransaction().setCustomAnimations(
//            R.anim.overshoot_slide_right_in, 0, 0,
//            R.anim.overshoot_slide_right_out
//        ).add(container, fragment, tag).addToBackStack(tag).commit()
//    }
//
//}





/* This code is used for getting real path from uri  */

fun getRealPathFromUri(uri: Uri, context: Context): String? {
    // DocumentProvider
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(
            context,
            uri
        )
    ) {
        // ExternalStorageProvider
        if (isExternalStorageDocument(uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":").toTypedArray()
            val type = split[0]
            if ("primary".equals(type, ignoreCase = true)) {
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            }
        } else if (isDownloadsDocument(uri)) {
            val id = DocumentsContract.getDocumentId(uri)
            val contentUri: Uri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"),
                java.lang.Long.valueOf(id)
            )
            return getDataColumn(context, contentUri, "", emptyArray())
        } else if (isMediaDocument(uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":").toTypedArray()
            val type = split[0]
            var contentUri: Uri? = null
            when (type) {
                "image" -> {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                }
                "video" -> {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                }
                "audio" -> {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
            }
            val selection = "_id=?"
            val selectionArgs = arrayOf(
                split[1]
            )
            return getDataColumn(context, contentUri!!, selection, selectionArgs)
        }
    } else if ("content".equals(uri.scheme, ignoreCase = true)) {

        // Return the remote address
        return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
            context,
            uri,
            "",
            emptyArray()
        )
    } else if ("file".equals(uri.scheme, ignoreCase = true)) {
        return uri.path
    }
    return null
}


fun getDataColumn(
    context: Context, uri: Uri, selection: String,
    selectionArgs: Array<String>,
): String? {
    var cursor: Cursor? = null
    val column = "_data"
    val projection = arrayOf(
        column
    )
    try {
        cursor = context.contentResolver.query(
            uri, projection, selection, selectionArgs,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val index: Int = cursor.getColumnIndexOrThrow(column)
            return cursor.getString(index)
        }
    } finally {
        cursor?.close()
    }
    return null
}

fun isExternalStorageDocument(uri: Uri) =
    "com.android.externalstorage.documents" == uri.authority


fun isDownloadsDocument(uri: Uri) =
    "com.android.providers.downloads.documents" == uri.authority


fun isMediaDocument(uri: Uri) =
    "com.android.providers.media.documents" == uri.authority


fun isGooglePhotosUri(uri: Uri) =
    "com.google.android.apps.photos.content" == uri.authority

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

