package ch4_Lamda_FunctionalIFS;

import java.util.function.BiConsumer;

public class BiConsumerEx {
    public static void exBiConsumer(String p1, String p2, BiConsumer<String,String> consumer)
    {
        consumer.accept(p1,p2);
    }

    public static void main(String[] args) {
        BiConsumer<String,String> biConsumer=(s, s2) -> {
            System.out.println(s);
            System.out.println(s2);
        };
        BiConsumerEx.exBiConsumer("hello","world!",biConsumer);
    }
}
