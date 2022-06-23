package com.example.kinesis

import com.example.kinesis.sdk.config.AwsProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AwsProperties::class)
class KinesisApplication

fun main(args: Array<String>) {
    runApplication<KinesisApplication>(*args)
}
