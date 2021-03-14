package ch4_Lamda_FunctionalIFS;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryOperatorEx {
    public static void main(String[] args) {
        // 하나의 연산.
        UnaryOperator<Integer> op=(Integer i)->i*2;
        for (int i = 0; i < 3; i++) {
            System.out.println(op.apply(i));
        }// 두개의 연산
        System.out.println("=========");
        BinaryOperator<Integer> op2=(Integer i,Integer j)-> i*j;
        for (int i = 0; i < 4; i++) {
            System.out.println(op2.apply(i,i+1));
        }
    }
}
