package com.bloomberg.proxy;

import com.bloomberg.proxy.filter.ProxyServerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * This is the proxy or gateway server which will forward all requests to a running ProxyServer
 * and will return the evaluated expression.
 *
 * @author Conor
 */
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