package tw.edu.pu.csim.tcyang.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {

    var CanvasWidth by remember { mutableStateOf(0f) }
    var CanvasHeight by remember { mutableStateOf(0f) }

    val minionsImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.minions)
    var minionOffsetX by remember { mutableStateOf(0f) }
    var minionOffsetY by remember { mutableStateOf(0f) }
    var gameover by remember { mutableStateOf(false) }

    LaunchedEffect(gameover) {
        while (!gameover) {
            delay(1L)
            minionOffsetX += 1
            minionOffsetY += 1

        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 畫布區塊
        Canvas(
            modifier = modifier.fillMaxSize()
        ) {
            drawCircle(
                Color.Magenta,
                100f,
                Offset(100f, 100f)
            )
            drawRect(
                Color.Blue,
                Offset(300f, 200f),
                Size(200f, 200f),
                style = Stroke(width = 5f)
            )

            CanvasWidth = size.width
            CanvasHeight = size.height

            drawLine(
                Color.Red,
                Offset(200f, 800f),
                Offset(size.width, size.height),
                strokeWidth = 10f
            )
            translate(minionOffsetX,minionOffsetY) {
                drawImage(minionsImage)
            }
        }
        Column{
        Text("畫布寬度$CanvasWidth,長度$CanvasHeight")
            Button(
                onClick = {gameover = !gameover}
            )
            {
                if(gameover){Text("繼續遊戲")}
                else { Text("停止遊戲")}
            }
    }
}}