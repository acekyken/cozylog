package com.acekyken.cozylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.acekyken.cozylog.ui.theme.CozyLogTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.dp

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Fredoka")
//val fredokafamily = FontFamily(
//    Font(R.font.fredoka_light, FontWeight.Light),
//    Font(R.font.fredoka, FontWeight.Normal),
//    Font(R.font.fredoka_medium, FontWeight.Medium),
//    Font(R.font.fredoka_semibold, FontWeight.SemiBold),
//    Font(R.font.fredoka_bold, FontWeight.Bold)
//)
val fredokafamily = FontFamily(
    Font(googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CozyLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)) {
                            Column( modifier = Modifier.padding(all=8.dp)) {
                                MessageCard(message = Message("Alton", "How are you feeling today?"))
                            }

                            Column (modifier = Modifier.padding(all = 8.dp)){
                                TrackableCard(Trackable(TrackableType.BINARY, name = "migraine", TrackableValue.BinaryTrackable(false)))
                            }
                        }


                }
            }
        }
    }
}

@Composable
fun TrackableCard(element: Trackable) {
    when (element.type) {
        TrackableType.BINARY -> TrackableBinaryCard(element)
        TrackableType.LINEAR -> TrackableLinearElementCard(element)
        else -> Text("Oops")
    }
}

@Composable
fun TrackableBinaryCard(element: Trackable) {
    when (element.value) {
        is TrackableValue.BinaryTrackable -> {
            Row {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(element.name, fontFamily = fredokafamily)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    SwitchTrackableBinary(element.value)
                }
            }
        }
        else -> {}
    }
}

@Composable
fun TrackableLinearElementCard(element: Trackable) {
    when (element.value) {
        is TrackableValue.NumericTrackable -> {
            Row {
                Column {
                    Text(element.name, fontFamily = fredokafamily)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    SliderTrackable(element.value)
                }
            }
        }
        else -> {}
    }
}

@Composable
fun SliderTrackable(value: TrackableValue.NumericTrackable) {
    var sliderPosition by remember { mutableStateOf(value.value) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition = it}
        )
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun SwitchTrackableBinary(value: TrackableValue.BinaryTrackable) {
    var checked by remember { mutableStateOf(value.value) }
    Switch(checked = checked, onCheckedChange = {checked = it})
}

@Preview
@Composable
fun TrackableElementCardPreview() {
    CozyLogTheme {
        Surface {
            Column {
                TrackableCard(Trackable(TrackableType.BINARY,"migraine", TrackableValue.BinaryTrackable(true)))
                Spacer(modifier = Modifier.padding(8.dp))
                TrackableCard(Trackable(TrackableType.LINEAR, "anxiety", value = TrackableValue.NumericTrackable(0.5f)))
            }


        }
    }
}

@Preview
@Composable
fun Preview() {
    CozyLogTheme {
        Surface {

        }
    }
}

// -------------------------------------TUTORIAL EXAMPLES--------------------------------------------
// Data class for the tutorial example
data class Message(val author: String, val body: String)

// This is just a tutorial example for understanding Jetpack Compose. This can be deleted later!
@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all= 8.dp)) {
        Image(
            painter = painterResource(R.drawable.llama2),
            contentDescription = "contact profile picture",
            modifier = Modifier.size(80.dp).clip(CircleShape).border(1.5.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = message.author, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Surface (shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(text = message.body, style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}

@Preview()
@Composable
fun MessageCardPreview() {
    CozyLogTheme {
        Surface {
            MessageCard(message = Message("lilly", "hi! how are you today? feeling better?"))
        }
    }
}
// --------------------------------------------------------------------------------------------------
