package br.com.gabrielbrasileiro.sample.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.gabrielbrasileiro.sample.R
import br.com.gabrielbrasileiro.sample.message.viewmodel.LoadMessageAction
import br.com.gabrielbrasileiro.sample.message.viewmodel.LoadMessageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun LoadMessageScreen(
    viewModel: LoadMessageViewModel = koinViewModel()
) {
    val message by viewModel.message.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LoadMessage(message, viewModel, loading)
}

@Composable
internal fun LoadMessage(
    message: String,
    action: LoadMessageAction,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.column_spacing)
            )
        ) {
            Text(text = message)
            Button(
                onClick = { action.loadNewMessage() },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(
                            dimensionResource(R.dimen.progress_indicator_size)
                        ),
                        color = Color.White,
                        strokeWidth = dimensionResource(R.dimen.progress_indicator_stroke)
                    )
                } else {
                    Text(text = stringResource(R.string.new_message))
                }
            }
        }
    }
}

@Preview
@Composable
internal fun LoadMessagePreview() {
    LoadMessage(
        message = "Welcome",
        isLoading = false,
        action = object : LoadMessageAction {
            override fun loadNewMessage() = Unit
        }
    )
}