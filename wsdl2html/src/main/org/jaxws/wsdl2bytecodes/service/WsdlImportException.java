package org.jaxws.wsdl2bytecodes.service;

/**
 * 
 * @author chenjianjx
 * 
 */
public class WsdlImportException extends Exception {

    private static final long serialVersionUID = -1197961204211671600L;

    // the reason should be eligible to be read by end-user
    private String readableReason;

    public WsdlImportException(String readableReason) {
        super();
        this.readableReason = readableReason;
    }

    public String getReadableReason() {
        return readableReason;
    }

}
