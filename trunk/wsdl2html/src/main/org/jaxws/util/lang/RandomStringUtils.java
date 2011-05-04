package org.jaxws.util.lang;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 
 * @author chenjianjx
 * 
 */
public class RandomStringUtils {

	public static String getRandomLetters(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(getRandomLetter());
		}
		return sb.toString();
	}

	public static char getRandomLetter() {
		return (char) (RandomUtils.nextInt(getLetterRange()) + randomlyGetLetterA());
	}

	private static int getLetterRange() {
		return 'Z' - 'A' + 1;
	}

	private static int randomlyGetLetterA() {
		return RandomUtils.nextBoolean() ? 'A' : 'a';

	}

}
