package ch5_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Stream_Filter_Distinct_Ex {
    public static void main(String[] args) {
        List<Person> list=init();
        Stream<Person> stream=list.stream();
        stream.distinct().forEach(System.out::println);

        Stream<Person> stream2=list.stream();
    }
    public static List<Person> init()
    {
        List<Person> list=new ArrayList<>();
        list.add(new Person("K",10));
        list.add(new Person("K",20));
        list.add(new Person("P",20));
        list.add(new Person("L",31));
        return list;
    }
}
