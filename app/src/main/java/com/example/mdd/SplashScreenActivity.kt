package com.example.mdd

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mdd.model.DataQuotes
import com.example.mdd.ui.theme.MDDTheme
import com.example.mdd.utils.loadQuotes


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MDDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplaySplashScreen()
                }
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, TestBlindTest::class.java)
            startActivity(intent)
            finish()
        }, 6000)
    }
}



@Composable
fun DisplaySplashScreen() {
    val context = LocalContext.current
    val quotes = loadQuotes(context)
    var currentQuote by remember {
        mutableStateOf<DataQuotes?>(null)
    }
    LaunchedEffect(key1 = true) {
        if (quotes.isNotEmpty()) {
            currentQuote = quotes.random()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(text = stringResource(R.string.initial_title))
        Image(
            painter = painterResource(id = R.drawable.logoprovisoire),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(16.dp)
                .size(500.dp)
                .aspectRatio(1f)
        )

        currentQuote?.let {
            Column {
                Text(text = "Citation", color = MaterialTheme.colorScheme.onBackground)
                Text(text = buildAnnotatedString {
                    append("- ${it.quote} -")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold))
                    { append(it.artist) }
                    (" , ")
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(it.song)
                        (",")
                    }
                    Text(text = it.album +", ${it.date}", color = MaterialTheme.colorScheme.onBackground)
                }, color = MaterialTheme.colorScheme.onBackground)
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    MDDTheme {
       DisplaySplashScreen()
    }
}

