package ch5_Stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class dataFlating {
    public static void main(String[] args) {
        String str2[][] = {
                {"a", "b"},
                {"c", "d"},
                {"e", "a"},
                {"a", "h"},
                {"i", "j"}
        };
        // 배열의 열의 크기를 알고 있으면 사용가능.
        Arrays.asList(str2).stream().filter(str->str[0].equals("a")||str[1].equals("a")).forEach(strings -> System.out.println(strings[0]+" ,"+strings[1]));
        // 배열의 크기를 알 수없을 때.

        String str1[][] = {
                {"a", "b","f"},
                {"c", "d"},
                {"e","a"},
                {"a", "h", "a"},
                {"i", "j"}
        };
        Stream<String> stream = Arrays.asList(str1).stream().flatMap(strings -> Arrays.stream(strings));
        stream.forEach(System.out::println);
    }
}
