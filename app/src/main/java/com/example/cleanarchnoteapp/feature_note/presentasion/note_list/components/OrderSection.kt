package com.example.cleanarchnoteapp.feature_note.presentasion.note_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteOrder
import com.example.cleanarchnoteapp.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Des),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth()
        ){
            //______Title
            DefaultRadioButton(
                text = "Title",
                selected =noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))

            //______Date
            DefaultRadioButton(
                text = "Date",
                selected =noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))

            //______Color
            DefaultRadioButton(
                text = "Color",
                selected =noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType))})
        }
        //---------------------------------//
        Spacer(modifier = Modifier.height(8.dp))
        //---------------------------------//
        Row(
            Modifier.fillMaxWidth()
        ) {
            //______Asc
            DefaultRadioButton(
                text = "Asc",
                selected =noteOrder.orderType is OrderType.Asc,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Asc))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            //______Des
            DefaultRadioButton(
                text = "Des",
                selected =noteOrder.orderType is OrderType.Des,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Des))
                }
            )
        }
    }
}