package com.example.kinesis.kinesisClient.produce

import com.example.kinesis.kinesisClient.config.RoleArn
import com.example.kinesis.kinesisClient.config.exception.MyException
import com.example.kinesis.kinesisClient.model.SampleRecord
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest
import software.amazon.awssdk.services.kinesis.model.PutRecordResponse
import java.util.concurrent.ExecutionException

@Service
class ProduceService(
    private val roleArn: RoleArn
) {
    fun send(record: SampleRecord, shardName: String, streamName: String): PutRecordResponse? {
        try {
            val byte = record.toJsonAsByte()

            val mapper = ObjectMapper()

            val record = mapper.writeValueAsString(record).toByteArray()

            val kinesisClient = roleArn.config()

            return kinesisClient?.putRecord(
                PutRecordRequest.builder()
                    .streamName(streamName)
                    .partitionKey(shardName)
                    .data(SdkBytes.fromByteArray(record))
                    .build()
            ) ?: throw MyException("Kinesis Connection Error")

        }catch (e: InterruptedException) {
            System.out.println("Interrupted, assuming shutdown.");
        } catch (e: ExecutionException) {
            System.err.println("Exception while sending data to Kinesis will try again next cycle");
        }

        return null
    }

}