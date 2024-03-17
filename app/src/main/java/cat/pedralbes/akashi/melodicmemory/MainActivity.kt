package cat.pedralbes.akashi.melodicmemory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cat.pedralbes.akashi.melodicmemory.system.SimonGame
import cat.pedralbes.akashi.melodicmemory.ui.SimonButtonView
import cat.pedralbes.akashi.melodicmemory.ui.theme.MelodicMemoryTheme

class MainActivity : ComponentActivity() {
    private lateinit var simonGame: SimonGame
    private var buttons = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SimonButtonView(this, null))

        // Create game instance
        simonGame = SimonGame(buttons)

        // Start a sequence
        simonGame.generateNextSequence()

        // Get sequence
        val sequence = simonGame.getSequence()
        Log.d("SimonGame", "Sequence: $sequence")
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MelodicMemoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MelodicMemoryTheme {
        Greeting("Android")
    }*/
}