package com.example.cleanarchnoteapp.feature_note.domain.util

sealed class OrderType{
    object Asc: OrderType()
    object Des: OrderType()
}
