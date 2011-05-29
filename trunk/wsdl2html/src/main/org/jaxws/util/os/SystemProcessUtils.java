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

    public static void exec(String[] cmdArray) throws SystemProcessException {

        try {
            Process p = Runtime.getRuntime().exec(cmdArray);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            correctErrorStream(p, output);
            correctInputStream(p, output);
            int returningCode = p.waitFor();

            String consoleOutput = new String(output.toByteArray());
            System.out.println(consoleOutput);

            if (returningCode != 0) {
                throw new SystemProcessException(returningCode, consoleOutput);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void correctInputStream(Process p, OutputStream out) {
        if (p.getInputStream() != null) {
            StreamPrinter infoStreamPrinter = new StreamPrinter(p.getInputStream(), "[INFO]", out);
            infoStreamPrinter.start();
        }
    }

    private static void correctErrorStream(Process p, OutputStream out) {
        if (p.getErrorStream() != null) {
            StreamPrinter errorStreamPrinter = new StreamPrinter(p.getErrorStream(), "[ERROR]", out);
            errorStreamPrinter.start();
        }
    }

}
