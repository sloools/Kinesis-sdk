package com.example.kinesis.kinesisClient.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.kinesis.KinesisClient
import software.amazon.awssdk.services.sts.StsClient
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest


@Configuration
class RoleArn() {
    val roleArn = "arn:aws:iam::883976656071:role/role-server-log-stream"

    fun getStsClient(): StaticCredentialsProvider? {
//        val profile = ProfileCredentialsProvider.create("coinfra-dev")

        val stsClient =  StsClient.builder()
//            .credentialsProvider(profile)
//            .region(Region.AP_NORTHEAST_2)
            .build()

        val roleRequest = AssumeRoleRequest.builder()
            .roleArn(roleArn)
            .roleSessionName("coinfra-krdev")
            .build()

        val credentials = stsClient.assumeRole(roleRequest).credentials()

        val sessionCredentials = AwsSessionCredentials.create(
            credentials.accessKeyId(),
            credentials.secretAccessKey(),
            credentials.sessionToken()
        )

        return StaticCredentialsProvider.create(sessionCredentials)

    }

    @Bean
    fun config(): KinesisClient? {

        return KinesisClient.builder()
            .credentialsProvider(getStsClient())
            .region(Region.AP_NORTHEAST_2)
            .build()
    }
}