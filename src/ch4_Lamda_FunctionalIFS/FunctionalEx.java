package ch4_Lamda_FunctionalIFS;

import java.util.function.Function;

public class FunctionalEx {
    public static int exFunction(String context, Function<String,Integer> function)
    {
        return function.apply(context);
    }
    public static void main(String[] args) {
        System.out.println(FunctionalEx.exFunction("hello world",(String s)-> s.length()));
    }
}
