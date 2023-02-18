import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChallengeComposable() {
    Row(modifier = Modifier.height(150.dp)) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Blue)
                .width(100.dp)
        )
        Column(modifier = Modifier.width(300.dp)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(Color.Gray)
                    .padding(10.dp),
                text = "Test 1"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
                text = "Test 2"
            )
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun ChallengeComposablePreview() {
    ChallengeComposable()
}