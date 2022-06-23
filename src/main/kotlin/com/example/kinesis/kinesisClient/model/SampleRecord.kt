package com.example.kinesis.kinesisClient.model

import com.fasterxml.jackson.databind.ObjectMapper

data class SampleRecord(
    val name: String,
    val age: String
){
    fun toJsonAsByte(): ByteArray{
        return ObjectMapper().writeValueAsBytes(this)
    }
}