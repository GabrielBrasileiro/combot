package br.com.gabrielbrasileiro.sample.alert

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.gabrielbrasileiro.sample.R

@Composable
fun AlertView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding))
        ) {
            Text(
                text = stringResource(R.string.alert_message),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun AlertViewPreview() {
    AlertView()
}