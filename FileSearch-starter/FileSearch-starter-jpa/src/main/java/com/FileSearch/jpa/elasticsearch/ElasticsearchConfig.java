//package com.FileSearch.jpa.elasticsearch;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.net.ssl.SSLContext;
//
//@Configuration
//public class ElasticsearchConfig {
//
//    @Bean
//    public RestHighLevelClient createClient() {
//        try {
//            // Disable SSL verification
//            SSLContext sslContext = SSLContextBuilder.create()
//                    .loadTrustMaterial((chain, authType) -> true)
//                    .build();
//
//            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "uiswc-ij51BBcC-nxNUh"));
//
//            RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
//                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                            .setSSLContext(sslContext)
//                            .setDefaultCredentialsProvider(credentialsProvider));
//
//            return new RestHighLevelClient(builder);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create Elasticsearch client", e);
//        }
//    }
//}
