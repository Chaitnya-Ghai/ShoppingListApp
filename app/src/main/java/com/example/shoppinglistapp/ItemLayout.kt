package com.example.shoppinglistapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemLayout(
    item: ShoppingItem,
    onEditClicked: () -> Unit,
    onDeleteClicked :()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, Color(0xFF547DDC), shape = RoundedCornerShape(20)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name , modifier = Modifier
            .padding(8.dp)
            .weight("2".toFloat(), true))
        Text(text = "QTy: ${item.quantity}", modifier = Modifier
            .padding(8.dp)
            .weight("2".toFloat(), true))
        Row(modifier = Modifier
            .padding(8.dp)
            .weight("2".toFloat(), true)) {
            IconButton(onClick = onEditClicked ){
                Icon(imageVector = Icons.Default.Edit , contentDescription = "EDIT BUTTON ")
            }
            IconButton(onClick = onDeleteClicked ){
                Icon(imageVector = Icons.Default.Delete , contentDescription = "DELETE BUTTON ")
            }
        }
    }
}