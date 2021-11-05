package app.sovic.trucklocator.data

import android.content.Context
import app.sovic.trucklocator.data.model.Response
import com.google.gson.Gson
import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class Mock @Inject constructor(private val context: ApplicationClass) {

  fun loadMockData(): Response {
    val data = readJsonFromAssets(
      context,
      "Data.json"
    ) //here is my file inside the folder assets/json/some.json

      val `object` = Gson().fromJson<Response>(data, Response::class.java)
      return `object`


  }


//    if (data != null) {
//        val `object` = Gson().fromJson<MyPostsResponse>(data, MyPostsResponse::class.java)
//
//        Log.d("DAATA",`object`.toString())
//        discoverBubblesAdapter.submitList(`object`.ApiResponseObject.Posts)
//    }

  private fun readJsonFromAssets(context: Context, filePath: String): String? {
    try {
      val source = context.assets.open(filePath).source().buffer()
      return source.readByteString().string(Charset.forName("utf-8"))

    } catch (e: IOException) {
      e.printStackTrace()
    }
    return null
  }
}