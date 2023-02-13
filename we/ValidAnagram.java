package we;

public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		// if the length of both string is not the same, return false
		if (s.length() != t.length())
			return false;

		// define temporary space to store if each char is existed 
		int[] count = new int[128];

		// set 1 in the index of each char
		for (final char c : s.toCharArray())
			++count[c];

		// minus 1 in the index of each char, and than compare whether the value is less than 0.
		for (final char c : t.toCharArray())
			if (--count[c] < 0)
				return false;

		return true;
	}
}
