package com.asyncstorage.sqlite.android.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.asyncstorage.sqlite.android.example.ui.basic.BasicPage
import com.asyncstorage.sqlite.android.example.ui.keys.KeysPage
import com.asyncstorage.sqlite.android.example.ui.merge.MergePage
import com.asyncstorage.sqlite.android.example.ui.multi.MultiPage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabNavigation()
        }
    }
}


sealed class Screens(val name: String, val icon: ImageVector) {
    data object Basic : Screens("Basic", Icons.Filled.Home)
    data object Multi : Screens("Multi", Icons.Filled.Menu)
    data object Keys : Screens("Keys", Icons.Filled.Lock)
    data object Merge : Screens("Merge", Icons.Filled.Refresh)
}

val tabs = listOf(
    Screens.Basic,
    Screens.Multi,
    Screens.Keys,
    Screens.Merge,
)

@Composable
fun TabNavigation() {
    var activeScreen: Screens by remember {
        mutableStateOf(Screens.Basic)
    }

    Scaffold(bottomBar = {
        BottomNavigation {
            for (tab in tabs) {
                key(tab.name) {
                    BottomNavigationItem(
                        selected = activeScreen == tab,
                        onClick = { activeScreen = tab },
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = {
                            Text(text = tab.name)
                        }
                    )
                }
            }
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            when (activeScreen) {
                Screens.Basic -> BasicPage(modifier = Modifier.fillMaxSize())
                Screens.Multi -> MultiPage(modifier = Modifier.fillMaxSize())
                Screens.Keys -> KeysPage(modifier = Modifier.fillMaxSize())
                Screens.Merge -> MergePage(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
