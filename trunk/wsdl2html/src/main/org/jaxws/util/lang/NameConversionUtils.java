package org.jaxws.util.lang;


/**
 * 
 * @author chenjianjx
 * 
 */
public class NameConversionUtils {

	/**
	 * helloWorld => hello-world HelloWorld => hello-world
	 * 
	 * @param camelStyle
	 * @return
	 */
	public static String camelToHyphen(String camelStyle) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < camelStyle.length(); i++) {
			char c = camelStyle.charAt(i);
			if (!isLetter(c)) {
				sb.append(c);
				continue;
			}
			if (isUpperCaseLetter(c)) {
				// no hyphen at the beginning of the world
				if (i != 0) {
					sb.append("-");
				}

				sb.append(lowser(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static char lowser(char c) {
		return (char) (c + 32);
	}

	private static boolean isLetter(char c) {
		return isLowerCaseLetter(c) || isUpperCaseLetter(c);
	}

	static boolean isLowerCaseLetter(char c) {
		return c <= 'z' && c >= 'a';
	}

	static boolean isUpperCaseLetter(char c) {
		return c <= 'Z' && c >= 'A';
	}
}
