package ch4_Lamda_FunctionalIFS;

import jdk.jshell.spi.SPIResolutionException;

import java.util.function.Predicate;

public class PredicateEx {
    public static boolean isValid(String name, Predicate<String> predicate)
    {
        return predicate.test(name);
    }
    public static void main(String[] args) {
        System.out.println(isValid("",(String name)->!name.isEmpty()));
    }
}
