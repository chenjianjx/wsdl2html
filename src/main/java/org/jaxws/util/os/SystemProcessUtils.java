package org.jaxws.util.os;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jaxws.util.io.StreamPrinter;

/**
 * Spawning a process from the OS
 * 
 * @author chenjianjx
 * 
 */
public class SystemProcessUtils {

	/**
	 * execture a program and returns console output
	 */
	public static String exec(String[] cmdArray) throws SystemProcessException {

		try {
			Process p = Runtime.getRuntime().exec(cmdArray);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			Thread errStreamThread = collectErrorStream(p, output);
			Thread inputStreamThread = collectInputStream(p, output);
			int returningCode = p.waitFor();
			int timeout = 60000;
			if (errStreamThread != null) {
				errStreamThread.join(timeout);
			}
			if (inputStreamThread != null) {
				inputStreamThread.join(timeout);
			}
			String consoleOutput = new String(output.toByteArray());
			System.out.println(consoleOutput);
			if (returningCode != 0) {
				throw new SystemProcessException(returningCode, consoleOutput);
			}
			return consoleOutput;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	private static StreamPrinter collectInputStream(Process p, OutputStream out) {
		if (p.getInputStream() != null) {
			StreamPrinter infoStreamPrinter = new StreamPrinter(p.getInputStream(), "[INFO]", out);
			infoStreamPrinter.start();
			return infoStreamPrinter;
		}
		return null;
	}

	private static StreamPrinter collectErrorStream(Process p, OutputStream out) {
		if (p.getErrorStream() != null) {
			StreamPrinter errorStreamPrinter = new StreamPrinter(p.getErrorStream(), "[ERROR]", out);
			errorStreamPrinter.start();
			return errorStreamPrinter;
		}
		return null;
	}

}