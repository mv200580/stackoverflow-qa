import org.junit.Test;

/**
 * (JAVA) Given a string, find the longest substring with the same number of vowels and consonants?
 * http://stackoverflow.com/questions/39607480/java-given-a-string-find-the-longest-substring-with-the-same-number-of-vowels
 *
 * Created by smv on 21/09/16.
 */
public class LongestSubstringWithVowelsAndConsonants {


    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public int getVowelCount(String str) {
        int res = 0;
        for(int i=0; i < str.length(); i++){
            if(isVowel(str.charAt(i))) {
                res++;
            }
        }
        return res;
    }

    public int getConsonantCount(String str) {
        int res = 0;
        for(int i=0; i < str.length(); i++){
            if(!isVowel(str.charAt(i))) {
                res++;
            }
        }
        return res;
    }

    @Test
    public void run() throws Exception {
        String string = "aasdaasggsertcwevwertweaaaaaaaaaaaddddddddddddd";
        int lengthOfString = string.length();
        String maxSub = "";
        int maxSubLength = 0;

        // find all substrings of given string
        for( int c = 0 ; c < lengthOfString ; c++ )
        {
            for( int i = 1 ; i <= lengthOfString - c ; i++ )
            {
                String sub = string.substring(c, c+i);

                // comparing count vowels and consonants
                if (getVowelCount(sub) == getConsonantCount(sub)) {
                    if (sub.length() > maxSubLength) {
                        maxSub = sub;
                        maxSubLength = sub.length();
                    }
                }
            }
        }
        System.out.println(maxSub);
    }
}
