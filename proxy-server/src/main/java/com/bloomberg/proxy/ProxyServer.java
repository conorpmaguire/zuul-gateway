package com.bloomberg.proxy;

import com.bloomberg.proxy.filter.ProxyServerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ProxyServer {
    public static void main(String[] args) {
        SpringApplication.run(ProxyServer.class, args);
    }

    @Bean
    public ProxyServerFilter getProxyServerFilter() {
        return new ProxyServerFilter();
    }
}