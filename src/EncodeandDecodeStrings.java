import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeandDecodeStrings {

    /**
     * Encodes a list of strings to a single string
     */
    public String encode(List<String> list) {
        StringBuilder encoded = new StringBuilder();

        for (String s : list) {
            encoded.append(s.length()).append('/').append(s);
        }

        return encoded.toString();
    }

    /**
     * Decodes a single string to a list of strings
     */
    public List<String> decode(String s) {
        List<String> decoded = new ArrayList<>();

        for (int i = 0; i < s.length();) {
            int slash = s.indexOf('/', i);
            int length = Integer.parseInt(s.substring(i, slash));
            i = slash + length + 1;
            decoded.add(s.substring(slash + 1, i));
        }

        return decoded;
    }
    

    public static void main(String[] args) {
        EncodeandDecodeStrings t = new EncodeandDecodeStrings();

        String [] input = {"lint", "code", "love", "you"};
        List<String> list = Arrays.asList(input);
        System.out.println(list);
        String encode = t.encode(list);
        System.out.println(encode);
        List<String> decode = t.decode(encode);
        System.out.println(decode);
        
    }
}
