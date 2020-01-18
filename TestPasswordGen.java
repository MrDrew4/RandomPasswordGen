import java.util.Random;
import java.util.Arrays;

public class TestPasswordGen {

	/**
	 * Tests for the buildPassword method.
	 *
	 * For random methods, it is critical that you follow the algorithm exactly. If
	 * not, you will draw different random values than are expected.
	 *
	 * @return true if tests pass, false otherwise.
	 */
	public static boolean testBuildPassword() {
		System.out.println("Starting testBuildPassword...");
		Random rand = new Random(Config.SEED);
		// Following tests work assuming Config.SEED is 12344321
		String pass1 = PasswordGen.buildPassword(5, 10, rand);
		if (!pass1.equals("BqRNgs[6O")) {
			System.out.println("\tTest Password 1 failed!");
			return false;
		}

		String pass2 = PasswordGen.buildPassword(10, 10, rand);
		if (!pass2.equals("0;f#3VP0ok")) {
			System.out.println("\tTest Password 2 failed!");
			return false;
		}
		System.out.println("...Done testBuildPassword");
		return true;

	}

	public static boolean testGenPasswords() {
		System.out.println("Starting testGenPasswords...");
		Random rand = new Random(Config.SEED);
		// Following tests work assuming Config.SEED is 12344321

		// Test 1: Bad rules array
		int ret1 = PasswordGen.genPasswords(new String[1], null, 5, 10, rand);
		if (ret1 != -1) {
			System.out.println("\tTest 1 failed!");
			return false;
		}

		// Test 2: All rules; length 5 to 10
		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			rules[Config.HAS_UC] = true;
			rules[Config.HAS_LC] = true;
			rules[Config.HAS_DIGIT] = true;
			rules[Config.HAS_PUNCT] = true;
			String[] passwords = new String[5];
			String[] expVal = { "0;f#3VP0ok", "wnM4\"3*bk", "8Ppwjy!M", "pZ55\\}N", "FH;EA1r{<J" };
			int ret = PasswordGen.genPasswords(passwords, rules, 5, 10, rand);
			if (ret != 1 || !Arrays.equals(passwords, expVal)) {
				System.out.println("\tTest 2 failed!");
				System.out.println("Return val: " + ret + "; expected 1.");
				System.out.println("Passwords generated:\n" + Arrays.toString(passwords));
				System.out.println("Passwords expected:\n" + Arrays.toString(expVal));
				return false;
			}
		}

		// Test 3: No rules; length 1 to 10
		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			rules[Config.HAS_UC] = false;
			rules[Config.HAS_LC] = false;
			rules[Config.HAS_DIGIT] = false;
			rules[Config.HAS_PUNCT] = false;
			String[] passwords = new String[4];
			String[] expVal = { "r", "BW,3X($Pni", "E*S", ",D" };
			int ret = PasswordGen.genPasswords(passwords, rules, 1, 10, rand);
			if (ret != 1 || !Arrays.equals(passwords, expVal)) {
				System.out.println("\tTest 3 failed!");
				System.out.println("Return val: " + ret + "; expected 1.");
				System.out.println("Passwords generated:\n" + Arrays.toString(passwords));
				System.out.println("Passwords expected:\n" + Arrays.toString(expVal));
				return false;
			}
		}

		// Test 4: Only digit rule; length 4 to 4
		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			rules[Config.HAS_UC] = false;
			rules[Config.HAS_LC] = false;
			rules[Config.HAS_DIGIT] = true;
			rules[Config.HAS_PUNCT] = false;
			String[] passwords = new String[3];
			String[] expVal = { "n]*5", "/2!m", "s3}s" };
			int ret = PasswordGen.genPasswords(passwords, rules, 4, 4, rand);
			if (ret != 1 || !Arrays.equals(passwords, expVal)) {
				System.out.println("\tTest 4 failed!");
				System.out.println("Return val: " + ret + "; expected 1.");
				System.out.println("Passwords generated:\n" + Arrays.toString(passwords));
				System.out.println("Passwords expected:\n" + Arrays.toString(expVal));
				return false;
			}
		}

		System.out.println("...Done testGenPasswords");
		return true;
	}

	public static boolean testIsValidPassword() {
		System.out.println("Starting testIsValidPassword...");

		// TODO -- add at least 2 tests. For 2 of the tests, the first argument for the
		// calls to
		// isValidPassword must be String literals.
		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			int minLen = 6;
			int maxLen = 20;
			rules[Config.HAS_UC] = true;
			rules[Config.HAS_LC] = true;
			rules[Config.HAS_DIGIT] = true;
			rules[Config.HAS_PUNCT] = true;
			String[] passwords = new String[7];

			passwords[0] = "CS200!?computer";
			passwords[1] = "CS200computer";
			passwords[2] = "CScomputer";
			passwords[3] = "computer";
			passwords[4] = "ABCDEF";
			passwords[5] = "CS200!?computercomputer";
			passwords[6] = "!Cc0";

			int[] expVal = { 1, 0, 0, 0, 0, 0, 0 };

			for (int i = 0; i < passwords.length; i++) {
				if (PasswordGen.isValidPassword(passwords[i], rules, minLen, maxLen) != expVal[i]) {
					System.out.println("\tTest isValidPassword failed!");
//                     System.out.println("Return val: " + ret + "; expected 1.");
//                     System.out.println("Passwords generated:\n" + Arrays.toString(passwords));
//                     System.out.println("Passwords expected:\n" + Arrays.toString(expVal));
					System.out.println("Hi" + i + PasswordGen.isValidPassword(passwords[i], rules, minLen, maxLen));
				}
			}

		}

		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			int minLen = 1;
			int maxLen = 1;
			rules[Config.HAS_UC] = true;
			rules[Config.HAS_LC] = true;
			rules[Config.HAS_DIGIT] = true;
			rules[Config.HAS_PUNCT] = true;

			PasswordGen.isValidPassword("TheWord12!", rules, minLen, maxLen);

		}
		{
			boolean[] rules = new boolean[Config.RULES_LEN];
			int minLen = 6;
			int maxLen = 20;
			rules[Config.HAS_UC] = false;
			rules[Config.HAS_LC] = true;
			rules[Config.HAS_DIGIT] = false;
			rules[Config.HAS_PUNCT] = false;

			PasswordGen.isValidPassword("empty", rules, minLen, maxLen);

		}

		System.out.println("...Done testIsValidPassword");

		return true;
	}

	public static void main(String[] args) {

		testBuildPassword();
		testGenPasswords();
		testIsValidPassword();
	}

}
