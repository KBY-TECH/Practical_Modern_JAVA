package ch5_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SteamConstructor {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 1; i <11 ; i++) {
            list.add(i);
        }

        Stream<Integer> stream=list.stream();
        // 최종 연산
        System.out.println(stream.count());
        // 중간 연산.
        Stream<Integer> stream2=stream.limit(5);
        stream2.forEach(System.out::println);
    }
}
