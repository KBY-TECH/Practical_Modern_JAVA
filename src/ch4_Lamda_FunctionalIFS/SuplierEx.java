package ch4_Lamda_FunctionalIFS;

import java.util.function.Supplier;

public class SuplierEx {
    public static String exeSuplier(Supplier<String> stringSupplier)
    {
        return stringSupplier.get();
    }

    public static void main(String[] args) {
        String version="java upgrade Book, version 0.1 BETA";
        System.out.println( exeSuplier(() -> {return version;}));
    }
}
