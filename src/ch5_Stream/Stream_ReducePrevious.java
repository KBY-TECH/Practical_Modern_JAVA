package ch5_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_ReducePrevious {
    public static void main(String[] args) {

        //=================리듀스 연산을 배우기 전=============================
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i+1);
        }
        // 트릭 :  어떻게 배열은 컴파일이 허용될까?
        // 한번 배열이 생성되고 나면 배열의 크기를 변경할 수 없기 때문에 언어적으로는 final이므로 참조가 가능하다. sum자체는 final이고 sum[0]은 final이 아니기 때문에 정상적인 컴파일과 실행이 된다.

        int sum[]={0};
        // 람다 로컬 변수는 final이거나 final과 유사한 형태여야 한다.
        list.stream().forEach(integer -> sum[0]+=integer);
        System.out.println(sum[0]);

        // intStream
        System.out.println(list.stream().mapToInt(value -> value).sum());
        // collect 연산
        System.out.println(list.stream().collect(Collectors.summingInt(value -> value)));
    }
}
