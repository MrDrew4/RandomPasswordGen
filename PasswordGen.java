import java.util.Random;

/**
 * PasswordGen is a class that will generate an array of randomly generated
 * passwords with random lengths.
 *
 * This class will depend on the constants that are defined in the Config class
 * in the Config.java file. Be sure to use them and not the literals. We will
 * change the values of the constants for some of the zyBooks' tests.
 */
public class PasswordGen {

	/**
	 * This method will pseudorandomly generate a random string of characters with a
	 * random length between minLen and maxLen (inclusive). Note that, in order to
	 * pass the tests, you must follow the algorithm exactly as described.
	 *
	 * Algorithm: 1 - Draw a random int from rand, using a single call to the
	 * nextInt(int) method. The random integer should be between minLen and maxLen
	 * (both inclusive). This random int is the length of the password that will be
	 * generated. 2 - For each random character to be generated, draw a random int,
	 * using a single call to the nextInt(int) method. The random integer should be
	 * between Config.MAX_CHAR and Config.MIN_CHAR (both inclusive) and should be
	 * appended to the password string as a char.
	 *
	 * If implemented correctly, your method should perform 1 + (length of password
	 * to be generated) calls to nextInt(int). More or less calls to nextInt(int)
	 * than defined may cause your method to produce the wrong output. Calling
	 * nextInt(int) with the wrong parameter may cause your method to produce the
	 * wrong output.
	 *
	 * @param minLen The minimum length for the generated password.
	 * @param maxLen The maximum length for the generated password.
	 * @param rand   The Random object from which to draw the pseudorandom values.
	 * @return The pseudoramdomly generated password.
	 */
	public static String buildPassword(int minLen, int maxLen, Random rand) {
		int theRands = rand.nextInt(maxLen - minLen + 1) + minLen;
		String theWord = "";

		for (int i = 0; i < theRands; i++) {
			int weRands = rand.nextInt(Config.MAX_CHAR - Config.MIN_CHAR + 1) + Config.MIN_CHAR;
			char theChars = (char) weRands;
			theWord += theChars;
		}

		return theWord;
	}

	/**
     * Checks if password passes the requirements for a valid password.
     *
     * The rules boolean array contains flags to indicate if a particular rule applies. A value of 
     * true indicates that the rule applies. The length of the array should be Config.RULES_LEN. The
     * rules to consider are:
     *    index          | Rule
     * ----------------------------------------------------------------------------------------------
     * Config.HAS_UC     | Contains one upper case character (i.e. Character.isUpperCase(.) returns 
     *                   | true for at least one character in password)
     * Config.HAS_LC     | Contains one lower case character (i.e. Character.isLowerCase(.) returns 
     *                   | true for at least one character in password)
     * Config.HAS_DIGIT  | Contains one digit character (i.e. Character.isDigit(.) returns true for
     *                   | at least one character in password)
     * Config.HAS_PUNCT  | Contains one punctuation character (i.e. Character.isLetterOrDigit(.) 
     *                   | returns false for at least one character in password)
     *
     * A password is invalid if:
     * - the password parameter is null
     * - the length of the password is less than minLen or greater than maxLen
     * - for any of the rules flagged as true in the rules array, the corresponding criteria is not
     *   fulfilled (see above).
     *
     * @param password The password to validate.
     * @param rules The flags indicating with a true value what constitutes a valid password.
     * @param minLen The minimum length for the generated password.
     * @param maxLen The maximum length for the generated password.
     * @return 1 if the password is valid, 0 if the password is invalid, -1 if the rules array 
     * length does not equal Config.RULES_LEN or the rules array is null.
     */

	public static int isValidPassword(String password, boolean[] rules, int minLen, int maxLen) {
		
		boolean upper = false;
		boolean lower = false;
		boolean digit = false;
		boolean punct = false; 
		
		if (password == null || (password.length() < minLen || password.length() > maxLen)) {
			return 0;
		}
		
		else if (rules == null || (rules.length != Config.RULES_LEN)) {
			return -1;
		}
		
		
		// loop through all characters in password to see if containing 
		// uppercase, lowercase, digit, or punctuation
		
		for (int i = 0; i < password.length(); i++) {

			if (Character.isUpperCase(password.charAt(i))) {
				upper = true;
			}

			if (Character.isLowerCase(password.charAt(i))) {
				lower = true;
			}

			if (Character.isDigit(password.charAt(i))) {
				digit = true;
			}

			if (!Character.isLetterOrDigit(password.charAt(i))) {
				punct = true;
			}


		}
		
		
		// if rule applies and password does not match rule, return 0 to indicate invalid
		
		if (rules[Config.HAS_UC] == true && !upper) {
			return 0; 
		}
		if (rules[Config.HAS_LC] == true && !lower) {
			return 0; 
		}
		if (rules[Config.HAS_DIGIT] == true && !digit) {
			return 0; 
		}
		if (rules[Config.HAS_PUNCT] == true && !punct) {
			return 0; 
		}

		return 1;
	}

	/**
	 * Generates valid, pseudorandom passwords to populate the passwords array.
	 *
	 * For each cell in passwords: - Generate a pseudorandom password, using the
	 * buildPassword method. - Verify that the generated password is valid, using
	 * the isValidPassword method: - if so, store the password. - if not, generate
	 * another password (and repeat this until a valid password is generated). If at
	 * any time, the isValidPassword method returns -1, then this method should stop
	 * immediately and return -1;
	 *
	 * @param passwords The array to populate with valid, pseudorandom passwords.
	 * @param rules     The flags indicating with a true value what constitues a
	 *                  valid password.
	 * @param minLen    The minimum length for the generated password.
	 * @param maxLen    The maximum length for the generated password.
	 * @param rand      The Random object from which to draw the pseudorandom
	 *                  values.
	 * @return 1 if all the passwords are successfully generated, -1 if there is an
	 *         error when validating the password.
	 */
	public static int genPasswords(String[] passwords, boolean[] rules, int minLen, int maxLen, Random rand) {
			String randWords;
		
		
		for(int i = 0; i < passwords.length; i++) {
			
			randWords = buildPassword(minLen, maxLen, rand);
			
			while(isValidPassword(randWords, rules ,minLen,maxLen) == 0) {
				
				randWords = buildPassword(minLen, maxLen, rand);
			}

			
			if(isValidPassword(randWords, rules,minLen,maxLen) == 1) {
				passwords[i] = randWords;
			}
			
			if(isValidPassword(randWords, rules,minLen,maxLen) == -1) {
				return -1;
			}
			
			
		}

		return 1;
	}

}
