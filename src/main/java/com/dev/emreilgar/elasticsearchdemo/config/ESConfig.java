package com.dev.emreilgar.elasticsearchdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dev.emreilgar.elasticsearchdemo.repository")
@ComponentScan(basePackages = { "com.dev.emreilgar.elasticsearchdemo" })
public class ESConfig extends ElasticsearchConfiguration {
    //normal de 9200 dana yağa kalkıyor, fakat değiştirmek istersen application.propertiesden çekmek için yazdık
    @Value("${elasticsearch.url}")
    private String url;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(url)
                .build();
    }
}
