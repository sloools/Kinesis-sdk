package com.example.kinesis.sdk.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "aws")
data class AwsProperties (
    val accessKeyId: String,
    val secretKey: String
)