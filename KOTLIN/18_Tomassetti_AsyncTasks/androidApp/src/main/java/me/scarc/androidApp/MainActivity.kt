package me.scarc.androidApp

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.scarc.shared.Greeting
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var i : Int = 0;

    var CONNECTION_TIMEOUT_MILLISECONDS = 1000;

    private lateinit var listView : ListView
    private lateinit var textView : TextView
    private lateinit var adapter : ArrayAdapter<Any>;

    var listItems = arrayOf("Moooin", "Seeervus", "moooooooin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        listView = findViewById(R.id.list_view)


        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }

    fun onClickMe(view: View) {
        i++;
        textView.text = "Jo schau du host mi schon $i mol ongetatschlet :-)"
    }

    fun onClickAccessWebService(view: View) {
        var city = "Villach"
        var url = "http://172.27.11.105:8080/api/Admins/"
        var task = FetchDataTask().execute(url);
    }

    private inner class FetchDataTask : AsyncTask<String, String, String>() {
        override fun onPostExecute(result: String) {
            print("Downloaded $result bytes")
        }

        override fun onProgressUpdate(vararg values: String) {
            adapter.add(values)
            adapter.notifyDataSetChanged()

            Log.i("DEBUG", adapter.count.toString())
        }

        override fun doInBackground(vararg urls: String?): String {
            var urlConnection: HttpURLConnection? = null;

            try {
                var url = URL(urls[0])

                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = CONNECTION_TIMEOUT_MILLISECONDS;
                urlConnection.readTimeout = CONNECTION_TIMEOUT_MILLISECONDS;

                var inString = streamToString(urlConnection.inputStream)
                publishProgress(inString);
            }catch (ex: Exception) {
                Log.i("Err", ex.localizedMessage);
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return " ";
        }
    }

    private fun streamToString(inputStream: InputStream?): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line : String
        var result = ""

        try {
            do {
                line = bufferedReader.readLine()
                if(line != null) {
                    result += line;
                }
            }while (line != null);
            inputStream?.close()
        } catch (ex : Exception){
            Log.i("Err", ex.localizedMessage)
        }
        return result;
    }
}
