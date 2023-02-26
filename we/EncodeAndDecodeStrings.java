package we;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings {
	
	public static void main(String[] args) {
		EncodeAndDecodeStrings eds = new EncodeAndDecodeStrings();
		//TestData
		String[] data = {"abcde","abc","cde"};
		List<String> list = new ArrayList<String>();
		for(final String s : data) {
			list.add(s);
		}
		
		String decodeString = eds.encode(list);
		System.out.println("===============End Encode==================");
		List<String> encodeString = eds.decode(decodeString);
	}
	
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder encoded = new StringBuilder();

		for (final String s : strs) {
			// 문자열 길이 + "/"+ 문자열  의 형태로 인코드함.
			encoded.append(s.length()).append('/').append(s);
			System.out.println(encoded.toString());
		}

		return encoded.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> decoded = new ArrayList<>();

		for (int i = 0; i < s.length();) {
			// "/"의 인덱스 취득
			final int slash = s.indexOf('/', i);
			// 문자열의 길이 취득
			final int length = Integer.parseInt(s.substring(i, slash));
			System.out.println("Slash :"+slash + "   "+"Length :"+length);
			// 다음 검색 위치 재설정
			i = slash + length + 1;
			System.out.println("slash + length + 1 :"+i);
			// 문자열 추출해서 리스트에 추가
			decoded.add(s.substring(slash + 1, i));
			System.out.println(Arrays.toString(decoded.toArray()));
		}

		return decoded;
	}
}
