package com.example.srdp_tomassetti_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.srdp_tomassetti_kotlin.data.API
import com.example.srdp_tomassetti_kotlin.data.Todo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var tvPlaceholder : TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        API.setBaseUrl("https://jsonplaceholder.typicode.com")

        tvPlaceholder = findViewById(R.id.tvPlaceholder);
    }

    fun onClickGetTodos(view: View) {
        System.out.println("I have been pressed")

        GlobalScope.launch {
           var todo : Todo = API.getAPI().getTodos1();

            runOnUiThread{

                tvPlaceholder.text = todo.toString();
            }
        }
    }
}