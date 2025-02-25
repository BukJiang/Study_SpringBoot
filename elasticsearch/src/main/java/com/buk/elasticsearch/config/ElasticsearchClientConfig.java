package com.buk.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO: elasticsearch配置
 *
 * @author BuK
 * @see com.buk.elasticsearch.config.DocumentTest
 * @see com.buk.elasticsearch.config.IndexTest
 * @since 2020/09/04
 */
@Configuration
public class ElasticsearchClientConfig {

    /**
     * Rest高级客户端
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 验证
        String userName = "elastic";
        String password = "elastic";
        // 凭证提供器
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password)
        );
        // Http客户端配置回调
        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        };
        // HttpHost
        HttpHost httpHost = new HttpHost("192.168.26.252", 9200, "http");
        // Rest客户端生成器
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        restClientBuilder.setHttpClientConfigCallback(httpClientConfigCallback);
        // RestHighLevelClient
        return new RestHighLevelClient(restClientBuilder);
    }
}
