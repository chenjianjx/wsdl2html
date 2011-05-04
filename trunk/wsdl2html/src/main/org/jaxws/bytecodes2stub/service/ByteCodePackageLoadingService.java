package org.jaxws.bytecodes2stub.service;

import static java.lang.Thread.currentThread;
import static org.apache.commons.lang.StringUtils.stripEnd;
import static org.apache.commons.lang.StringUtils.stripStart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.jaxws.util.lang.ClassNameUtils;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ByteCodePackageLoadingService {

	public static List<Class<?>> loadAll(ByteCodePackage byteCodePackage)  {
		List<Class<?>> allClasses = new ArrayList<Class<?>>();
		ClassLoader classLoader = new ByteCodePackageClassLoader(currentThread().getContextClassLoader(), byteCodePackage);
		List<String> classNames = getAllClassNames(byteCodePackage);
		for (String className : classNames) {
			Class<?> clazz = loadClass(classLoader, className);
			allClasses.add(clazz);
		}
		return allClasses;
	}

	private static Class<?> loadClass(ClassLoader classLoader, String className)  {
		try {
			return classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<String> getAllClassNames(ByteCodePackage byteCodePackage) {
		List<String> classNames = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<File> files = (List<File>) FileUtils.listFiles(byteCodePackage.getDir(), new String[] { "class" }, true);
		File rootDir = byteCodePackage.getDir();
		for (File classFile : files) {
			String relativeDir = getRelativeDir(rootDir, classFile);
			String packageName = ClassNameUtils.toPackageName(relativeDir);
			String className = packageName + "." + FilenameUtils.getBaseName(classFile.getName());
			classNames.add(className);
		}
		return classNames;
	}

	static String getRelativeDir(File rootDir, File file) {
		String filePath = file.getAbsolutePath();
		String rootPath = rootDir.getAbsolutePath();
		String relative = StringUtils.substringBetween(filePath, rootPath, file.getName());
		relative = FilenameUtils.separatorsToUnix(relative);
		return stripEnd(stripStart(relative, "/"), "/");
	}

}
