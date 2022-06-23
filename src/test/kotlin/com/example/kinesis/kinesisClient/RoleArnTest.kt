package com.example.kinesis.kinesisClient

import com.example.kinesis.kinesisClient.config.RoleArn
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RoleArnTest @Autowired constructor(
    private val roleArn: RoleArn
) {

    @Test
    fun roleArnTest(){
//        val roleRequest = roleArn.getRoleRequest()

//        println(roleRequest?.roleSessionName())
    }
}