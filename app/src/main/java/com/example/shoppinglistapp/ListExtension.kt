package com.example.shoppinglistapp

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.UUID


@Composable
fun ShoppingListApp(modifier: Modifier = Modifier) {
    var shoppingListItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showingDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(shoppingListItems) {
            item ->
            if (item.isEditing){
                EditItemLayout(item = item, onEditComplete = { name, quantity ->
                    shoppingListItems = shoppingListItems.map { it.copy(isEditing = false) }
                    val editedItem = shoppingListItems.find { it.id == item.id }
                    if (editedItem != null) {
                        if (!(editedItem.name.isBlank() || editedItem.quantity.isBlank())){
                            editedItem.name = name
                            editedItem.quantity = quantity.toString()
                        }else{
                            Log.e("Update", "error on values : ${editedItem.name} & ${editedItem.quantity }" )
                        }
                    }
                } )
            }
            else{
                ItemLayout(item , onEditClicked = {
                    shoppingListItems = shoppingListItems.map { it.copy(isEditing = it.id == item.id) }
                } , onDeleteClicked = {
                    shoppingListItems=shoppingListItems-item
                })
            }
//
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(onClick = { showingDialog = true }) {
            Text("Add Item")
        }
    }

    if (showingDialog) {
        AlertDialog(
            onDismissRequest = { showingDialog = false },
            title = { Text("Add new Shopping Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = { Text("Item Name") },
                        placeholder = { Text("Enter Item Name") }
                    )
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { if ( it.all { c: Char -> c.isDigit() || c =='.' })quantity = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = { Text("Quantity") },
                        placeholder = { Text("Enter Quantity") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val quantityInt = quantity.toDoubleOrNull() ?: 0.0 // Prevent crash
                    if (name.isNotBlank() && quantityInt > 0.0) {
                        shoppingListItems = shoppingListItems + ( ShoppingItem(id = "${UUID.randomUUID()}", name = name, quantity = quantityInt.toString()))
                        Log.e("TAG", "list size : ${shoppingListItems.size}")
                        name = ""   // Reset values after adding
                        quantity = ""
                        showingDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = {
                    name = ""
                    quantity = ""
                    showingDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListAppPreview() {
    ShoppingListApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewEditItemLayout() {
    val sampleItem = ShoppingItem(
        id = "1",
        name = "Milk",
        quantity = "2",
        isEditing = true
    )

    EditItemLayout(
        item = sampleItem,
        onEditComplete = { newName, newQuantity ->
            println("Edited Item -> Name: $newName | Quantity: $newQuantity")
        }
    )
}



@Preview(showBackground = true)
@Composable
fun ItemLayoutPreview() {
    ItemLayout(
        ShoppingItem(
            "1",
            "Milk",
            "2"
        ),
        {},
        {}
    )
}