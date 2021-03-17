package ch5_Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTranlateEx {
    public static void main(String[] args) {
        Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        String fruitList = fruits.collect(Collectors.joining(","));
        System.out.println(fruitList);


        Stream<Person> stream=Stream_Filter_Distinct_Ex.init().stream();
       int age= stream.collect(Collectors.summingInt(Person::getAge));
        System.out.println(age);

        String str[]={"banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon"};
        Stream<String> stream1 = Arrays.stream(str);
        for (int i = 0; i < str.length; i++) {
            if(str[i].equals("apple"))
            {
                str[i]="null";
            }
        }
        System.out.println(Arrays.toString(str));
        stream1.forEach(System.out::print);

    }
}
