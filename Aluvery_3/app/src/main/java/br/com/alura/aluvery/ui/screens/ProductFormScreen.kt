package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Product
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenUiState(
    val urlText: String = "",
    val onUrlChange: (String) -> Unit = {},
    val nameText: String = "",
    val onNameChange: (String) -> Unit = {},
    val priceText: String = "",
    val onPriceChange: (String) -> Unit = {},
    val descriptionText: String = "",
    val onDescriptionChange: (String) -> Unit = {}
)

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {},
) {
    var urlText by remember {
        mutableStateOf("")
    }
    var nameText by remember {
        mutableStateOf("")
    }
    var priceText by remember {
        mutableStateOf("")
    }
    var descriptionText by remember {
        mutableStateOf("")
    }
    val formatter = remember {
        DecimalFormat("#.##")
    }

    val state = remember(urlText, nameText, priceText, descriptionText) {
        ProductFormScreenUiState(
            urlText = urlText,
            onUrlChange = {
                urlText = it
            },
            nameText = nameText,
            onNameChange = {
                nameText = it
            },
            priceText = priceText,
            onPriceChange = {
                try {
                    priceText = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        priceText = it
                    }
                }
            },
            descriptionText = descriptionText,
            onDescriptionChange = {
                descriptionText = it
            },
        )
    }

    ProductFormScreen(onSaveClick, state)
}

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {},
    state: ProductFormScreenUiState = ProductFormScreenUiState()
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(Modifier)
        Text(
            text = "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )

        if (state.urlText.isNotBlank()) {
            AsyncImage(
                model = state.urlText, contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            value = state.urlText,
            onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = state.nameText,
            onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = state.priceText,
            onValueChange = state.onPriceChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )


        TextField(
            value = state.descriptionText,
            onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(onClick = {
            val convertedPrice = try {
                BigDecimal(state.priceText)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }

            val product = Product(
                name = state.nameText,
                image = state.urlText,
                price = convertedPrice,
                description = state.descriptionText
            )

            onSaveClick(product)
        }, Modifier.fillMaxWidth()) {
            Text(text = "Salvar")
        }
        Spacer(Modifier)
    }
}
