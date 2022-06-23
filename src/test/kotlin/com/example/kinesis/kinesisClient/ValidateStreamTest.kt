package com.example.kinesis.kinesisClient


import com.example.kinesis.kinesisClient.config.RoleArn
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import software.amazon.awssdk.services.kinesis.model.DescribeStreamRequest

@SpringBootTest
class ValidateStreamTest @Autowired constructor(
    val roleArn: RoleArn
) {
    @Test
    fun validateTest(){
        val streamName = "KRDEV-Server-Log-Stream"
//        val streamName = "local_kinesis_test"

        val describeStreamRequest = DescribeStreamRequest.builder().streamName(streamName).build()
        val describeStreamResponse = roleArn.config()?.describeStream(describeStreamRequest)

        if(describeStreamResponse?.streamDescription()?.streamStatus().toString() == "ACTIVE"){
            println("ACTIVE%%%%%%")
        }else{
            println("NOT ACTIVE########")
        }
    }

}