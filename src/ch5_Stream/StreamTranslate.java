package ch5_Stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTranslate {
    public static void main(String[] args) {
        List<Person> list=Stream_Filter_Distinct_Ex.init();

        List<Person> k = list.stream().filter(person -> person.getName().equals("K")).sorted().collect(Collectors.toList());
        k.forEach(System.out::println);
        // 재사용이 가능하게 컬렉션으로 변환 후 컬렉션 변수에 담아 냄.
        List<Person> limit=  k.stream().limit(1).collect(Collectors.toList());
        System.out.println(limit);
    }
}
