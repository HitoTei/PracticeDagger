package com.example.practicedagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicedagger.ui.TodoListFragment
import com.example.practicedagger.ui.edit_text.EditTextFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, TodoListFragment())
                .replace(R.id.edit_text, EditTextFragment())
                .commitNow()
        }

    }


}