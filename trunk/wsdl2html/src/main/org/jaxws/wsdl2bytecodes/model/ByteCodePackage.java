package org.jaxws.wsdl2bytecodes.model;

import java.io.File;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ByteCodePackage {

	/**
	 * note: the dir of "/home/temp/classes/com.sun.helloworld" would be
	 * "/home/temp/classes/"
	 * 
	 */
	private File dir;
	private String packageName;

	public ByteCodePackage() {

	}

	public ByteCodePackage(File dir, String packageName) {
		super();
		this.dir = dir;
		this.packageName = packageName;
	}

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "ByteCodePackage [dir=" + dir + ", packageName=" + packageName + "]";
	}

	
	
}
