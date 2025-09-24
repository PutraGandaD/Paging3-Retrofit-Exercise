package com.putragandad.pagingretrofit.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.putragandad.pagingretrofit.presentation.theme.JetpackComposePagingRetrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApp()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}

@Composable
fun MyApp() {
    JetpackComposePagingRetrofitTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .padding(contentPadding)
            ) {
                Text("Hello")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    JetpackComposePagingRetrofitTheme {
        MyApp()
    }
}