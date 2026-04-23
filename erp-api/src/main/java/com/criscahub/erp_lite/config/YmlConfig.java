package com.criscahub.erp_lite.config;

import com.criscahub.erp_lite.persistence.aws.models.AwsConfigModel;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(AwsConfigModel.class)
@PropertySource(value = "classpath:aws/aws.yml",factory = YamlPropertySourceFactory.class)
public class YmlConfig {



}
