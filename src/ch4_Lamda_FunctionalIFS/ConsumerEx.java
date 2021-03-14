package ch4_Lamda_FunctionalIFS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerEx {
    public static void exeConsumer(List<String> nameList, Consumer<String> consumer)
    {
        for (String s : nameList) {
            consumer.accept(s);
        }
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("k");
        list.add("p");
        list.add("l");
        list.add("o");
        ConsumerEx.exeConsumer(list,(String name)-> System.out.println(name));
    }
}
