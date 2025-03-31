package com.zelkulon.printzone1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.zelkulon.printzone1.ui.theme.PrintZone1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrintZone1Theme {
                Scaffold { innerPadding ->
                    PrintMediaTabLayout()
                }
            }
        }
    }
}
