package com.example.practicedagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicedagger.ui.TodoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, TodoListFragment())
                .commitNow()
        }

    }
}