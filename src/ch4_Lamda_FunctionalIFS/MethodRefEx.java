package ch4_Lamda_FunctionalIFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MethodRefEx {
    public static MethodRefEx of()
    {
        return new MethodRefEx();
    }

    public static void exeMethod(String entity)
    {
        if(entity!=null && ! "".equals(entity))
        {
            System.out.println(entity);
            System.out.println(entity.length());
        }
    }
    public  void toUpper(String entity)
    {
        System.out.println(entity.toUpperCase());
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        // 정적 메서드 참조
        System.out.println("===");
        list.stream().forEach(MethodRefEx::exeMethod);
        System.out.println("===");
        // 한정적 메서드 참조
        list.stream().forEach(MethodRefEx.of()::toUpper);
        System.out.println("===");
        // 비한정적 메서드 참조.
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }


}
