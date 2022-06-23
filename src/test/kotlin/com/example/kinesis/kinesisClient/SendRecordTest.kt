package com.example.kinesis.kinesisClient

import com.example.kinesis.kinesisClient.config.exception.MyException
import com.example.kinesis.kinesisClient.model.SampleRecord
import com.example.kinesis.kinesisClient.produce.ProduceService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SendRecordTest @Autowired constructor(
    val produceService: ProduceService
){

    @Test
    fun sendTest(){

        val record = SampleRecord("Song", "32")

        val response = produceService.send(record, "shard_1", "KRDEV-Server-Log-Stream")

        println("=================")
        println(response.toString())
        println(response?.shardId())
    }
}