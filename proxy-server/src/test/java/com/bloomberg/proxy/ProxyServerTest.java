package com.bloomberg.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Simple integration test for the proxy server. This test will start the proxy server using
 * the port defined in application.properties, however you will need to start the resource
 * server manually in order to run this test.
 *
 * @author Conor
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProxyServerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProxyServerTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testProxyServerWithMultiplication() {
        String testExpression1 = "12*3";
        ResponseEntity<Integer> response = restTemplate.getForEntity(buildURI(testExpression1), Integer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new Integer(36), response.getBody());
    }

    @Test
    public void testProxyServerWithAddition() throws Exception {
        String testExpression1 = "8+8";
        ResponseEntity<Integer> response = restTemplate.getForEntity(buildURI(testExpression1), Integer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new Integer(16), response.getBody());
    }

    private URI buildURI(String expression) {
        StringBuffer queryString = new StringBuffer("/resource/compute?expr=").append(expression);
        URI uri = URI.create(queryString.toString());
        return uri;
    }

    private String encodeExpression(String expression) throws UnsupportedEncodingException{
        expression.replaceAll("\\+", "%2b");
        return URLEncoder.encode(expression, StandardCharsets.UTF_8.toString());
    }
}