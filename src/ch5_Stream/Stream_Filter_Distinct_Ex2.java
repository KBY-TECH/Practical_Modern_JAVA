package ch5_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stream_Filter_Distinct_Ex2 {
    public static <T> Predicate<T> distinctByKey(Function<? super T,?> key)
    {
        Map<Object,Boolean> seen=new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(key.apply(t),Boolean.TRUE)==null;
    }
    public static void main(String[] args) {
        List<Person> list=init();

        list.stream().filter(distinctByKey(b->(b.getName() +b.getAge()))).forEach(System.out::println);
    }
    public static List<Person> init()
    {
        List<Person> list=new ArrayList<>();
        list.add(new Person("K",10));
        list.add(new Person("K",10));
        list.add(new Person("P",20));
        list.add(new Person("L",31));
        return list;
    }
}
