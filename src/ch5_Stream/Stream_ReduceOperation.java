package ch5_Stream;

import java.util.ArrayList;
import java.util.List;

public class Stream_ReduceOperation {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i+1);
        }

        int sum=list.stream().reduce(0, Integer::sum);
        //Integer::sum
        int sum1 = Integer.sum(10, 100);
        System.out.println(sum1);
        System.out.println(sum);

        // 두번째 파라미터 값을 리턴받아 다음 값이랑 비교 또는 덧샘을 시도한다.
        int parallelSum=list.parallelStream().reduce(0,(integer, integer2) -> integer+integer2);
        System.out.println(parallelSum);
        int max=list.stream().reduce(0,(integer, integer2) -> Math.max(integer,integer2));
        System.out.println(max);
        int min=list.stream().reduce(0,(integer, integer2) -> Math.min(integer,integer2));
        System.out.println(min);
    }
}
