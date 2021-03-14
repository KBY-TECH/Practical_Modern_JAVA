package ch4_Lamda_FunctionalIFS;

import java.util.function.IntPredicate;

public class IntPredicateEx {
    public static boolean isPositive(int i, IntPredicate intPredicate)
    {
        return intPredicate.test(i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1_000; i++) {
            System.out.println(isPositive(i,(int val)-> val>0));
        }
    }
}
