package com.example.kinesis.kinesisClient

import com.example.kinesis.kinesisClient.consume.ConsumeService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetRecordTest @Autowired constructor(
    private val consumeService: ConsumeService
) {
    @Test
    fun getRecordTest() {
        consumeService.consumeRecord()
    }
}
