package com.asyncstorage.sqlite.android.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.asyncstorage.sqlite.android.example.ui.MainScreen
import org.asyncstorage.sqlitestorage.SQLiteStorageFactory
import org.asyncstorage.sqlitestorage.SqliteStorage

class MainActivity : AppCompatActivity() {
    lateinit var db: SqliteStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = SQLiteStorageFactory(this).create("my_db")
        setContent {
            MainScreen(db)
        }
    }
}
