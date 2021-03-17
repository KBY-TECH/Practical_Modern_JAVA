package ch5_Stream;

import java.util.Comparator;
import java.util.List;

public class Stream_Sord_Ex {
    public static void main(String[] args) {


           List<Person> list=Stream_Filter_Distinct_Ex.init();
        System.out.println("람다 외부 조건 ");
           list.stream().sorted(Comparator.comparing(person -> person.getName())).forEach(System.out::println);
           // A.getName().compareTo(B.getName()) 풀어서 쓰면.

        System.out.println(" 역순 Comparable 기준");
           list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);





    }
}
