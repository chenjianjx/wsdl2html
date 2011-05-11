package org.jaxws.wsdl2bytecodes.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.jaxws.util.lang.RandomStringUtils;
import org.jaxws.util.os.SystemProcessException;
import org.jaxws.util.os.SystemProcessUtils;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2ByteCodes {

	public static ByteCodePackage generate(String byteCodesDirParent, String wsdlUrl) throws SystemProcessException {
		String currentTime = formatDate(new Date(), "yyyyMMddHHmmssSSS");
		File byteCodeDir = createByteCodesDir(byteCodesDirParent, currentTime);
		String packageName = generatePakcageName(currentTime);

		byteCodeDir.mkdirs();
		doWsImport(byteCodeDir.getAbsolutePath(), wsdlUrl, packageName);
		return new ByteCodePackage(byteCodeDir, packageName);
	}

	private static File createByteCodesDir(String byteCodeDirParent, String currentTime) {
		String nextDir = RandomStringUtils.getRandomLetters(10);
		return new File(byteCodeDirParent + "/" + currentTime + "/" + nextDir);
	}

	static String generatePakcageName(String currentTime) {

		List<String> fragments = new ArrayList<String>();
		fragments.add("wsdl2bytecodes" + currentTime); // first level
		for (int i = 0; i < 9; i++) { // the next 9 levels
			// each level may have 3 to 13 words
			fragments.add(RandomStringUtils.getRandomLetters(3 + RandomUtils.nextInt(10)));
		}
		return StringUtils.join(fragments, ".");
	}

	private static void doWsImport(String outputDir, String wsdlUrl, String packageName) throws SystemProcessException {

		List<String> cmdList = new ArrayList<String>();
		if (isWindows()) {
			cmdList.add("cmd.exe");
			cmdList.add("/c");
		}

		cmdList.add("wsimport");
		cmdList.add("-B-XautoNameResolution");
		cmdList.add("-s");
		cmdList.add(outputDir);
		cmdList.add("-d");
		cmdList.add(outputDir);
		cmdList.add("-p");
		cmdList.add(packageName);
		cmdList.add("-verbose");
		cmdList.add(wsdlUrl);

		String[] cmdArray = new String[cmdList.size()];
		cmdList.toArray(cmdArray);
		SystemProcessUtils.exec(cmdArray);

	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

	static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
