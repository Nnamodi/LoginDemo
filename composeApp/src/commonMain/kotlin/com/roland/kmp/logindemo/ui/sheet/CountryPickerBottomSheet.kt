package com.roland.kmp.logindemo.ui.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

typealias countryCode = String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerBottomSheet(
    onDismissRequest: () -> Unit,
    countries: List<CountryCodeItemModel>,
    onCountrySelected: (countryCode) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(Modifier.fillMaxWidth()) {
            Header(
                modifier = Modifier.fillMaxWidth(),
                onCloseClicked = onDismissRequest
            )
            LazyColumn(Modifier.weight(1f)) {
                items(countries.size) { index ->
                    CountryCodeListItem(
                        model = countries[index],
                        onItemClick = onCountrySelected
                    )
                }
            }
        }
    }
}

@Composable
private fun CountryCodeListItem(
    model: CountryCodeItemModel,
    modifier: Modifier = Modifier,
    onItemClick: (countryCode) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClick(model.countryCode) }
            .heightIn(min = 40.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.flagEmoji,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                text = model.title,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                text = model.body,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        if (model.isChecked) {
            Image(
                imageVector = Icons.Rounded.Check,
                contentDescription = "${model.countryCode} selected"
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Select country",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            IconButton(onClick = onCloseClicked) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
        HorizontalDivider()
    }
}

data class CountryCodeItemModel(
    val flagEmoji: String,
    val title: String,
    val body: String,
    val countryCode: String,
    val isChecked: Boolean
)