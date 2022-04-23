package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onValueClear: () -> Unit,
    modifier: Modifier = Modifier,
    onDone: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(R.string.hint_search))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onDone?.invoke()
            }
        ),
        trailingIcon = {
            if (value.isNotEmpty())
                IconButton(onClick = onValueClear) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = stringResource(R.string.cd_clear)
                    )
                }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .shadow(4.dp)
            .background(color = MaterialTheme.colors.surface)
            .height(60.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true, name = "State: Empty")
@Composable
internal fun EmptySearchBarPreview() {
    MeliChallengeTheme {
        SearchBar(
            value = "",
            onValueChange = {  },
            onValueClear = {  }
        )
    }
}

@Preview(showBackground = true, name = "State: Filled")
@Composable
internal fun FillerSearchBarPreview() {
    MeliChallengeTheme {
        SearchBar(
            value = "Smart TV",
            onValueChange = {  },
            onValueClear = {  }
        )
    }
}