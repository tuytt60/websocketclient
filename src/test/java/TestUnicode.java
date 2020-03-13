import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by lwg on 2020/3/13.
 */
public class TestUnicode {
    public static void main(String[] args) {
        String unescapeJava = StringEscapeUtils.unescapeJava("\\u9a71\\u52a8\\u5668");
        System.out.println(unescapeJava);
    }
}
