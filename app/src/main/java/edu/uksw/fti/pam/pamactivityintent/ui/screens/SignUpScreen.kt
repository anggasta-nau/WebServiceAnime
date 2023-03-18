package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun SignUpForm(
    btnOnClickAction: (String?) -> Unit
) {
    var usernameInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = usernameInput,
            onValueChange = { usernameInput = it },
            label = { Text(text = stringResource(id = R.string.label_username)) },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = {
                btnOnClickAction(usernameInput)
            }
        ) {
            Text(stringResource(R.string.btn_label_ok))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpFormPreview() {
    PAMActivityIntentTheme {
        SignUpForm({})
    }
}