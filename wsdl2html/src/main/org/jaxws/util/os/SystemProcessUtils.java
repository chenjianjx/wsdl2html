package org.jaxws.util.os;

import java.io.IOException;

import org.jaxws.util.io.StreamPrinter;

/**
 * Spawning a process from the OS
 * 
 * @author chenjianjx
 * 
 */
public class SystemProcessUtils {

	public static void exec(String[] cmdArray) throws SystemProcessException {

		try {
			Process p = Runtime.getRuntime().exec(cmdArray);
			printErrorStream(p);
			printInputStream(p);

			int returningCode = p.waitFor();

			if (returningCode != 0) {
				throw new SystemProcessException("Process returning code = " + returningCode);
			}
		} catch (IOException e) {
			throw new SystemProcessException(e);
		} catch (InterruptedException e) {
			throw new SystemProcessException(e);
		}
	}

	private static void printInputStream(Process p) {
		if (p.getInputStream() != null) {
			StreamPrinter infoStreamPrinter = new StreamPrinter(p.getInputStream(), "[INFO]", System.out);
			infoStreamPrinter.start();
		}
	}

	private static void printErrorStream(Process p) {
		if (p.getErrorStream() != null) {
			StreamPrinter errorStreamPrinter = new StreamPrinter(p.getErrorStream(), "[ERROR]", System.out);
			errorStreamPrinter.start();
		}
	}

}
