package com.asyncstorage.sqlite.android.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.asyncstorage.sqlite.android.example.ui.MainScreen
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.SQLiteStorageFactory

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = SQLiteStorageFactory(this).create("my_db")
        db.files.size()
        setContent {
            MainScreen(db)
        }
    }
}
