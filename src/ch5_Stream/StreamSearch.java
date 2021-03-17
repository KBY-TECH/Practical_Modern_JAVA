package ch5_Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamSearch {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1_000_000_000,3,5,7,11);

        // allMatch
        boolean ans1=list.stream().allMatch(integer -> (integer<10));
        System.out.println("모든 원소가 10보다 작은 가요?"+ans1);
        // anyMatch
        boolean ans2=list.stream().anyMatch(integer -> integer%3==0);
        System.out.println("3의 배수가 하나라도 존재하나요?"+ans2);

        // noneMatch
        boolean ans3=list.stream().noneMatch(integer -> integer==1);
        System.out.println("모든 요소들이 1인가요?"+ans3);

        Optional<Integer> res=list.stream().parallel().filter(integer -> integer>4).findAny();
        Optional<Integer> res2=list.stream().parallel().findFirst();
        System.out.println("4보다 큰 값"+res.get());
        System.out.println("첫번째 값 "+res2.get());
    }
}
