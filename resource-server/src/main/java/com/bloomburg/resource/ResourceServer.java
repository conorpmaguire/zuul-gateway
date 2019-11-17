package com.bloomburg.resource;

import com.bloomburg.resource.service.ComputationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceServer {

    private static final Logger logger = LoggerFactory.getLogger(ResourceServer.class);

    @Autowired
    private ComputationService computationService;

    /**
     * http://localhost:8090/compute?expr=2*3
     *
     * @param expression
     * @return a simple inteteger which represents the evaluated expression
     */
    @RequestMapping(value="/compute")
    public Integer compute(@RequestParam(name = "expr") String expression) {
        Integer result = computationService.compute(expression);
        logger.debug("Computation service returned: [{}]", result);
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class, args);
    }
}
