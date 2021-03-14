package ch4_Lamda_FunctionalIFS;

import java.util.ArrayList;
import java.util.List;

public class ConstructorRefEx {
    private String name;

    public ConstructorRefEx(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConstructorRefEx{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        // 람다 표현식
        list.stream().map((String name)-> new ConstructorRefEx(name)).forEach( (ConstructorRefEx constructorRefEx) -> System.out.println(constructorRefEx));

        // 생성자 참조로 변환
        list.stream().map(ConstructorRefEx::new).forEach( (ConstructorRefEx constructorRefEx) -> System.out.println(constructorRefEx));

        // 생성자 참조, 메서드 참조로 변환
        list.stream().map(ConstructorRefEx::new).forEach(System.out::println);

    }
}
