package org.jaxws.bytecodes2stub.service;

import static org.jaxws.util.lang.ClassNameUtils.getRelativeDir;
import static org.jaxws.util.lang.ClassNameUtils.toSimpleFileName;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ByteCodePackageClassLoader extends ClassLoader {
	private ByteCodePackage byteCodePackage;

	public ByteCodePackageClassLoader(ClassLoader parent, ByteCodePackage byteCodePackage) {
		super(parent);
		this.byteCodePackage = byteCodePackage;
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(name);
		if (clazz != null) {
			return clazz;
		}
		clazz = letParentLoad(name);
		if (clazz != null) {
			return clazz;
		}
		clazz = loadFromFileSystem(name);
		return clazz;
	}

	private Class<?> loadFromFileSystem(String name) throws ClassFormatError {
		Class<?> clazz;
		File classDir = appendDir(byteCodePackage.getDir(), getRelativeDir(name));
		String fileName = toSimpleFileName(name);
		byte[] classData = readFileToBytes(new File(classDir, fileName));
		clazz =  defineClass(name, classData, 0, classData.length);
		return clazz;
	}

	private Class<?> letParentLoad(String name) {

		try {
			return getParent().loadClass(name);
		} catch (ClassNotFoundException e) {
			return null;
		}

	}

	static File appendDir(File rootDir, String relativePath) {
		return new File(rootDir.getAbsolutePath() + "/" + relativePath);
	}

	private byte[] readFileToBytes(File classFile) {
		try {
			return FileUtils.readFileToByteArray(classFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
