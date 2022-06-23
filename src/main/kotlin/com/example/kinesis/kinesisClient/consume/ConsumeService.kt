package com.example.kinesis.kinesisClient.consume

import com.example.kinesis.kinesisClient.config.RoleArn
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.kinesis.model.DescribeStreamRequest
import software.amazon.awssdk.services.kinesis.model.GetRecordsRequest
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorRequest
import software.amazon.awssdk.services.kinesis.model.Shard
import software.amazon.awssdk.services.kinesis.model.ShardIteratorType

@Service
class ConsumeService(
    private val roleArn: RoleArn
) {
    val arn = "{SHOULD ADD role-arn}"

    fun consumeRecord() {
        val kinesisClient = roleArn.config() ?: return

        val describeStreamRequest = DescribeStreamRequest.builder()
            .streamName("KRDEV-Server-Log-Stream")
            .build()

        val shards: ArrayList<Shard> = ArrayList()
        var lastShardId = ""

        do {
            var streamRequest = kinesisClient.describeStream(describeStreamRequest)
            shards.addAll(streamRequest.streamDescription().shards())

            if (shards.size > 0) {
                lastShardId = shards.get(shards.size - 1).shardId()
            } else {
                println("No Shard !!!")
            }
        } while (streamRequest.streamDescription().hasMoreShards())

        val itReq = GetShardIteratorRequest.builder()
            .streamName("KRDEV-Server-Log-Stream")
            .shardIteratorType(ShardIteratorType.LATEST)
            .shardId(lastShardId)
            .build()

        val shardIteratorResponse = kinesisClient.getShardIterator(itReq)
        val shardIterator = shardIteratorResponse.shardIterator()

        val response = kinesisClient.getRecords(
            GetRecordsRequest.builder()
                .shardIterator(shardIterator)
                .limit(1000)
                .build()
        )
        println("===============")
        response.records().map {
                record ->
            println("Record Data :  ${record.data().asByteArray()} , Record Seq : ${record.sequenceNumber()}")
        }
    }

//    fun consumeRecord(){
//        val kinesisClient = roleArn.config()
//
//        val shardResponse = kinesisClient?.getShardIterator(
//            GetShardIteratorRequest.builder()
//                .streamName("KRDEV-Server-Log-Stream")
//                .build()
//        )
//
//        SubscribeToShardRequest.builder()
//            .consumerARN(arn)
//            .shardId("shard_1")
//            .startingPosition { s -> s.type(ShardIteratorType.LATEST) }
//            .build()

//        return kinesisClient?.getRecords(
//            GetRecordsRequest.builder()
//                .shardIterator()
//        )
//    }
}
