package com.bloomburg.resource.exception;

/**
 * Customized exception for bad expressions
 *
 * @author Conor
 */
public class MalformedExpressionException extends RuntimeException {

    /**
     * Custom runtime exception wrapping a checked exception
     *
     * @param throwable - the wrapped exception
     */
    public MalformedExpressionException(Throwable throwable) {
        super(throwable);
    }
}
