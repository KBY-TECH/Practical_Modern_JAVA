package ch5_Stream;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMap_FlatMap
{
    public static void main(String[] args) {
        // Person 객체를 String 으로 반환
        List<Person> list=Stream_Filter_Distinct_Ex.init();
        Stream<String> stream = list.stream().map((Person p) -> p.toString());
        stream.forEach((String s) -> {
            String str[]=s.split("");
            for (String s1 : str) {
                System.out.print(s1+" ");
            }
        });
        System.out.println("");

        IntStream integerStream = list.stream().mapToInt((Person p) -> p.getAge());
        integerStream.forEach((int i)-> System.out.println(i));
    }
}
