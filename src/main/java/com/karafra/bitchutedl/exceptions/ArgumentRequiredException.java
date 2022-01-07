package com.karafra.bitchutedl.exceptions;

/**
 * Exception thrown when required argument is missing, status code 400.
 * @since 1.0
 * @version 1.0
 * @category exception
 * @author Karafra
 */
public class ArgumentRequiredException extends ApiRuntimeException {

    /**
     * Constructor used if only name of argument is known.
     * 
     * @param name
     *            name of argument.
     */
    public ArgumentRequiredException(String name) {
        super(String.format("Argument with name '%s' was not provided!", name));
    }

    /**
     * Constructor used if name and required type is known.
     * 
     * @param name
     *            of argument.
     * @param type
     *            type of argument.
     */
    public ArgumentRequiredException(String name, Class<?> type) {
        super(String.format("Argument with name '%s' of type [%s] was not provided!", name, type));
    }
}
