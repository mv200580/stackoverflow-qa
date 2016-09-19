import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * See also - Обработка строк в Java. Часть II: Pattern, Matcher
 * https://habrahabr.ru/post/260773/
 *
 * Created by smv on 19/09/16.
 */
public class RegexMatcher {

    @Test
    public void run() {
        Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W]).{8,})");

        Matcher matcher = pattern.matcher("A#df2222");
        System.out.println(matcher.find());

    }


}
