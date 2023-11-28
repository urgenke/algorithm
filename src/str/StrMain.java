package str;

/**
 * @author : mocun
 * @since : 2023/11/23
 */
public class StrMain {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len2 == 0) {
            return len1;
        }

        if (len1 == 0) {
            return len2;
        }


        // todo
        return -1;
    }
}
