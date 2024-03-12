package com.droidcourses

import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.droidcourses.SuccessDispatcher.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.IOException
import java.io.InputStream
import java.io.InputStreamReader

const val FAKE_NEWS_API = "/everything"
const val FAKE_NEWS_RESPONSE_FILE = "fake_news.json"
class SuccessDispatcher(private val context: Context = getInstrumentation().targetContext) : Dispatcher() {
    private val responseFilesByPath: Map<String, String> = mapOf(
        FAKE_NEWS_API to FAKE_NEWS_RESPONSE_FILE
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse
        val responseFile = responseFilesByPath[pathWithoutQueryParams]

        return if (responseFile != null ) {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            errorResponse
        }
    }

    object AssetReaderUtil {
        fun asset(context: Context, assetPath: String): String {

           return  try {
               context.assets.open("network_files/$assetPath").bufferedReader().use{
                   it.readText()
               }
           }catch (e: Exception) {
               throw RuntimeException("Journal exception ${e.message}")
           }

//            try {
//                val inputStream = context.assets.open("network_files/$assetPath")
//                return inputStreamToString(inputStream, "UTF-8")
//            } catch (e: IOException) {
//                throw RuntimeException(e)
//            }
        }

        private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, charsetName)
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        }
    }
}