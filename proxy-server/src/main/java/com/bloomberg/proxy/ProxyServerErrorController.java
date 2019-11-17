package com.bloomberg.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom controller to provide custom routing for Zuul which forwards to '/error' in the event of
 * the downstream service being unavailable.
 *
 * @author Conor
 */
@Controller
public class ProxyServerErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/json")
    public @ResponseBody ResponseEntity error(HttpServletRequest request) {
        final String errorMessage = "Cannot connect to downstream resource server! Please try again later!";
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorMessage);
    }
}
