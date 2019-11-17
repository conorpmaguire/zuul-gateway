package com.bloomburg.resource.service.impl;

import com.bloomburg.resource.exception.MalformedExpressionException;
import com.bloomburg.resource.service.ComputationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.constraints.NotNull;

@Service
public class ComputationServiceImpl implements ComputationService {

    private static final Logger logger = LoggerFactory.getLogger(ComputationServiceImpl.class);

    public Integer compute(@NotNull String expression) {

        logger.debug("Evaluating expression [{}]", expression);

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        Integer val = null;

        try {
            val = (Integer) engine.eval(expression);
        } catch (ScriptException scriptException) {
            logger.error("Error evaluating expression [{}]!", expression);
            throw new MalformedExpressionException(scriptException);
        }
        return val;
    }
}
