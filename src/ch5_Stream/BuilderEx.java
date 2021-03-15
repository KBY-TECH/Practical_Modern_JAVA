package ch5_Stream;

import java.util.stream.Stream;

public class BuilderEx {
    public static void main(String[] args) {
        // accept
        Stream.Builder<String> builder=Stream.builder();
        for (int i = 0; i < 10; i++) {
            String s=Integer.toString(i+1);
            builder.accept(s);
        }
        builder.build().forEach(System.out::print);
        System.out.println();
        // accept
        Stream.Builder<Integer> builder2=Stream.builder();
        int cnt=1;
            builder2.add(cnt).add(++cnt).add(++cnt);
        builder2.build().forEach(System.out::print);
    }
}
