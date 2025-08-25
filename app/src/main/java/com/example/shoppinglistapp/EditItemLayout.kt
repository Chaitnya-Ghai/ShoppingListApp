package com.example.shoppinglistapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditItemLayout(item: ShoppingItem , onEditComplete :(String,Double) -> Unit){
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity) }
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .border(width = 2.dp, Color(0xFF547DDC), shape = RoundedCornerShape(20))
    ){
        Column{
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange = { newValue->
                    if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        editedQuantity = newValue
                    } },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
        }
        Button(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(8.dp) ,
            onClick = {
                onEditComplete(editedName,editedQuantity.toDouble())
            }) { Text(text = "Save") }
    }
}