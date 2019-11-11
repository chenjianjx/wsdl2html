package org.jaxws.wsdl2bytecodes.service;

import com.sun.tools.ws.wscompile.WsimportTool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.jaxws.util.lang.RandomStringUtils;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2ByteCodes {

	public static ByteCodePackage generate(String byteCodesDirParent, String wsdlUrl) throws WsdlImportException {
		String currentTime = formatDate(new Date(), "yyyyMMddHHmmssSSS");
		File byteCodeDir = createByteCodesDir(byteCodesDirParent, currentTime);
		String packageName = generatePakcageName(currentTime);
		byteCodeDir.mkdirs();
		doWsImport(byteCodeDir.getAbsolutePath(), wsdlUrl, packageName);
		doCompile(byteCodeDir);
		System.out.println("Java files generated at: " + byteCodeDir);
		return new ByteCodePackage(byteCodeDir, packageName);
	}

	private static void doCompile(File sourceDir) throws WsdlImportException {
		System.out.println("Compiling stubs");
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Collection<File> files = FileUtils.listFiles(sourceDir, new String[] { "java" }, true);
		// there is one file that you may encounter compliation errors
		removeWebServiceClientFile(files);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);
		List<String> compilerOptions = Arrays.asList("-source", "1.8", "-target", "1.8");
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, compilerOptions, null, compilationUnits);
		boolean success = task.call();
		try {
			fileManager.close();
		} catch (IOException e) {
			// ignore it
		}
		if (!success) {
			StringBuffer errors = new StringBuffer();
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				errors.append(String.format("Compilation Error on line %d in %s", diagnostic.getLineNumber(), diagnostic)).append("\n");
			}
			throw new WsdlImportException(errors.toString());
		}
	}

	private static void removeWebServiceClientFile(Collection<File> files) {
		File theFile = null;
		for (File file : files) {
			String content = null;
			try {
				content = FileUtils.readFileToString(file);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
			if (content.contains("@WebServiceClient")) {
				theFile = file;
				break;
			}
		}
		if (theFile != null) {
			files.remove(theFile);
		}
	}

	private static File createByteCodesDir(String byteCodeDirParent, String currentTime) {
		String nextDir = RandomStringUtils.getRandomLetters(10);
		return new File(byteCodeDirParent + "/" + currentTime + "/" + nextDir);
	}

	static String generatePakcageName(String currentTime) {

		List<String> fragments = new ArrayList<String>();
		fragments.add("wsdl2bytecodes" + currentTime); // first level
		for (int i = 0; i < 2; i++) { // the next 9 levels
			// each level may have 3 to 13 words
			fragments.add(RandomStringUtils.getRandomLetters(3 + RandomUtils.nextInt(10)));
		}
		return StringUtils.join(fragments, ".");
	}

	//See:  https://stackoverflow.com/a/16370962
	private static void doWsImport(String outputDir, String wsdlUrl, String packageName) throws WsdlImportException {




		File jaxbFile = copyDefaultJaxbFile(outputDir);

		List<String> argList = new ArrayList<String>();
		argList.add("-B-XautoNameResolution");
		argList.add("-b");
		argList.add(jaxbFile.getAbsolutePath());
		argList.add("-s");
		argList.add(outputDir);
		argList.add("-d");
		argList.add(outputDir);
		// We will do the compilation manually, otherwise there may be problems
		// if there are multiple JDKS installed. wsimport doesn't allow you to
		// pass jdk-version option. JAX-WS sucks.
		argList.add("-Xnocompile");
		if (packageName != null) {
			argList.add("-p");
			argList.add(packageName);
		}
		argList.add("-verbose");
		argList.add("-extension");
		argList.add(wsdlUrl);

		String[] argArray = new String[argList.size()];
		argList.toArray(argArray);

		System.out.println(StringUtils.join(argArray, " "));


		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		boolean result = new WsimportTool(outputStream).run(argArray);
		String output = new String(outputStream.toByteArray());
		if (!result) {
			throw new WsdlImportException(output);
		}
		if (output.contains("Two declarations cause a collision in the ObjectFactory class")) {
			throw new DeclarationCollisionException(output);
		}

	}

	private static File copyDefaultJaxbFile(String outputDir) {

		File generatedJaxbFile = new File(outputDir, "jaxb.xml");
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			String defaultJaxbClasspath = "/default-jaxb.xml";
			inputStream = Wsdl2ByteCodes.class.getResourceAsStream(defaultJaxbClasspath);
			outputStream = new FileOutputStream(generatedJaxbFile);
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}

		return generatedJaxbFile;
	}


	static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static final class DeclarationCollisionException extends WsdlImportException {
		public DeclarationCollisionException(String readableReason) {
			super(readableReason);
		}

		private static final long serialVersionUID = 7625306326132684237L;
	}
}