package org.jaxws.util.os;

/**
 * 
 * @author chenjianjx
 * 
 */

public class SystemProcessException extends Exception {

    private int returnCode;
    private String consoleOutput;

    /**
	 * 
	 */
    private static final long serialVersionUID = -6904952219344675845L;

    public SystemProcessException(int returnCode, String consoleOutput) {
        super();
        this.returnCode = returnCode;
        this.consoleOutput = consoleOutput;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getConsoleOutput() {
        return consoleOutput;
    }

}
