package com.karafra.bitchutedl.exceptions;

/**
 * Exception thrown when xpath is not present in DOM.
 * 
 * @author Karafra
 * @since 1.0
 * @version 1.0
 */
public class WrongXPathException extends ApiRuntimeException {
    public WrongXPathException(String xpath) {
        super(xpath);
    }
}
