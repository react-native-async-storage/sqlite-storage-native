package com.asyncstorage.sqlite.android.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.asyncstorage.sqlite.SQLiteStorageFactory
import com.asyncstorage.sqlite.StorageAccess
import com.asyncstorage.sqlite.android.example.ui.MainScreen

class MainActivity : AppCompatActivity() {
    lateinit var db: StorageAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = SQLiteStorageFactory(this).create("my_db")
        setContent {
            MainScreen(db)
        }
    }
}
