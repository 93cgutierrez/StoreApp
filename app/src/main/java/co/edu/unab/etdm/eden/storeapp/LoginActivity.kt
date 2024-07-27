package co.edu.unab.etdm.eden.storeapp

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import co.edu.unab.etdm.eden.storeapp.ui.ui.theme.StoreAppTheme

class LoginActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        println("onStart......")
    }

    override fun onResume() {
        super.onResume()
        println("onResume......")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart......")
    }

    override fun onStop() {
        super.onStop()
        println("onStop......")
    }

    override fun onPause() {
        super.onPause()
        println("onPause......")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate......")
        enableEdgeToEdge()
        setContent {
            StoreAppTheme {
                LoginScreen()
            }
        }
    }
}


@Composable
fun MyBoxLayouts() {
    Box(
        modifier = Modifier
            .background(color = Color.Cyan)
            .padding(100.dp)
            .size(400.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .size(100.dp)
        )
        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .size(50.dp)
        )

    }
}


@Composable
fun MyColumnLayout() {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .height(100.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .height(50.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}


@Composable
fun MyRowLayout() {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .fillMaxHeight()
                .width(100.dp)
        )
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .fillMaxHeight()
                .width(50.dp)
        )

        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .fillMaxHeight()
                .width(50.dp)
        )
    }
}


@Composable
fun MyConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxYellow, boxBlue, boxRed) = createRefs()
        Box(
            modifier = Modifier
                .background(color = Color.Yellow)
                .size(100.dp)
                .constrainAs(boxYellow) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .size(100.dp)
                .constrainAs(boxBlue) {
                    top.linkTo(boxYellow.bottom)
                    end.linkTo(boxYellow.start)
                }
        )

        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .size(100.dp)
                .constrainAs(boxRed) {
                    top.linkTo(boxYellow.bottom)
                    start.linkTo(boxYellow.end)
                }
        )
    }

}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview2() {
    StoreAppTheme {
        Greeting2("Android")
    }
}