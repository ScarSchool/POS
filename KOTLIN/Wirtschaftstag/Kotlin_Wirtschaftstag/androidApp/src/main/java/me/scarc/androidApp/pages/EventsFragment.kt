package me.scarc.androidApp.pages

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.scarc.androidApp.R
import me.scarc.androidApp.adapter.EventsAdapter
import me.scarc.androidApp.data.Event
import me.scarc.androidApp.web.API
import me.scarc.androidApp.web.WebHelper
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class EventsFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var eventAdapter: EventsAdapter
    private lateinit var recyclerViewer: RecyclerView
    private var events: MutableList<Event> = mutableListOf();

    private lateinit var adapter: EventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.page_events, container, false)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        recyclerViewer = rootView.findViewById(R.id.rvMain)

        adapter = EventsAdapter(events)
        recyclerViewer.adapter = adapter

        GetEventsAsync().execute()
    }


    fun makeList(newEvents: List<Event>) {
        Log.i("ENDLICH", "endlich ane gottverdammte events kriag " + events.size)
        newEvents.forEach({adapter.addEvent(it)})
        recyclerViewer.adapter?.notifyDataSetChanged()
    }


    private inner class GetEventsAsync : AsyncTask<String, String, String>() {
        val hostname: String = "172.16.228.1"
        val port: String = "8080"
        val endpoint: String = "/api/Events/"

        override fun onProgressUpdate(vararg values: String) {
            var events: List<Event> = listOf()

            var rawData = values[0]


            try {
                val gson: Gson = Gson()
                val sType = object : TypeToken<List<Event>>() {}.type
                events = gson.fromJson<List<Event>>(rawData, sType)

                Log.i("INFO", "Successfully got ${events.size} eventses.")
                makeList(events)
            } catch (ex: Exception) {
                var error = "Could not de-json companies: " + ex.message
                Log.e("ERR", error)
            }
        }

        override fun doInBackground(vararg p0: String?): String {
            var urlConnection: HttpURLConnection? = null
            var inString: String = ""

            try {
                val url = URL("http://$hostname:$port$endpoint");

                urlConnection = url.openConnection() as HttpURLConnection;
                urlConnection.connectTimeout = 30000;
                urlConnection.readTimeout = 30000;
                Log.i("DEBUG", "wo hello")

                Log.i("IMPORTANTO", "Import stream is null " + (urlConnection.inputStream == null).toString())

                inString = streamToString(urlConnection.inputStream)

                publishProgress(inString)
            } catch (ex: Exception) {
                var error = "Error while getting data: " + ex.message
                Log.e("ERR", error)
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }

            Log.i("INFO", "Got company data: " + inString)
            return inString;
        }

        fun streamToString(inputStream: InputStream): String {
            Log.i("DEBUG", "wo1")
            val bufferReader = BufferedReader(InputStreamReader(inputStream));
            Log.i("DEBUG", "wo2")
            var line: String;
            var result = "";

            try {
                do {
                    line = bufferReader.readLine();
                    if (line != null)
                        result += line
                } while (line != null);
                inputStream.close();
            } catch (ex: Exception) {
                Log.i("ERR", "An Error occurred while parsing data: " + ex.message);
            }
            Log.i("DEBUG", "wo3")
            return result;
        }
    }
}