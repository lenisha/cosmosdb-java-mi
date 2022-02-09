package com.azure.cosmosdemo.demo;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.spring.data.cosmos.core.ReactiveCosmosTemplate;
import com.azure.spring.data.cosmos.core.convert.MappingCosmosConverter;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.azure.identity.ManagedIdentityCredentialBuilder;


@Configuration
public class CosmosConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CosmosConfiguration.class);
    
    @Value("${azure.cosmos.uri}")
    private String endpoint;

    @Bean
    public CosmosClientBuilder cosmosClient() {
    	DirectConnectionConfig directConnectionConfig = DirectConnectionConfig.getDefaultConfig();
        GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();
        
        logger.info(" ----  Trying to Connection with ManagedIdentityCredentialBuilder ");
        return new CosmosClientBuilder()
                .endpoint(endpoint)
				.credential(new ManagedIdentityCredentialBuilder().build())
                .gatewayMode(gatewayConnectionConfig);
     }

     @EnableReactiveCosmosRepositories(basePackages = "com.azure.cosmosdemo.demo",
                                       reactiveCosmosTemplateRef = "databaseTemplate")
     public class DatabaseConfiguration extends AbstractCosmosConfiguration {

        @Bean
        public ReactiveCosmosTemplate databaseTemplate(CosmosAsyncClient cosmosAsyncClient,
                                                              CosmosConfig cosmosConfig,
                                                              MappingCosmosConverter mappingCosmosConverter) {
            return new ReactiveCosmosTemplate(cosmosAsyncClient, "testdb", cosmosConfig, mappingCosmosConverter);
        }

        @Override
        protected String getDatabaseName() {
            return "testdb";
        }
    }

}
