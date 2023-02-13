package we;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> keyToAnagrams = new HashMap<>();

		for (final String str : strs) {
			// convert string to char array
			char[] chars = str.toCharArray();
			System.out.print("NEXT WORD :"+ Arrays.toString(chars)+"      ");
			// sort char array
			Arrays.sort(chars);
			String key = String.valueOf(chars);
			System.out.println("KEY :"+ Arrays.toString(chars));
			// if the key sorted char array is exist add it to list
			keyToAnagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
			System.out.println("RESULT" + Arrays.toString(keyToAnagrams.get(key).toArray()));
		}

		return new ArrayList<>(keyToAnagrams.values());
	}
}
